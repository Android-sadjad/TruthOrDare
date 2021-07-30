package com.example.truthordare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.truthordare.R;
import com.example.truthordare.adapter.ViewPagerAdapter;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.dialog.AddQuestionDialog;
import com.example.truthordare.dialog.AdvertisingDialog;
import com.example.truthordare.interfaces.CallBackAddQuestions;
import com.example.truthordare.model.Questions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabFragment extends Fragment {

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


    public TabFragment(String listType) {
        this.listType = listType;
        this.questions = new Questions(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_question, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        init();
        setupViewPager();
        configuration();


    }

    private void findViews(View view) {

        tabLayout = view.findViewById(R.id.my_tab_layout);
        viewPager = view.findViewById(R.id.my_view_pager);
        floatingActionButton = view.findViewById(R.id.float_btn);
    }

    private void init() {

        if (listType.equals(MyConstant.MY_LIST)) {

            dareList = questions.getMyDareQuestionList();
            truthList = questions.getMyTruthQuestionList();
            truthQuestionFragment = new QuestionLIstFragment(truthList, MyConstant.MY_TRUTH);
            dareQuestionFragment = new QuestionLIstFragment(dareList, MyConstant.MY_DARE);


        } else if (listType.equals(MyConstant.DEFAULT_LIST)) {

            truthList = questions.getTruthQuestionList();
            dareList = questions.getDareQuestionList();
            truthQuestionFragment = new QuestionLIstFragment(truthList, MyConstant.TRUTH);
            dareQuestionFragment = new QuestionLIstFragment(dareList, MyConstant.DARE);

        }

    }

    private void setupViewPager() {

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        if (listType.equals(MyConstant.MY_LIST)) {
            viewPagerAdapter.addToList(dareQuestionFragment, "جرعت من");
            viewPagerAdapter.addToList(truthQuestionFragment, "حقیقت من");

        } else if (listType.equals(MyConstant.DEFAULT_LIST)) {
            viewPagerAdapter.addToList(dareQuestionFragment, "جرعت");
            viewPagerAdapter.addToList(truthQuestionFragment, "حقیقت");
        }


        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public void onStop() {
        super.onStop();

        questions.updateQuestions(getContext(), questions);
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
        AddQuestionDialog addQuestionDialog = new AddQuestionDialog(getContext(), new CallBackAddQuestions() {
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

        if(MyConstant.isNetworkAvailable(getContext())){

            AdvertisingDialog advertisingDialog=new AdvertisingDialog(getContext());
            advertisingDialog.show();


        }


    }
}
