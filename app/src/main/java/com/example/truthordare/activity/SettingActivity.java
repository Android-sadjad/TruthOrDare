package com.example.truthordare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.adapter.SelectedPhotoAdapter;
import com.example.truthordare.model.Setting;

public class SettingActivity extends AppCompatActivity {

    RecyclerView rvSelectPhoto;
    SelectedPhotoAdapter selectedPhotoAdapter;
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
        switchOnClick();

    }

    private void setUpSetting() {

        switchDefaultQuestion.setChecked(setting.isDefaultQuestion());
        switchMYQuestion.setChecked(setting.isMYQuestion());
        switchRepeatQuestion.setChecked(setting.isRepeatQuestion());
        switchAppSound.setChecked(setting.isAppSound());
        switchCircleSound.setChecked(setting.isCircleSound());


    }

    private void init() {
        setting = new Setting(this);

        selectedPhotoAdapter = new SelectedPhotoAdapter(this, setting);
        rvSelectPhoto.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSelectPhoto.setAdapter(selectedPhotoAdapter);


    }

    private void findViews() {

        rvSelectPhoto = findViewById(R.id.rv_select_photo);

        switchDefaultQuestion = findViewById(R.id.switch_default);
        switchMYQuestion = findViewById(R.id.switch_my);
        switchRepeatQuestion = findViewById(R.id.switch_repeat_question);
        switchAppSound = findViewById(R.id.switch_app_sound);
        switchCircleSound = findViewById(R.id.switch_circle_cound);

    }


    public void switchOnClick() {


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
        setting.updateSetting(this,setting);

    }

    @Override
    protected void onStop() {
        super.onStop();

        Intent returnIntent = new Intent();

        returnIntent.putExtra("position",setting.getPosition());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }
}