package com.example.truthordare;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    StartGameFragment startGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        startGameFragment = new StartGameFragment();
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_show_start_dialog:

                loadFragment(startGameFragment);

                break;

            case R.id.tv_setting:

                break;

            case R.id.tv_questions_list:

                break;

            case R.id.tv_hemayat:

                break;

            case R.id.tv_comment:

                break;

            case R.id.tv_exit:

                break;

        }
    }

    public void loadFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_fragment_container, fragment)
                .addToBackStack(null).commit();
    }
}