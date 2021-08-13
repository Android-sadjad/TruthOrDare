package com.example.truthordare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.adapter.listQuestionsAdapter;
import com.example.truthordare.R;
import com.example.truthordare.model.Questions;

import java.util.ArrayList;

public class QuestionLIstFragment extends Fragment {

    RecyclerView rvQuestionList;
    ArrayList<String> questionList;
    listQuestionsAdapter questionsAdapter;

    LinearLayout llGuide;
    String listName;

    public QuestionLIstFragment(ArrayList<String> questionList, String listName){
        this.questionList=questionList;

        this.listName=listName;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        init();

        setVisibilityGuideLayout();

    }



    private void findViews(View view){

        rvQuestionList = view.findViewById(R.id.rv_question_list);
        llGuide = view.findViewById(R.id.ll_guide);
    }

    public void init(){


        questionsAdapter = new listQuestionsAdapter(questionList,listName,new Questions(getContext()));
        rvQuestionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvQuestionList.setAdapter(questionsAdapter);

    }

    private void setVisibilityGuideLayout() {

        if(questionList.isEmpty())
            llGuide.setVisibility(View.VISIBLE);
        else
            llGuide.setVisibility(View.GONE);
    }


    public void updateList(){
        questionsAdapter.notifyDataSetChanged();

        setVisibilityGuideLayout();

    }





}
