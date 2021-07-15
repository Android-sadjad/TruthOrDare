package com.example.truthordare;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StartDialog extends Dialog {
    ConstraintLayout clStartDialog;
    Slider sbPlayerNumber;
    ConstraintLayout[] clPlayerNames;
    TextInputEditText[] tiePlayerNames;

    TextView tvStartGame;

    Context context;
    MyCallBack myCallBack;
    public StartDialog(Context context,MyCallBack myCallBack) {
        super(context);
        setContentView(R.layout.dialog_start);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.context=context;
        this.myCallBack=myCallBack;

        findViews();
        setSize();
        configuraion();

    }

    private void configuraion() {

        sbPlayerNumber.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {

                for(int i=2;i<value;i++){
                     clPlayerNames[i].setVisibility(View.VISIBLE);
                }

                for(int i = (int) value; i<9; i++){
                    clPlayerNames[i].setVisibility(View.GONE);
                }
            }
        });

        tvStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> playerNameList=new ArrayList<>();


                for (int i=0;i<9;i++){

                    if(clPlayerNames[i].getVisibility()==View.VISIBLE){

                        if(tiePlayerNames[i].getText().length()==0){

                            Toast.makeText(context, "لطفا همه ی اسامی را وارد کنید", Toast.LENGTH_SHORT).show();
                        }
                        else
                        playerNameList.add(tiePlayerNames[i].getText().toString());


                    }
                }

                myCallBack.callBackPlayerList(playerNameList);
            }
        });

    }

    private void findViews() {


        clStartDialog = findViewById(R.id.cl_start_dialog);
        sbPlayerNumber = findViewById(R.id.sb_player_number);

        clPlayerNames=new ConstraintLayout[9];
        tiePlayerNames=new TextInputEditText[9];

        clPlayerNames[0] = findViewById(R.id.cl_player_name_1);
        clPlayerNames[1] = findViewById(R.id.cl_player_name_2);
        clPlayerNames[2] = findViewById(R.id.cl_player_name_3);
        clPlayerNames[3] = findViewById(R.id.cl_player_name_4);
        clPlayerNames[4] = findViewById(R.id.cl_player_name_5);
        clPlayerNames[5] = findViewById(R.id.cl_player_name_6);
        clPlayerNames[6] = findViewById(R.id.cl_player_name_7);
        clPlayerNames[7] = findViewById(R.id.cl_player_name_8);
        clPlayerNames[8] = findViewById(R.id.cl_player_name_9);

        tiePlayerNames[0]=findViewById(R.id.et_player_1);
        tiePlayerNames[1]=findViewById(R.id.et_player_2);
        tiePlayerNames[2]=findViewById(R.id.et_player_3);
        tiePlayerNames[3]=findViewById(R.id.et_player_4);
        tiePlayerNames[4]=findViewById(R.id.et_player_5);
        tiePlayerNames[5]=findViewById(R.id.et_player_6);
        tiePlayerNames[6]=findViewById(R.id.et_player_7);
        tiePlayerNames[7]=findViewById(R.id.et_player_8);
        tiePlayerNames[8]=findViewById(R.id.et_player_9);



        tvStartGame=findViewById(R.id.tv_start_game);

    }

    public void setSize() {
        clStartDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
        clStartDialog.getLayoutParams().height = MyConstant.getScreenHeight() * 80 / 100;

    }

}
