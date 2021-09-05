package com.dragontech.truthordare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.adapter.SelectPhotoAdapter;
import com.dragontech.truthordare.model.MyMediaPlayer;
import com.dragontech.truthordare.model.Setting;

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

    @Override
    protected void onResume() {
        super.onResume();

        Setting set = new Setting(SettingActivity.this);
        if (set.isAppSound() && !MyMediaPlayer.mpAppSound.isPlaying()) {

            MyMediaPlayer.mpAppSound.start();
        }
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
        rvSelectPhoto.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        rvSelectPhoto.setAdapter(selectPhotoAdapter);

    }

    private void setUpSetting() {

        switchDefaultQuestion.setChecked(setting.isDefaultQuestion());
        switchMYQuestion.setChecked(setting.isMYQuestion());
        switchRepeatQuestion.setChecked(setting.isRepeatQuestion());
        switchAppSound.setChecked(setting.isAppSound());
        switchCircleSound.setChecked(setting.isSpinSound());
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

        switchCircleSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setSpinSound(isChecked);
            }
        });

        switchButtonSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setButtonSound(isChecked);
            }
        });

        switchAppSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setAppSound(isChecked);
                if (isChecked) {
                    MyMediaPlayer.mpAppSound.start();
                } else {
                    MyMediaPlayer.mpAppSound.pause();
                }
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