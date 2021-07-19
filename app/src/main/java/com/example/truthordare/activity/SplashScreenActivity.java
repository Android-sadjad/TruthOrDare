package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.R;

public class SplashScreenActivity extends AppCompatActivity {

    final int DURATION = 500;

    ImageView ivSplashScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        findViews();
        configuration();
        goToMainActivity();

    }

    private void findViews() {

        ivSplashScreen = findViewById(R.id.iv_splash_screen);
    }

    private void configuration() {

        int width = MyConstant.getScreenWidth() * 60 / 100;
        ivSplashScreen.getLayoutParams().width = width;
        ivSplashScreen.getLayoutParams().height = width;
    }

    private void goToMainActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, DURATION);
    }
}