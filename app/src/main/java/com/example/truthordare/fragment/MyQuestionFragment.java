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
import com.example.truthordare.classes.Questions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MyQuestionFragment extends Fragment {

    QuestionFragment truthQuestionFragment;
    QuestionFragment dareQuestionFragment;

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

    }

    private void init() {

        questions=new Questions();

        truthQuestionFragment = new QuestionFragment(questions.getTruthQuestionList(getContext()));
        dareQuestionFragment = new QuestionFragment(questions.getDareQuestionList(getContext()));


    }

    private void configuration() {



    }
}
