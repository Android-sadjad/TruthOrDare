package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivSplashScreen;
    TextView tvTeamName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        findViews();
        setViewsSize();
        setStartAnimation();
        goToMainActivity();




    }

    private void setStartAnimation() {

        Animation translateAnmimation= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.translate_right_animation);
        Animation bounceAnmimation= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.bounce_animation);
        tvTeamName.startAnimation(translateAnmimation);
ivSplashScreen.startAnimation(bounceAnmimation);


    }

    private void findViews() {

        ivSplashScreen = findViewById(R.id.iv_splash_screen);
        tvTeamName = findViewById(R.id.tv_team_name);
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