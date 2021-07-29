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

import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MyTapsell;
import com.example.truthordare.interfaces.MyCallBack;
import com.example.truthordare.fragment.MyQuestionFragment;
import com.example.truthordare.fragment.TabFragment;
import com.example.truthordare.R;
import com.example.truthordare.dialog.StartDialog;
import com.example.truthordare.fragment.StartGameFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;

public class MainActivity extends AppCompatActivity {

    StartGameFragment startGameFragment;
    TabFragment tabFragment;
    MyQuestionFragment myQuestionFragment;

    ImageView ivMenu;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    int screenWidth;
    int screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTapsell();

        findViews();
        init();
        setViewSize();
        configuraion();

        RelativeLayout relativeLayout=findViewById(R.id.standardBanner);




        MyTapsell.showStandardBanner(MainActivity.this,MyConstant.STANDARD_BANNER_HOME_PAGE,relativeLayout);

    }

    private void initTapsell() {

        TapsellPlus.initialize(this, MyConstant.TAPSELL_KEY,new TapsellPlusInitListener() {
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



    public void findViews(){
        ivMenu = findViewById(R.id.iv_menu);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
    }

    public void setViewSize(){

        ivMenu.getLayoutParams().height = screenWidth * 13 / 100;
        ivMenu.getLayoutParams().width = screenWidth * 13 / 100;

        navigationView.getLayoutParams().width=screenWidth*60/100;
    }

    private void init() {

        tabFragment = new TabFragment();
        myQuestionFragment=new MyQuestionFragment();

        screenWidth= MyConstant.getScreenWidth();
        screenHeight=MyConstant.getScreenHeight();

    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_show_start_dialog:


                showStartDialog();
                break;

            case R.id.tv_my_question:
             loadFragment(myQuestionFragment);

                break;

            case R.id.tv_questions_list:

                loadFragment(tabFragment);
                break;

            case R.id.tv_hemayat:
                MyTapsell.showInterstitialAd(MainActivity.this,MyConstant.interstitial_BANNER);

                break;
            case R.id.tv_comment:

                break;

            case R.id.tv_setting:

                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                break;
            case R.id.tv_exit:

                break;

        }
    }

    public void configuraion(){

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(Gravity.RIGHT);

            }
        });

    }



    private void showStartDialog() {

        StartDialog startDialog = new StartDialog(MainActivity.this, new MyCallBack() {
            @Override
            public void callBackPlayerList(ArrayList<String> playerName) {

                startGameFragment = new StartGameFragment(playerName);
                loadFragment(startGameFragment);
            }

            @Override
            public void callBackAddList(String key, String myQuestion) {

            }
        });

        startDialog.show();
    }


    public void loadFragment(Fragment fragment) {

        if(fragment==startGameFragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_fragment_container, fragment)
                    .addToBackStack(null).commit();
        }
        else { getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment_full_screen, fragment)
                .addToBackStack(null).commit();
        }

    }


    public void onClick(MenuItem item) {
        switch (item.getItemId()) {



            case R.id.nav_my_question:
                loadFragment(myQuestionFragment);

                break;

            case R.id.nav_default_question:

                loadFragment(tabFragment);
                break;

            case R.id.tv_hemayat:

                break;
            case R.id.tv_comment:

                break;


            case R.id.tv_exit:

                break;

        }

        drawerLayout.closeDrawer(Gravity.RIGHT);
    }
}