package com.example.truthordare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    final int DURATION = 2000;

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
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        },DURATION);


    }

    private void configuration() {

        Display displaySize = getWindowManager().getDefaultDisplay();
        Point size=new Point();
        displaySize.getSize(size);

        int width=size.x*60/100;
        ivSplash.getLayoutParams().width=width;
        ivSplash.getLayoutParams().height=width;


    }

    private void findViews() {

        ivSplash=findViewById(R.id.iv_splash);
    }
}