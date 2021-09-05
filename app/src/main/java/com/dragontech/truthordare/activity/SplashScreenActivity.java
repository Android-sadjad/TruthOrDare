package com.dragontech.truthordare.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.Typewriter;
import com.dragontech.truthordare.classes.UseFullMethod;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivProgramIcon;
    Typewriter typewriterTeamName;
    TextView tvCodeVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        findViews();
        setViewsSize();
        startAnimation();
        setText();
        goToMainActivity();
    }

    private void findViews() {

        ivProgramIcon = findViewById(R.id.iv_program_icon);
        typewriterTeamName = findViewById(R.id.typewriter_team_name);
        tvCodeVersion=findViewById(R.id.tv_code_version);
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

    private void setText(){

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = getString(R.string.version)+pInfo.versionName;
            tvCodeVersion.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tvCodeVersion.setText(R.string.version_code);
        }

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