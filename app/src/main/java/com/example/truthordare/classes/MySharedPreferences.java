package com.example.truthordare.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySharedPreferences {

     SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;

    Context context;

    private static MySharedPreferences mySharedPreferences=null;

    public static MySharedPreferences getInstance(Context context){


        if(mySharedPreferences==null)
            mySharedPreferences=new MySharedPreferences(context);

        return mySharedPreferences;
    }

    private MySharedPreferences(Context context){

        sharedPreferences=context.getSharedPreferences("question_list",Context.MODE_PRIVATE);
        editor =sharedPreferences.edit();

        this.context=context;

    }


    public void putDareQuestionList(ArrayList<String> questionList){

        Gson gson = new Gson();
        String questionListString=gson.toJson(questionList);

        editor.putString("dare_questions",questionListString).apply();


    }

    public ArrayList<String> getDareQuestionList(){

        String questionListString=sharedPreferences.getString("dare_questions",null);

        if(questionListString==null)
            return null;

        Gson gson=new Gson();
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String>questionList=gson.fromJson(questionListString,listType);


        return  questionList;

    }

    public void putTruthQuestionList(ArrayList<String> questionList){

        Gson gson = new Gson();

        String questionListString=gson.toJson(questionList);

        editor.putString("truth_questions",questionListString).apply();

    }

    public ArrayList<String> getTruthQuestionList(){

        String questionListString=sharedPreferences.getString("truth_questions",null);

        if(questionListString==null)
            return null;

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        Gson gson=new Gson();
        ArrayList<String>questionList=gson.fromJson(questionListString,listType);

        return  questionList;

    }


}
