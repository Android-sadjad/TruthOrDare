package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.interfaces.MyCallBack;
import com.example.truthordare.fragment.MyQuestionFragment;
import com.example.truthordare.fragment.TabFragment;
import com.example.truthordare.R;
import com.example.truthordare.dialog.StartDialog;
import com.example.truthordare.fragment.StartGameFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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

        findViews();
        init();
        setViewSize();
        configuraion();

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment_container, fragment)
                .addToBackStack(null).commit();
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