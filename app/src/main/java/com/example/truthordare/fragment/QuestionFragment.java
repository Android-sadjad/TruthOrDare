package com.example.truthordare.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.adapter.RvAdapter;
import com.example.truthordare.R;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    RecyclerView rvQuestionList;
    ArrayList<String> questionList;
    RvAdapter questionsAdapter;

    public QuestionFragment(ArrayList<String> questionList){
        this.questionList=questionList;
    }


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


        questionsAdapter = new RvAdapter(questionList);
        rvQuestionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvQuestionList.setAdapter(questionsAdapter);

    }

    public void updateList(){
        questionsAdapter.notifyDataSetChanged();

    }

}
