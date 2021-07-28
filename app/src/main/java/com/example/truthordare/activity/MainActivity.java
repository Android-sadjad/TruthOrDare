package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.truthordare.interfaces.MyCallBack;
import com.example.truthordare.fragment.MyQuestionFragment;
import com.example.truthordare.fragment.TabFragment;
import com.example.truthordare.R;
import com.example.truthordare.dialog.StartDialog;
import com.example.truthordare.fragment.StartGameFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    StartGameFragment startGameFragment;
    TabFragment tabFragment;
    MyQuestionFragment myQuestionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        tabFragment = new TabFragment();
        myQuestionFragment=new MyQuestionFragment();

    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_show_start_dialog:

                showStartDialog();
                break;

            case R.id.tv_my_question:
             loadFragment(myQuestionFragment);

                break;

            case R.id.tv_questions_list:

                loadFragment(tabFragment);
                break;

            case R.id.tv_hemayat:

                break;
            case R.id.tv_comment:

                break;

            case R.id.tv_setting:

                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                break;
            case R.id.tv_exit:

                break;

        }
    }




    private void showStartDialog() {

        StartDialog startDialog = new StartDialog(MainActivity.this, new MyCallBack() {
            @Override
            public void callBackPlayerList(ArrayList<String> playerName) {

                startGameFragment = new StartGameFragment(playerName);
                loadFragment(startGameFragment);
            }

            @Override
            public void callBackAddList(String key, String myQuestion) {

            }
        });

        startDialog.show();
    }


    public void loadFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment_container, fragment)
                .addToBackStack(null).commit();
    }


}