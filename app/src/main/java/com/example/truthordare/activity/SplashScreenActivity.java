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
import com.example.truthordare.classes.Typewriter;
import com.example.truthordare.classes.UseFullMethod;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivProgramIcon;
    Typewriter typewriterTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        findViews();
        setViewsSize();
        startAnimation();
        goToMainActivity();
    }

    private void findViews() {

        ivProgramIcon = findViewById(R.id.iv_program_icon);
        typewriterTeamName = findViewById(R.id.typewriter_team_name);
    }

    private void startAnimation() {


        Animation bounceAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.bounce_animation);
        ivProgramIcon.startAnimation(bounceAnimation);


        typewriterTeamName.setCharacterDelay(MyConstant.TYPE_WRITER_ANIM_DELAY);
        typewriterTeamName.animateText(getString(R.string.developer_team_name));

    }

    private void setViewsSize() {

        int width = UseFullMethod.getScreenWidth() * 60 / 100;
        ivProgramIcon.getLayoutParams().width = width;
        ivProgramIcon.getLayoutParams().height = width;

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