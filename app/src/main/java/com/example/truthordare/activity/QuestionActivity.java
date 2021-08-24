package com.example.truthordare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.truthordare.R;
import com.example.truthordare.adapter.ViewPagerAdapter;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.dialog.AddQuestionDialog;
import com.example.truthordare.dialog.AdvertisingDialog;
import com.example.truthordare.fragment.QuestionLIstFragment;
import com.example.truthordare.interfaces.CallBackAddQuestions;
import com.example.truthordare.interfaces.CallBackUpdateList;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    String listType;
    Questions questions;
    QuestionLIstFragment truthQuestionFragment;
    QuestionLIstFragment dareQuestionFragment;

    ArrayList<String> truthList;
    ArrayList<String> dareList;

    FloatingActionButton floatingActionButton;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_tab);


        findViews();
        init();
        setupViewPager();
        configuration();


    }

    @Override
    protected void onResume() {
        super.onResume();

        Setting setting = new Setting(QuestionActivity.this);
        if (setting.isAppSound() && !MyMediaPlayer.mpMainSound.isPlaying()) {

            MyMediaPlayer.mpMainSound.start();
        }
    }

    private void findViews() {

        tabLayout = findViewById(R.id.my_tab_layout);
        viewPager = findViewById(R.id.my_view_pager);
        floatingActionButton = findViewById(R.id.float_btn);
    }

    private void init() {

       listType =getIntent().getStringExtra(MyConstant.LIST_TYPE);
       questions = new Questions(this);

        if (listType.equals(MyConstant.MY_LIST)) {

            truthList = questions.getMyTruthQuestionList();
            dareList = questions.getMyDareQuestionList();

            truthQuestionFragment = new QuestionLIstFragment(truthList, questions,MyConstant.MY_TRUTH);
            dareQuestionFragment = new QuestionLIstFragment(dareList,  questions,MyConstant.MY_DARE);


        } else if (listType.equals(MyConstant.DEFAULT_LIST)) {

            truthList = questions.getTruthQuestionList();
            dareList = questions.getDareQuestionList();
            truthQuestionFragment = new QuestionLIstFragment(truthList,  questions,MyConstant.TRUTH);
            dareQuestionFragment = new QuestionLIstFragment(dareList,  questions,MyConstant.DARE);

        }

    }

    private void setupViewPager() {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (listType.equals(MyConstant.MY_LIST)) {

            viewPagerAdapter.addToList(truthQuestionFragment, getString(R.string.my_truth));
            viewPagerAdapter.addToList(dareQuestionFragment, getString(R.string.my_dare));

        } else if (listType.equals(MyConstant.DEFAULT_LIST)) {

            viewPagerAdapter.addToList(truthQuestionFragment, getString(R.string.truth));
            viewPagerAdapter.addToList(dareQuestionFragment, getString(R.string.dare));

        }


        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    protected void onPause() {
        super.onPause();
        questions.updateQuestions(this, questions);
    }


    private void configuration() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (listType.equals(MyConstant.MY_LIST)) {
                    showAddMyQuestionDialog();

                } else if (listType.equals(MyConstant.DEFAULT_LIST)) {

                    showAdvertisingDialog();

                }


            }
        });


    }

    private void showAddMyQuestionDialog() {
        AddQuestionDialog addQuestionDialog = new AddQuestionDialog(this, new CallBackAddQuestions() {
            @Override
            public void addQuestionToList(String key, String myQuestion) {

                if (key.equals(MyConstant.TRUTH)) {
                    truthList.add(myQuestion);
                    truthQuestionFragment.updateList();

                } else if (key.equals(MyConstant.DARE)) {

                    dareList.add(myQuestion);
                    dareQuestionFragment.updateList();

                }
            }
        });
        addQuestionDialog.show();

    }

    private void showAdvertisingDialog(){

        if(MyConstant.isNetworkAvailable(this)){

            AdvertisingDialog advertisingDialog=new AdvertisingDialog(this, questions, new CallBackUpdateList() {
                @Override
                public void updateCallBack() {
                    dareQuestionFragment.init();
                    truthQuestionFragment.init();

                }
            });
            advertisingDialog.show();


        }


    }


}