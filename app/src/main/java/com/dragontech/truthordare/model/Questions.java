package com.dragontech.truthordare.model;

import android.content.Context;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.MySharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class Questions {

    int questionNumber;

    private ArrayList<String> truthQuestionList;
    private ArrayList<String> dareQuestionList;
    private ArrayList<String> myTruthQuestionList;
    private ArrayList<String> myDareQuestionList;


    public Questions() {

    }

    public Questions(Context context) {

        Questions questions = MySharedPreferences.getInstance(context).getQuestions();

        if (questions == null) {


            questionNumber = MyConstant.FREE_QUESTION_NUMBER;

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

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

}
