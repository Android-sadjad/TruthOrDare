package com.example.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    final int MAX_PLAYER_NUMBER = 9;
    final int MIN_PLAYER_NUMBER = 2;
    CardView cvPlayer;
    ScrollView svPlayerName;
    ConstraintLayout[] clPlayerNames;
    TextInputEditText[] tiePlayerNames;
    TextView tvPlayerNumber;
    TextView tvStartGame;
    ImageView ivRightArrow;
    ImageView ivLeftArrow;
    Animation scaleAnimation;
    Animation scaleXAnimation;
    Setting setting;
    int counter = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        init();
        findViews();
        configuration();

    }

    @Override
    protected void onResume() {
        super.onResume();

        setting = new Setting(StartGameActivity.this);
        if (setting.isAppSound() && !MyMediaPlayer.mpAppSound.isPlaying()) {

            MyMediaPlayer.mpAppSound.start();
        }
    }

    private void init() {

        clPlayerNames = new ConstraintLayout[MAX_PLAYER_NUMBER];
        tiePlayerNames = new TextInputEditText[MAX_PLAYER_NUMBER];

        scaleAnimation = AnimationUtils.loadAnimation(StartGameActivity.this, R.anim.scale_animation);
        scaleXAnimation = AnimationUtils.loadAnimation(StartGameActivity.this, R.anim.scale_x_animation);

        setting = new Setting(this);
    }

    private void findViews() {

        svPlayerName = findViewById(R.id.sv_player_name);
        ivRightArrow = findViewById(R.id.iv_right_arrow);
        ivLeftArrow = findViewById(R.id.iv_left_arrow);
        tvPlayerNumber = findViewById(R.id.tv_player_number);


        for (int i = 0; i < 9; i++) {

            int id = getResources().getIdentifier("cl_player_name_" + (i + 1), "id", getPackageName());
            clPlayerNames[i] = findViewById(id);

            id = getResources().getIdentifier("et_player_" + (i + 1), "id", getPackageName());
            tiePlayerNames[i] = findViewById(id);

        }

        tvStartGame = findViewById(R.id.tv_start_game);
        cvPlayer = findViewById(R.id.cv_start);
    }


    private void configuration() {

        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivRightArrow.startAnimation(scaleAnimation);

                if (counter >= MAX_PLAYER_NUMBER) {
                    Toast.makeText(StartGameActivity.this, getString(R.string.max_number), Toast.LENGTH_SHORT).show();
                    return;
                }

                tvPlayerNumber.setText(String.valueOf(++counter));
                setVisibility();

            }
        });

        ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivLeftArrow.startAnimation(scaleAnimation);

                ivLeftArrow.setSoundEffectsEnabled(true);

                if (counter <= MIN_PLAYER_NUMBER) {
                    Toast.makeText(StartGameActivity.this, getString(R.string.min_number), Toast.LENGTH_SHORT).show();
                    return;
                }

                tvPlayerNumber.setText(String.valueOf(--counter));
                setVisibility();
            }
        });

        tvStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvStartGame.startAnimation(scaleXAnimation);


                if (setting.isButtonSound()) {
                    MyMediaPlayer.mpBtnSound.start();
                }

                boolean fullAllEditText = true;
                ArrayList<String> playerNameList = new ArrayList<>();


                for (int i = 0; i < 9; i++) {

                    if (clPlayerNames[i].getVisibility() == View.VISIBLE) {

                        if (tiePlayerNames[i].getText().toString().trim().length() == 0) {

                            fullAllEditText = false;
                            tiePlayerNames[i].setError(getString(R.string.please_enter_this_field));
                            svPlayerName.smoothScrollTo(0, clPlayerNames[i].getTop());
                            break;
                        } else
                            playerNameList.add(tiePlayerNames[i].getText().toString());


                    }
                }
                if (fullAllEditText == true) {

                    Intent intent = new Intent(StartGameActivity.this, GameActivity.class);
                    intent.putExtra(MyConstant.PLAYER_NAME_LIST, playerNameList);
                    startActivityForResult(intent, MyConstant.REQUEST_CODE);
                }
            }


        });

    }

    private void setVisibility() {
        for (int i = MIN_PLAYER_NUMBER; i < counter; i++) {
            clPlayerNames[i].setVisibility(View.VISIBLE);
        }

        for (int i = (int) counter; i < MAX_PLAYER_NUMBER; i++) {
            clPlayerNames[i].setVisibility(View.GONE);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyConstant.FINISH_CODE) {
            finish();
        }
    }
}