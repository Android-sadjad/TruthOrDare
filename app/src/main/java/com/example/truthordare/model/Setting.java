package com.example.truthordare.model;

import android.content.Context;
import android.widget.Toast;

import com.example.truthordare.classes.MySharedPreferences;

public class Setting {

    boolean defaultQuestion;
    boolean mYQuestion;
    boolean repeatQuestion;
    boolean appSound;
    boolean circleSound;

    boolean []lockFlags;
    boolean []checkBoxFlags;

    final int PHOTO_NUMBER=30;

    public Setting(Context context){

        Setting setting= MySharedPreferences.getInstance(context).getSetting();

        if(setting==null){
            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
            defaultQuestion=true;
            mYQuestion=false;
            repeatQuestion=false;
            appSound=true;
            circleSound=true;

            checkBoxFlags=new boolean[PHOTO_NUMBER];
            lockFlags=new boolean[PHOTO_NUMBER];

            for (int i=0;i<checkBoxFlags.length;i++)
                if(i==0)
                    checkBoxFlags[i]=true;
                else
                    checkBoxFlags[i]=false;


            for (int i=0;i<lockFlags.length;i++)
                if(i==0||i==1||i==2)
                    lockFlags[i]=false;
                else
                    lockFlags[i]=true;

            updateSetting(context,this);

        }

        else {
            Toast.makeText(context, "not null", Toast.LENGTH_SHORT).show();

            defaultQuestion=setting.isDefaultQuestion();
            mYQuestion=setting.isMYQuestion();
            repeatQuestion=setting.isRepeatQuestion();
            appSound=setting.isAppSound();
            circleSound=setting.isCircleSound();

            checkBoxFlags=setting.getCheckBoxFlags();
            lockFlags=setting.getLockFlags();

        }


    }

    public void updateSetting(Context context,Setting setting){

        MySharedPreferences.getInstance(context).putSetting(setting);
    }


    public boolean isDefaultQuestion() {
        return defaultQuestion;
    }

    public void setDefaultQuestion(boolean defaultQuestion) {
        this.defaultQuestion = defaultQuestion;
    }

    public boolean isMYQuestion() {
        return mYQuestion;
    }

    public void setMYQuestion(boolean MYQuestion) {
        this.mYQuestion = MYQuestion;
    }

    public boolean isRepeatQuestion() {
        return repeatQuestion;
    }

    public void setRepeatQuestion(boolean repeatQuestion) {
        this.repeatQuestion = repeatQuestion;
    }

    public boolean isAppSound() {
        return appSound;
    }

    public void setAppSound(boolean appSound) {
        this.appSound = appSound;
    }

    public boolean isCircleSound() {
        return circleSound;
    }

    public void setCircleSound(boolean circleSound) {
        this.circleSound = circleSound;
    }

    public boolean[] getLockFlags() {
        return lockFlags;
    }

    public void setLockFlags(boolean[] lockFlags) {
        this.lockFlags = lockFlags;
    }

    public boolean[] getCheckBoxFlags() {
        return checkBoxFlags;
    }

    public void setCheckBoxFlags(boolean[] checkBoxFlags) {
        this.checkBoxFlags = checkBoxFlags;
    }
}
