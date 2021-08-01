package com.example.truthordare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.adapter.SelectPhotoAdapter;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;

public class SettingActivity extends AppCompatActivity {

    RecyclerView rvSelectPhoto;
    SelectPhotoAdapter selectPhotoAdapter;
    Setting setting;

    Switch switchDefaultQuestion;
    Switch switchMYQuestion;
    Switch switchRepeatQuestion;
    Switch switchAppSound;
    Switch switchCircleSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        findViews();
        init();
        setUpSetting();
        setSwitchOnClick();




    }

    private void findViews() {

        rvSelectPhoto = findViewById(R.id.rv_select_photo);

        switchDefaultQuestion = findViewById(R.id.switch_default);
        switchMYQuestion = findViewById(R.id.switch_my);
        switchRepeatQuestion = findViewById(R.id.switch_repeat_question);
        switchAppSound = findViewById(R.id.switch_app_sound);
        switchCircleSound = findViewById(R.id.switch_circle_cound);

    }

    private void init() {

        setting = new Setting(this);

        selectPhotoAdapter = new SelectPhotoAdapter(this, setting);
        rvSelectPhoto.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSelectPhoto.setAdapter(selectPhotoAdapter);

    }

    private void setUpSetting() {

        switchDefaultQuestion.setChecked(setting.isDefaultQuestion());
        switchMYQuestion.setChecked(setting.isMYQuestion());
        switchRepeatQuestion.setChecked(setting.isRepeatQuestion());
        switchAppSound.setChecked(setting.isAppSound());
        switchCircleSound.setChecked(setting.isCircleSound());

    }

    public void setSwitchOnClick() {


        switchDefaultQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setDefaultQuestion(isChecked);
            }
        });

        switchMYQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setMYQuestion(isChecked);
            }
        });

        switchRepeatQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setRepeatQuestion(isChecked);
            }
        });

        switchAppSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setAppSound(isChecked);
                if (isChecked){
                    MyMediaPlayer.mediaPlayer.start();
                }else {
                    MyMediaPlayer.mediaPlayer.pause();
                }


            }
        });

        switchCircleSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setCircleSound(isChecked);
            }
        });


    }





    @Override
    protected void onPause() {
        super.onPause();

        setting.updateSetting(this, setting);

    }

    @Override
    protected void onStop() {
        super.onStop();


        setResult(Activity.RESULT_OK, new Intent());



    }
}