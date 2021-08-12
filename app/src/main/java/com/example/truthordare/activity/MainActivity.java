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
import com.example.truthordare.fragment.StartFragment;
import com.example.truthordare.fragment.StartGameFragment;
import com.example.truthordare.fragment.TabFragment;
import com.example.truthordare.interfaces.CallBackPlayerList;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;

public class MainActivity extends AppCompatActivity {

    int screenWidth;
    int screenHeight;

    StartFragment startFragment;

    TabFragment defaultQuestionFragment;
    TabFragment myQuestionFragment;


    ImageView ivMenu;


    DrawerLayout drawerLayout;
    NavigationView navigationView;

    View viewOval;
    ImageView ivStartGame;


    Questions questions;

    AboutUsDialog aboutUsDialog;
    ExitDialog exitDialog;

    Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViews();
        init();
        setViewSize();


        RelativeLayout relativeLayout = findViewById(R.id.standardBanner);


        MyTapsell.showStandardBanner(MainActivity.this, MyConstant.STANDARD_BANNER_HOME_PAGE, relativeLayout);


        RotateAnimation rotateAnimation = new RotateAnimation(0, 360
                , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(30000);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        ivStartGame.startAnimation(rotateAnimation);

    }

    @Override
    protected void onResume() {
        super.onResume();

        setting = new Setting(MainActivity.this);
        if (setting.isAppSound()&&!MyMediaPlayer.mpMainSound.isPlaying()) {

            MyMediaPlayer.mpMainSound.start();
        }
    }



    public void findViews() {

        ivMenu = findViewById(R.id.iv_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
//        viewOval = findViewById(R.id.view_oval);
        ivStartGame = findViewById(R.id.tv_show_start_dialog);
    }

    private void init() {


        questions = new Questions(this);

        myQuestionFragment = new TabFragment(MyConstant.MY_LIST);
        defaultQuestionFragment = new TabFragment(MyConstant.DEFAULT_LIST);
        startFragment=new StartFragment();



        screenWidth = MyConstant.getScreenWidth();
        screenHeight = MyConstant.getScreenHeight();

        aboutUsDialog = new AboutUsDialog(MainActivity.this);
        exitDialog = new ExitDialog(MainActivity.this);
    }

    public void setViewSize() {

        ivMenu.getLayoutParams().height = screenWidth * 13 / 100;
        ivMenu.getLayoutParams().width = screenWidth * 13 / 100;

//        viewOval.getLayoutParams().height = screenHeight * 30 / 100;

        ivStartGame.getLayoutParams().width = screenWidth * 50 / 100;
        ivStartGame.getLayoutParams().height = screenWidth * 50 / 100;

    }



    public void loadFragment(Fragment fragment) {

        int flId;

        if (fragment == startFragment)
            flId = R.id.fl_fragment_container;
        else
            flId = R.id.fl_fragment_full_screen;


        getSupportFragmentManager().beginTransaction()
                .replace(flId, fragment)
                .addToBackStack(null).commit();

    }

    public void onClick(View view) {


        if (setting.isButtonSound()) {

            MyMediaPlayer.mpBtnSound.start();
        }


        switch (view.getId()) {

            case R.id.tv_show_start_dialog:

               loadFragment(startFragment);
                break;

            case R.id.tv_my_question:

                loadFragment(myQuestionFragment);
                break;

            case R.id.tv_default_questions:

                loadFragment(defaultQuestionFragment);
                break;

            case R.id.tv_hemayat:

                MyTapsell.showInterstitialAd(MainActivity.this, MyConstant.interstitial_BANNER, null);
                break;
            case R.id.tv_comment:
                MyIntent.commentIntent(MainActivity.this);


                break;

            case R.id.tv_setting:

                startActivityForResult(new Intent(MainActivity.this, SettingActivity.class),MyConstant.REQUEST_CODE);

                break;


            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;

        }
    }

    public void navItemsOnClick(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.nav_my_question:
                loadFragment(myQuestionFragment);

                break;

            case R.id.nav_default_question:

                loadFragment(defaultQuestionFragment);
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
            case R.id.nav_home_page:
                closeFragment();
                break;

            case R.id.nav_about_us:
                aboutUsDialog.show();
                break;
        }

        drawerLayout.closeDrawer(Gravity.RIGHT);
    }


    private void closeFragment() {
        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            onBackPressed();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.i("aaaa","main");
        Toast.makeText(this, "asdgfdjsgklvngggggggggggggggggggggggggg", Toast.LENGTH_SHORT).show();

    }

}