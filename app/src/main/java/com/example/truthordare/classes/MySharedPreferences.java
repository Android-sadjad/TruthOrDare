package com.example.truthordare.classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySharedPreferences {

    private static Gson gson;
    private static MySharedPreferences mySharedPreferences = null;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<String> truthQuestionList;
    ArrayList<String> dareQuestionList;
    ArrayList<String> myTruthQuestionList;
    ArrayList<String> myDareQuestionList;
    String truth;
    String dare;
    String my_truth;
    String my_dare;


    //////////////////////////////////////////////////////////////////////////////////
    int questionNumber;

    private MySharedPreferences(Context context) {

        sharedPreferences = context.getSharedPreferences(MyConstant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        this.context = context;

    }

    public static MySharedPreferences getInstance(Context context) {


        if (mySharedPreferences == null)
            mySharedPreferences = new MySharedPreferences(context);
        gson = new Gson();

        return mySharedPreferences;
    }

    //////////////////////////////////////////////////////////////////////////////////

    public void putSetting(Setting settingModel) {


        String settingStr = gson.toJson(settingModel, Setting.class);
        editor.putString(MyConstant.SETTING, settingStr).apply();

    }

    public Setting getSetting() {

        String settingStr = sharedPreferences.getString(MyConstant.SETTING, null);

        if (settingStr == null)
            return null;

        return gson.fromJson(settingStr, Setting.class);

    }

    //////////////////////////////////////////////////////////////////////////////////
    public Questions getQuestions() {

        truth = sharedPreferences.getString(MyConstant.TRUTH, null);
        dare = sharedPreferences.getString(MyConstant.DARE, null);
        my_truth = sharedPreferences.getString(MyConstant.MY_TRUTH, null);
        my_dare = sharedPreferences.getString(MyConstant.MY_DARE, null);
        questionNumber = sharedPreferences.getInt(MyConstant.QUESTION_NUMBER, 10);

        if (truth == null)
            return null;


        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        Questions questions = new Questions();

        questions.setTruthQuestionList(gson.fromJson(truth, listType));
        questions.setDareQuestionList(gson.fromJson(dare, listType));
        questions.setMyTruthQuestionList(gson.fromJson(my_truth, listType));
        questions.setMyDareQuestionList(gson.fromJson(my_dare, listType));
        questions.setQuestionNumber(questionNumber);


        return questions;

    }

    public void putQuestions(Questions questions) {

        truthQuestionList = questions.getTruthQuestionList();
        dareQuestionList = questions.getDareQuestionList();
        myTruthQuestionList = questions.getMyTruthQuestionList();
        myDareQuestionList = questions.getMyDareQuestionList();
        questionNumber = questions.getQuestionNumber();

        truth = gson.toJson(truthQuestionList);
        editor.putString(MyConstant.TRUTH, truth).apply();

        dare = gson.toJson(dareQuestionList);
        editor.putString(MyConstant.DARE, dare).apply();

        my_truth = gson.toJson(myTruthQuestionList);
        editor.putString(MyConstant.MY_TRUTH, my_truth).apply();

        my_dare = gson.toJson(myDareQuestionList);
        editor.putString(MyConstant.MY_DARE, my_dare).apply();

        editor.putInt(MyConstant.QUESTION_NUMBER, questionNumber).apply();


    }

}
