package com.example.truthordare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    CardView cvPlayer;

    ConstraintLayout[] clPlayerNames;
    TextInputEditText[] tiePlayerNames;

    TextView tvStartGame;
    TextView tvPlayerNumber;

    ImageView ivRightArrow;
    ImageView ivLeftArrow;


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


    private void init() {

        clPlayerNames = new ConstraintLayout[9];
        tiePlayerNames = new TextInputEditText[9];

        setting = new Setting(this);
    }

    private void findViews() {

        ivRightArrow = findViewById(R.id.iv_right_arrow);
        ivLeftArrow =findViewById(R.id.iv_left_arrow);
        tvPlayerNumber = findViewById(R.id.tv_player_number);


        for (int i = 0; i < 9; i++) {

            int id = getResources().getIdentifier("cl_player_name_" + (i + 1), "id", getPackageName());
            clPlayerNames[i] = findViewById(id);

            id = getResources().getIdentifier("et_player_" + (i + 1), "id", getPackageName());
            tiePlayerNames[i] = findViewById(id);

        }

        tvStartGame = findViewById(R.id.tv_start_game);
        cvPlayer =findViewById(R.id.cv_start);
    }




    private void configuration() {


        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter >= 9) {
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


                if (counter <= 2) {
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

                if (setting.isButtonSound()) {
                    MyMediaPlayer.mpBtnSound.start();
                }


                boolean fullAllEditText = true;
                ArrayList<String> playerNameList = new ArrayList<>();


                for (int i = 0; i < 9; i++) {

                    if (clPlayerNames[i].getVisibility() == View.VISIBLE) {

                        if (tiePlayerNames[i].getText().length() == 0) {

                            fullAllEditText = false;
                            tiePlayerNames[i].setError(getString(R.string.please_enter_this_field));
                            break;
                        } else
                            playerNameList.add(tiePlayerNames[i].getText().toString());


                    }
                }
                if (fullAllEditText == true) {

                    // callBackPlayerList.getPlayerList(playerNameList);
                    ////////////////////////////////////////////////////////
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.fl_game_container, new StartGameFragment(playerNameList)).commit();


                    Intent intent=new Intent(StartGameActivity.this,GameActivity.class);
                    intent.putExtra(MyConstant.PLAYER_NAME_LIST,playerNameList);
                    startActivity(intent);
                }
            }


        });

    }

    private void setVisibility() {
        for (int i = 2; i < counter; i++) {
            clPlayerNames[i].setVisibility(View.VISIBLE);
        }

        for (int i = (int) counter; i < 9; i++) {
            clPlayerNames[i].setVisibility(View.GONE);

        }


    }

}