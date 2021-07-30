package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivSplashScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        findViews();
        setViewsSize();
        goToMainActivity();

    }

    private void findViews() {

        ivSplashScreen = findViewById(R.id.iv_splash_screen);
    }

    private void setViewsSize() {

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
        }, MyConstant.SPLASH_SCREEN_DURATION);
    }
}