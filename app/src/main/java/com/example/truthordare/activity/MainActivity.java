package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MyIntent;
import com.example.truthordare.classes.MyTapsell;
import com.example.truthordare.dialog.AboutUsDialog;
import com.example.truthordare.dialog.ExitDialog;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;
import com.google.android.material.navigation.NavigationView;

import ir.tapsell.plus.TapsellPlusBannerType;

public class MainActivity extends AppCompatActivity {

    int screenWidth;
    int screenHeight;

    RelativeLayout rlAdvertising;
    ImageView ivStartGame;
    DrawerLayout drawerLayout;

    Setting setting;

    AboutUsDialog aboutUsDialog;
    ExitDialog exitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        init();
        setViewSize();
        startAnimation();

        MyTapsell.showStandardBanner(MainActivity.this, MyConstant.STANDARD_BANNER_HOME_PAGE, rlAdvertising, TapsellPlusBannerType.BANNER_320x50);





    }

    @Override
    protected void onResume() {
        super.onResume();

        setting = new Setting(MainActivity.this);
        if (setting.isAppSound() && !MyMediaPlayer.mpMainSound.isPlaying()) {

            MyMediaPlayer.mpMainSound.start();
        }
    }


    public void findViews() {

        rlAdvertising = findViewById(R.id.standardBanner);

        ivStartGame = findViewById(R.id.tv_show_start_dialog);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    private void init() {


        screenWidth = MyConstant.getScreenWidth();
        screenHeight = MyConstant.getScreenHeight();

        aboutUsDialog = new AboutUsDialog(MainActivity.this);
        exitDialog = new ExitDialog(MainActivity.this);
    }

    public void setViewSize() {

        ivStartGame.getLayoutParams().width = screenWidth * 50 / 100;
        ivStartGame.getLayoutParams().height = screenWidth * 50 / 100;

    }

    private void startAnimation() {

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360
                , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(30000);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        ivStartGame.startAnimation(rotateAnimation);
    }


    public void onClick(View view) {

        if (setting.isButtonSound()) {
            MyMediaPlayer.mpBtnSound.start();
        }


        switch (view.getId()) {

            case R.id.tv_show_start_dialog:

                startActivity(new Intent(MainActivity.this, StartGameActivity.class));
                break;

            case R.id.tv_my_question:

                openQuestionActivity(MyConstant.MY_LIST);

                break;

            case R.id.tv_default_questions:

                openQuestionActivity(MyConstant.DEFAULT_LIST);
                break;

            case R.id.tv_hemayat:
                MyTapsell.showInterstitialAd(MainActivity.this, MyConstant.interstitial_BANNER, null);
                break;

            case R.id.tv_comment:
                MyIntent.commentIntent(MainActivity.this);
                break;

            case R.id.iv_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;

            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;

        }
    }

    public void navItemsOnClick(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.nav_my_question:

            openQuestionActivity(MyConstant.MY_LIST);
                break;

            case R.id.nav_default_question:
                openQuestionActivity(MyConstant.DEFAULT_LIST);

                break;

            case R.id.nav_hemayat:
                MyTapsell.showInterstitialAd(MainActivity.this, MyConstant.interstitial_BANNER, null);

                break;
            case R.id.nav_comment:

                MyIntent.commentIntent(MainActivity.this);
                break;


            case R.id.nav_exit:
                exitDialog.show();
                break;

            case R.id.nav_share_app:
                MyIntent.shareAppIntent(MainActivity.this);
                break;

            case R.id.nav_about_us:
                aboutUsDialog.show();
                break;
        }

        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    private void openQuestionActivity(String listName){

        Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
        intent.putExtra(MyConstant.LIST_TYPE,listName);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        exitDialog.show();
    }
}