package com.example.truthordare.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truthordare.R;
import com.example.truthordare.adapter.SelectedPhotoAdapter;
import com.example.truthordare.classes.MySharedPreferences;
import com.example.truthordare.model.Setting;

public class SettingFragment extends Fragment {


    RecyclerView rvSelectPhoto;
    SelectedPhotoAdapter selectedPhotoAdapter;
    Setting setting;

    Switch switchDefaultQuestion;
    Switch switchMYQuestion;
    Switch switchRepeatQuestion;
    Switch switchAppSound;
    Switch switchCircleSound;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
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
        setting=new Setting(getContext());

        selectedPhotoAdapter=new SelectedPhotoAdapter(getContext(),setting);
        rvSelectPhoto.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSelectPhoto.setAdapter(selectedPhotoAdapter);





    }

    private void findViews(View view) {

        rvSelectPhoto=view.findViewById(R.id.rv_select_photo);

        switchDefaultQuestion=view.findViewById(R.id.switch_default);
        switchMYQuestion=view.findViewById(R.id.switch_my);
        switchRepeatQuestion=view.findViewById(R.id.switch_repeat_question);
        switchAppSound=view.findViewById(R.id.switch_app_sound);
        switchCircleSound=view.findViewById(R.id.switch_circle_cound);

    }



    public void switchOnClick(){


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
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onPause() {
        super.onPause();



        Log.i("puseee","pppakdadshgkl");
        setting.updateSetting(getContext(),setting);

    }
}
