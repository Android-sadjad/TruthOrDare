package com.example.truthordare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    final int DURATION = 500;

    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        findViews();
        configuration();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, DURATION);
    }

    private void findViews() {

        ivSplash = findViewById(R.id.iv_splash);
    }

    private void configuration() {

        int width = MyConstant.getScreenWidth() * 60 / 100;
        ivSplash.getLayoutParams().width = width;
        ivSplash.getLayoutParams().height = width;
    }
}