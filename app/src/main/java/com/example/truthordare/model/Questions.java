package com.example.truthordare.model;

import android.content.Context;
import android.util.Log;

import com.example.truthordare.R;
import com.example.truthordare.classes.MySharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class Questions {


    ArrayList<String> truthQuestionList;
    ArrayList<String> dareQuestionList;

    ArrayList<String> myTruthQuestionList;
    ArrayList<String> myDareQuestionList;


    int questionNumber;


    public Questions() {

    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Questions(Context context) {


        Questions questions = MySharedPreferences.getInstance(context).getQuestions();

        if (questions == null) {


            questionNumber = 20;

            initDefaultQuestion(context);

            myTruthQuestionList = new ArrayList<>();
            myDareQuestionList = new ArrayList<>();

            updateQuestions(context, this);


        } else {


            questionNumber = questions.getQuestionNumber();


            truthQuestionList = questions.getTruthQuestionList();
            dareQuestionList = questions.getDareQuestionList();

            myTruthQuestionList = questions.getMyTruthQuestionList();
            myDareQuestionList = questions.getMyDareQuestionList();


        }

    }


    private void initDefaultQuestion(Context context) {

        ArrayList<String> tempList = new ArrayList<>();
        truthQuestionList = new ArrayList<>();
        dareQuestionList = new ArrayList<>();

        String[] truthList = context.getResources().getStringArray(R.array.truth_questions_list);
        String[] dareList = context.getResources().getStringArray(R.array.dare_questions_list);

        truthQuestionList.addAll(Arrays.asList(truthList));
        dareQuestionList.addAll(Arrays.asList(dareList));

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
