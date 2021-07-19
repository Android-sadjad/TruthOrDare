package com.example.truthordare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class QuestionListFragment extends Fragment {

    QuestionFragment truthQuestionFragment;
    QuestionFragment dareQuestionFragment;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        init();
        setupViewPager();
        configuration();


    }

    private void setupViewPager() {

        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addToList(new QuestionFragment(), "جرعت");
        viewPagerAdapter.addToList(new QuestionFragment(), "حقیقت");
        viewPager.setAdapter(viewPagerAdapter);

    }

    private void findViews(View view) {

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

    }

    private void init() {

        truthQuestionFragment = new QuestionFragment();
        dareQuestionFragment = new QuestionFragment();


    }

    private void configuration() {

    }


}