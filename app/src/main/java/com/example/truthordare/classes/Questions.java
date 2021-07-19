package com.example.truthordare.classes;

import android.content.Context;
import android.widget.Toast;

import com.example.truthordare.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Questions {


    ArrayList<String> truthQuestionList;
    ArrayList<String> dareQuestionList;

    MySharedPreferences mySharedPreferences;



    public ArrayList<String> getTruthQuestionList(Context context) {

        mySharedPreferences = MySharedPreferences.getInstance(context);
        truthQuestionList = mySharedPreferences.getTruthQuestionList();

        if (truthQuestionList == null) {

            initTruthQuestionList(context);
            mySharedPreferences.putTruthQuestionList(truthQuestionList);
        }

        return truthQuestionList;
    }

    private void initTruthQuestionList(Context context) {

        truthQuestionList= new ArrayList<>();

        String[]list=context.getResources().getStringArray(R.array.truth_questions_list);
        truthQuestionList.addAll(Arrays.asList(list));


    }

    public ArrayList<String> getDareQuestionList(Context context) {

        mySharedPreferences = MySharedPreferences.getInstance(context);
        dareQuestionList = mySharedPreferences.getDareQuestionList();

        if (dareQuestionList == null) {
            initDareQuestionList(context);
            mySharedPreferences.putDareQuestionList(dareQuestionList);
        }

        return dareQuestionList;

    }

    private void initDareQuestionList(Context context) {

        dareQuestionList=new ArrayList<String>();
        String[]list=context.getResources().getStringArray(R.array.dare_questions_list);
        dareQuestionList.addAll(Arrays.asList(list));

    }


}
