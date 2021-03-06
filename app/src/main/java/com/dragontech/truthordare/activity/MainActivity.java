package com.dragontech.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.MyIntent;
import com.dragontech.truthordare.classes.MyTapsell;
import com.dragontech.truthordare.classes.UseFullMethod;
import com.dragontech.truthordare.dialog.AboutUsDialog;
import com.dragontech.truthordare.dialog.CommentDialog;
import com.dragontech.truthordare.dialog.ExitDialog;
import com.dragontech.truthordare.model.MyMediaPlayer;
import com.dragontech.truthordare.model.Setting;

import ir.tapsell.plus.TapsellPlusBannerType;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private RelativeLayout rlAdvertising;

    private ImageView ivStartGame;

    private TextView tvMyQuestion;
    private TextView tvDefaultQuestion;
    private TextView tvHemayat;
    private TextView tvComment;

    private Setting setting;

    private AboutUsDialog aboutUsDialog;
    private ExitDialog exitDialog;
    private CommentDialog commentDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        init();
        startAnimation();
        showStandardAdvertising();


    }

    @Override
    protected void onResume() {
        super.onResume();

        setting = new Setting(MainActivity.this);
        if (setting.isAppSound() && !MyMediaPlayer.mpAppSound.isPlaying()) {

            MyMediaPlayer.mpAppSound.start();

        }
    }


    public void findViews() {

        drawerLayout = findViewById(R.id.drawer_layout);
        rlAdvertising = findViewById(R.id.standardBanner);

        ivStartGame = findViewById(R.id.tv_start_game_activity);

        tvMyQuestion = findViewById(R.id.tv_my_question);
        tvDefaultQuestion = findViewById(R.id.tv_default_questions);
        tvHemayat = findViewById(R.id.tv_hemayat);
        tvComment = findViewById(R.id.tv_comment);
    }

    private void init() {

        aboutUsDialog = new AboutUsDialog(MainActivity.this);
        exitDialog = new ExitDialog(MainActivity.this);
        commentDialog = new CommentDialog(MainActivity.this);
    }

    private void startAnimation() {

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360
                , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


        rotateAnimation.setDuration(MyConstant.ROTATE_ANIM_DURATION);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        ivStartGame.startAnimation(rotateAnimation);


        Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in_animation);

        ConstraintLayout clMainMenuItems = findViewById(R.id.cl_main_menu_items);
        View toolBarView = findViewById(R.id.lay);
        clMainMenuItems.startAnimation(fadeInAnimation);
        toolBarView.startAnimation(fadeInAnimation);

    }

    private void showStandardAdvertising() {

        MyTapsell.showStandardBanner(MainActivity.this, MyConstant.STANDARD_BANNER, rlAdvertising, TapsellPlusBannerType.BANNER_320x50);
    }

    private void showInterstitialAdvertising() {
        if (UseFullMethod.isNetworkAvailable(this))
            MyTapsell.showInterstitialAd(MainActivity.this, MyConstant.INTERSTITIAL_BANNER, null);
    }


    public void onClick(View view) {

        if (setting.isButtonSound()) {
            MyMediaPlayer.mpBtnSound.start();
        }

        switch (view.getId()) {

            case R.id.tv_start_game_activity:
                startActivity(new Intent(MainActivity.this, StartGameActivity.class));
                break;

            case R.id.tv_my_question:
                openQuestionActivity(MyConstant.MY_LIST);
                break;

            case R.id.tv_default_questions:
                openQuestionActivity(MyConstant.DEFAULT_LIST);
                break;

            case R.id.tv_hemayat:
                showInterstitialAdvertising();
                break;

            case R.id.tv_comment:
                commentDialog.show();

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
                showInterstitialAdvertising();
                break;

            case R.id.nav_comment:
                commentDialog.show();
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

            case R.id.nav_other_app:
                if (UseFullMethod.isNetworkAvailable(this))
                    MyIntent.otherAppIntent(MainActivity.this);
                break;
        }

        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    private void openQuestionActivity(String listName) {

        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra(MyConstant.LIST_TYPE, listName);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        exitDialog.show();
    }
}