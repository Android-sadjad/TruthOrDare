package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MyIntent;
import com.example.truthordare.classes.MyTapsell;
import com.example.truthordare.classes.MytapsellBanner;
import com.example.truthordare.dialog.AboutUsDialog;
import com.example.truthordare.dialog.ExitDialog;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Questions;
import com.example.truthordare.dialog.StartDialog;
import com.example.truthordare.fragment.TabFragment;
import com.example.truthordare.fragment.StartGameFragment;
import com.example.truthordare.interfaces.CallBackPlayerList;
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

    StartGameFragment startGameFragment;
    TabFragment defaultQuestionFragment;
    TabFragment myQuestionFragment;

    ImageView ivMenu;

    DrawerLayout drawerLayout;
    NavigationView navigationView;


    Questions questions;

    AboutUsDialog aboutUsDialog;
    ExitDialog exitDialog;

    Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initTapsell();
        findViews();
        init();
        setViewSize();
        configuration();
        if (setting.isAppSound()) {

            MyMediaPlayer.mpMainSound.start();


        }


        RelativeLayout relativeLayout = findViewById(R.id.standardBanner);


        MyTapsell.showStandardBanner(MainActivity.this, MyConstant.STANDARD_BANNER_HOME_PAGE, relativeLayout);


    }

    private void initTapsell() {

        TapsellPlus.initialize(this, MyConstant.TAPSELL_KEY, new TapsellPlusInitListener() {
            @Override
            public void onInitializeSuccess(AdNetworks adNetworks) {

            }

            @Override
            public void onInitializeFailed(AdNetworks adNetworks,
                                           AdNetworkError adNetworkError) {
            }
        });
        TapsellPlus.setGDPRConsent(this, true);
    }

    public void findViews() {

        ivMenu = findViewById(R.id.iv_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
    }

    private void init() {

        setting = new Setting(MainActivity.this);
        MyMediaPlayer.createMediaPlayer(MainActivity.this);
        MyMediaPlayer.createButtonSound(MainActivity.this);
        questions = new Questions(this);

        myQuestionFragment = new TabFragment(MyConstant.MY_LIST);
        defaultQuestionFragment = new TabFragment(MyConstant.DEFAULT_LIST);

        screenWidth = MyConstant.getScreenWidth();
        screenHeight = MyConstant.getScreenHeight();

        aboutUsDialog=new AboutUsDialog(MainActivity.this);
        exitDialog=new ExitDialog(MainActivity.this);
    }

    public void setViewSize() {

        ivMenu.getLayoutParams().height = screenWidth * 13 / 100;
        ivMenu.getLayoutParams().width = screenWidth * 13 / 100;

    }

    public void configuration() {


    }

    private void showStartDialog() {

        StartDialog startDialog = new StartDialog(MainActivity.this, new CallBackPlayerList() {
            @Override
            public void getPlayerList(ArrayList<String> playerName) {

                startGameFragment = new StartGameFragment(playerName);
                loadFragment(startGameFragment);
            }
        });

        startDialog.show();
    }

    public void loadFragment(Fragment fragment) {

        int flId;

        if (fragment == startGameFragment)
            flId = R.id.fl_fragment_container;
        else
            flId = R.id.fl_fragment_full_screen;


        getSupportFragmentManager().beginTransaction()
                .replace(flId, fragment)
                .addToBackStack(null).commit();

    }

    public void onClick(View view) {

        setting = new Setting(MainActivity.this);
        if (setting.isButtonSound()) {

            MyMediaPlayer.mpBtnSound.start();
        }


        switch (view.getId()) {

            case R.id.tv_show_start_dialog:

                showStartDialog();
                break;

            case R.id.tv_my_question:

                loadFragment(myQuestionFragment);
                break;

            case R.id.tv_default_questions:

                loadFragment(defaultQuestionFragment);
                break;

            case R.id.tv_hemayat:

                MytapsellBanner.showInterstitialAd(MainActivity.this, MyConstant.interstitial_BANNER);

                break;
            case R.id.tv_comment:
                MyIntent.commentIntent(MainActivity.this);


                break;

            case R.id.tv_setting:

                startActivity(new Intent(MainActivity.this, SettingActivity.class));

                break;
            case R.id.tv_exit:
exitDialog.show();
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
                MytapsellBanner.showInterstitialAd(MainActivity.this, MyConstant.interstitial_BANNER);

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

    @Override
    protected void onPause() {
        super.onPause();
        if (MyMediaPlayer.mpMainSound.isPlaying())
            MyMediaPlayer.mpMainSound.pause();
    }

    private void closeFragment() {
while (getSupportFragmentManager().getBackStackEntryCount()>0){

    onBackPressed();
}

    }
}