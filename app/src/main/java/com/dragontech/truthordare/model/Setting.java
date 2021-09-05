package com.dragontech.truthordare.model;

import android.content.Context;

import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.MySharedPreferences;

public class Setting {

    boolean defaultQuestion;
    boolean mYQuestion;
    boolean repeatQuestion;

    boolean appSound;
    boolean spinSound;
    boolean buttonSound;

    boolean[] lockFlags;
    int selectedPhotoPosition;


    public Setting(Context context) {

        Setting setting = MySharedPreferences.getInstance(context).getSetting();

        if (setting == null) {

            defaultQuestion = true;
            mYQuestion = true;
            repeatQuestion = false;
            appSound = true;
            spinSound = true;
            buttonSound = true;

            selectedPhotoPosition = 0;

            lockFlags = new boolean[MyConstant.BOTTLE_NUMBER];
            for (int i = 0; i < lockFlags.length; i++)
                if (i <= 3)
                    lockFlags[i] = false;
                else
                    lockFlags[i] = true;

            updateSetting(context, this);

        } else {


            defaultQuestion = setting.isDefaultQuestion();
            mYQuestion = setting.isMYQuestion();
            repeatQuestion = setting.isRepeatQuestion();
            appSound = setting.isAppSound();
            spinSound = setting.isSpinSound();

            selectedPhotoPosition = setting.getSelectedPhotoPosition();
            lockFlags = setting.getLockFlags();

            buttonSound = setting.isButtonSound();

        }


    }

    public void updateSetting(Context context, Setting setting) {

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

    public boolean isSpinSound() {
        return spinSound;
    }

    public void setSpinSound(boolean spinSound) {
        this.spinSound = spinSound;
    }

    public boolean[] getLockFlags() {
        return lockFlags;
    }

    public void setLockFlags(boolean[] lockFlags) {
        this.lockFlags = lockFlags;


    }

    public boolean isButtonSound() {
        return buttonSound;
    }

    public void setButtonSound(boolean buttonSound) {
        this.buttonSound = buttonSound;
    }

    public int getSelectedPhotoPosition() {
        return selectedPhotoPosition;
    }

    public void setSelectedPhotoPosition(int selectedPhotoPosition) {
        this.selectedPhotoPosition = selectedPhotoPosition;
    }

}
