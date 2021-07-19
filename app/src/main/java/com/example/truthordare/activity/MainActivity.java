package com.example.truthordare.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.truthordare.classes.MyCallBack;
import com.example.truthordare.fragment.QuestionListFragment;
import com.example.truthordare.R;
import com.example.truthordare.dialog.StartDialog;
import com.example.truthordare.fragment.StartGameFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    StartGameFragment startGameFragment;
    QuestionListFragment questionListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        questionListFragment = new QuestionListFragment();
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_show_start_dialog:

                showStartDialog();
                break;

            case R.id.tv_setting:

                break;

            case R.id.tv_questions_list:

                loadFragment(questionListFragment);
                break;

            case R.id.tv_hemayat:

                break;
            case R.id.tv_comment:

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
        });

        startDialog.show();
    }


    public void loadFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_fragment_container, fragment)
                .addToBackStack(null).commit();
    }
}