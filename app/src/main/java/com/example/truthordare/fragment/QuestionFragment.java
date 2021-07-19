package com.example.truthordare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.adapter.QuestionsAdapter;
import com.example.truthordare.R;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    RecyclerView rvQuestionList;
    ArrayList<String> questionList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        init();

    }


    private void findViews(View view){

        rvQuestionList = view.findViewById(R.id.rv_question_list);

    }

    private void init(){

        questionList = new ArrayList<>();
        questionList.add("اخرین بار کی اب خوردی؟");
        questionList.add("اخرین بار کی نوشابه خوردی؟");
        questionList.add("اخرین بار کی دلستر خوردی؟");
        questionList.add("اخرین بار کی ابجو خوردی؟");
        questionList.add("اخرین بار کی شراب خوردی؟");
        questionList.add("اخرین بار کی ویسکی خوردی؟");


        QuestionsAdapter questionsAdapter = new QuestionsAdapter(questionList);
        rvQuestionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvQuestionList.setAdapter(questionsAdapter);

    }
}
