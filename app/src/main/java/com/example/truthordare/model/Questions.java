package com.example.truthordare.model;

import android.app.Activity;
import android.content.Context;

import com.example.truthordare.R;
import com.example.truthordare.classes.MySharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class Questions {


    ArrayList<String> truthQuestionList;
    ArrayList<String> dareQuestionList;

    ArrayList<String> myTruthQuestionList;
    ArrayList<String> myDareQuestionList;


    public Questions() {

    }

    public Questions(Activity context) {


        Questions questions = MySharedPreferences.getInstance(context).getQuestions();

        if (questions == null) {

            truthQuestionList = new ArrayList<>();
            String[] truthList = context.getResources().getStringArray(R.array.truth_questions_list);
            truthQuestionList.addAll(Arrays.asList(truthList));

            dareQuestionList = new ArrayList<>();
            String[] dareList = context.getResources().getStringArray(R.array.dare_questions_list);
            dareQuestionList.addAll(Arrays.asList(dareList));

            myTruthQuestionList = new ArrayList<>();
            myDareQuestionList = new ArrayList<>();

            updateQuestions(context, this);


        } else {

            truthQuestionList = questions.getTruthQuestionList();
            dareQuestionList = questions.getDareQuestionList();

            myTruthQuestionList = questions.getMyTruthQuestionList();
            myDareQuestionList = questions.getMyDareQuestionList();


        }

    }

    ////////////////////////////////////////////////////////////////////////////////

    public void updateQuestions(Context context, Questions questions) {
        MySharedPreferences.getInstance(context).putQuestions(questions);

    }

    ////////////////////////////////////////////////////////////////////////////////
    public ArrayList<String> getTruthQuestionList() {
        return truthQuestionList;
    }

    public void setTruthQuestionList(ArrayList<String> truthQuestionList) {
        this.truthQuestionList = truthQuestionList;
    }

    public ArrayList<String> getDareQuestionList() {
        return dareQuestionList;
    }

    public void setDareQuestionList(ArrayList<String> dareQuestionList) {
        this.dareQuestionList = dareQuestionList;
    }

    public ArrayList<String> getMyTruthQuestionList() {
        return myTruthQuestionList;
    }

    public void setMyTruthQuestionList(ArrayList<String> myTruthQuestionList) {
        this.myTruthQuestionList = myTruthQuestionList;
    }

    public ArrayList<String> getMyDareQuestionList() {
        return myDareQuestionList;
    }

    public void setMyDareQuestionList(ArrayList<String> myDareQuestionList) {
        this.myDareQuestionList = myDareQuestionList;
    }

}
