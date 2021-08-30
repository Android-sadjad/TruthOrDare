package com.example.truthordare.activity;

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
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MyIntent;
import com.example.truthordare.classes.MyTapsell;
import com.example.truthordare.dialog.AboutUsDialog;
import com.example.truthordare.dialog.ExitDialog;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;

import ir.tapsell.plus.TapsellPlusBannerType;

public class MainActivity extends AppCompatActivity {

    int screenWidth;
    int screenHeight;

    RelativeLayout rlAdvertising;
    ImageView ivStartGame;
    DrawerLayout drawerLayout;

    TextView tvMyQuestion;
    TextView tvDefaultQuestion;
    TextView tvHemayat;
    TextView tvComment;

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

        tvMyQuestion = findViewById(R.id.tv_my_question);
        tvDefaultQuestion = findViewById(R.id.tv_default_questions);
        tvHemayat = findViewById(R.id.tv_hemayat);
        tvComment = findViewById(R.id.tv_comment);
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


        Animation fadeInAnimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in_animation);


        TextView textView=findViewById(R.id.tv_toolbar_title);
        ConstraintLayout clMainMenuItems=findViewById(R.id.cl_main_menu_items);
        View toolBarView=findViewById(R.id.lay);
        clMainMenuItems.startAnimation(fadeInAnimation);
        toolBarView.startAnimation(fadeInAnimation);




        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clMainMenuItems.startAnimation(fadeInAnimation);
                toolBarView.startAnimation(fadeInAnimation);

            }
        });
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