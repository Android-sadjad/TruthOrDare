package com.example.truthordare.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySharedPreferences {

    private static Gson gson;
    SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;

    Context context;

    private static MySharedPreferences mySharedPreferences=null;

    public static MySharedPreferences getInstance(Context context){


        if(mySharedPreferences==null)
            mySharedPreferences=new MySharedPreferences(context);
        gson=new Gson();

        return mySharedPreferences;
    }

    private MySharedPreferences(Context context){

        sharedPreferences=context.getSharedPreferences("question_list",Context.MODE_PRIVATE);
        editor =sharedPreferences.edit();

        this.context=context;

    }


    public void putDareQuestionList(ArrayList<String> questionList){


        String questionListString=gson.toJson(questionList);

        editor.putString("dare_questions",questionListString).apply();


    }

    public ArrayList<String> getDareQuestionList(){

        String questionListString=sharedPreferences.getString("dare_questions",null);

        if(questionListString==null)
            return null;


        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String>questionList=gson.fromJson(questionListString,listType);


        return  questionList;

    }

    public void putTruthQuestionList(ArrayList<String> questionList){



        String questionListString=gson.toJson(questionList);

        editor.putString("truth_questions",questionListString).apply();

    }

    public ArrayList<String> getTruthQuestionList(){

        String questionListString=sharedPreferences.getString("truth_questions",null);

        if(questionListString==null)
            return null;

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();

        ArrayList<String>questionList=gson.fromJson(questionListString,listType);

        return  questionList;

    }


    public void putMyDareList(ArrayList<String> questionList){


        String questionListString=gson.toJson(questionList);

        editor.putString("My_dare",questionListString).apply();


    }

    public ArrayList<String> getMyDareList(){

        String questionListString=sharedPreferences.getString("My_dare",null);

        if(questionListString==null)
            return null;


        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String>questionList=gson.fromJson(questionListString,listType);


        return  questionList;

    }

    public void putMyTruthList(ArrayList<String> questionList){



        String questionListString=gson.toJson(questionList);

        editor.putString("My_truth",questionListString).apply();

    }

    public ArrayList<String> getMyTruthList(){

        String questionListString=sharedPreferences.getString("My_truth",null);

        if(questionListString==null)
            return null;

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();

        ArrayList<String>questionList=gson.fromJson(questionListString,listType);

        return  questionList;

    }

    public void putIsDefaultQuestion(boolean b){
        editor.putBoolean("default_question",b).apply();
    }

    public boolean getIsDefaultQuestion(){

        return sharedPreferences.getBoolean("default_question",true);
    }

    public void putIsMyQuestion(boolean b){
        editor.putBoolean("my",b).apply();
    }

    public boolean getIsMYQuestion(){

        return sharedPreferences.getBoolean("my",true);
    }





}
