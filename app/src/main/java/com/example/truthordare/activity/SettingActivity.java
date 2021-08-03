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
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;

public class SettingActivity extends AppCompatActivity {

    RecyclerView rvSelectPhoto;
    SelectPhotoAdapter selectPhotoAdapter;
    Setting setting;

    Switch switchDefaultQuestion;
    Switch switchMYQuestion;
    Switch switchRepeatQuestion;
    Switch switchAppSound;
    Switch switchCircleSound;
    Switch switchButtonSound;


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
        switchButtonSound = findViewById(R.id.switch_btn_sound);
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
        switchButtonSound.setChecked(setting.isButtonSound());

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
                    MyMediaPlayer.mpMainSound.start();
                }else {
                    MyMediaPlayer.mpMainSound.pause();
                }


            }
        });

        switchCircleSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setCircleSound(isChecked);
            }
        });

       switchButtonSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               setting.setButtonSound(isChecked);
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