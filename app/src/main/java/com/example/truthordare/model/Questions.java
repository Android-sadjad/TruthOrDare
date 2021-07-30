package com.example.truthordare.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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


            questionNumber=10;

            ArrayList<String>tempList=new ArrayList<>();
            truthQuestionList = new ArrayList<>();
            dareQuestionList = new ArrayList<>();


            String[] truthList = context.getResources().getStringArray(R.array.truth_questions_list);
            tempList.addAll(Arrays.asList(truthList));

            for (int i=0;i<questionNumber;i++)
                truthQuestionList.add(tempList.get(i));


            String[] dareList = context.getResources().getStringArray(R.array.dare_questions_list);
            tempList.addAll(Arrays.asList(dareList));

            for (int i=0;i<questionNumber;i++)
                dareQuestionList.add(tempList.get(i));

            myTruthQuestionList = new ArrayList<>();
            myDareQuestionList = new ArrayList<>();

            updateQuestions(context, this);


        } else {




            truthQuestionList = questions.getTruthQuestionList();
            dareQuestionList = questions.getDareQuestionList();

            myTruthQuestionList = questions.getMyTruthQuestionList();
            myDareQuestionList = questions.getMyDareQuestionList();

            questionNumber=questions.getQuestionNumber();





        }
        Log.i("number11", String.valueOf(questionNumber));

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
