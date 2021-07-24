package com.example.truthordare.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.truthordare.R;
import com.example.truthordare.adapter.ViewPagerAdapter;
import com.example.truthordare.classes.MyCallBack;
import com.example.truthordare.classes.MySharedPreferences;
import com.example.truthordare.classes.Questions;
import com.example.truthordare.dialog.AddQuestionDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyQuestionFragment extends Fragment {
LinearLayout llGuide;
    QuestionFragment truthQuestionFragment;
    QuestionFragment dareQuestionFragment;
    SharedPreferences sharedPreferences;
    ArrayList<String> myTruthList;
    ArrayList<String> myDareList;

    FloatingActionButton floatingActionButton;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Questions questions;




    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_question,container,false);
    }



    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        init();
        setupViewPager();
        configuration();






    }
    private void setupViewPager() {



        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addToList(dareQuestionFragment, "جرعت من");
        viewPagerAdapter.addToList(truthQuestionFragment, "حقیقت من");
        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.setupWithViewPager(viewPager);


    }

    private void findViews(View view) {

        tabLayout = view.findViewById(R.id.my_tab_layout);
        viewPager = view.findViewById(R.id.my_view_pager);
        floatingActionButton = view.findViewById(R.id.float_btn);
        llGuide = view.findViewById(R.id.ll_guide);

    }

    private void init() {



        myDareList= MySharedPreferences.getInstance(getContext()).getMyDareList();
        myTruthList= MySharedPreferences.getInstance(getContext()).getMyTruthList();

        if(myTruthList==null){

            myTruthList=new ArrayList<>();
            llGuide.setVisibility(View.VISIBLE);
        }
        if(myDareList==null){
            myDareList=new ArrayList<>();
            llGuide.setVisibility(View.VISIBLE);
        }

        truthQuestionFragment = new QuestionFragment(myTruthList);
        dareQuestionFragment = new QuestionFragment(myDareList);


    }

    @Override
    public void onStop() {
        super.onStop();
    MySharedPreferences.getInstance(getContext()).putMyDareList(myDareList);
    MySharedPreferences.getInstance(getContext()).putMyTruthList(myTruthList);


    }




    private void configuration() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuestionDialog addQuestionDialog=new AddQuestionDialog(getContext(), new MyCallBack() {
                    @Override
                    public void callBackPlayerList(ArrayList<String> playerName) {

                    }

                    @Override
                    public void callBackAddList(String key,String myQuestion) {
                        if (key.equals("truth")){
                            myTruthList.add(myQuestion);
                            truthQuestionFragment.updateList();

                        }
                        else if (key.equals("dare")){

                            myDareList.add(myQuestion);
                            dareQuestionFragment.updateList();

                        }



                    }
                });
                addQuestionDialog.show();


            }
        });



    }
}
