package com.example.truthordare;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.slider.Slider;

public class StartDialog extends Dialog {
    ConstraintLayout clStartDialog;
    Slider sbPlayerNumber;
    ConstraintLayout[] clPlayerNames;


    public StartDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_start);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        findViews();
        setSize();
        configuraion();

    }

    private void configuraion() {

        sbPlayerNumber.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {

                for(int i=2;i<value;i++){

                    Toast.makeText(getContext(), String.valueOf(value), Toast.LENGTH_SHORT).show();
                     clPlayerNames[i].setVisibility(View.VISIBLE);
                }

                for(int i = (int) value; i<9; i++){

                    clPlayerNames[i].setVisibility(View.GONE);

                }
            }
        });





    }

    private void findViews() {


        clStartDialog = findViewById(R.id.cl_start_dialog);
        sbPlayerNumber = findViewById(R.id.sb_player_number);

        clPlayerNames=new ConstraintLayout[9];

        clPlayerNames[0] = findViewById(R.id.cl_player_name_1);
        clPlayerNames[1] = findViewById(R.id.cl_player_name_2);
        clPlayerNames[2] = findViewById(R.id.cl_player_name_3);
        clPlayerNames[3] = findViewById(R.id.cl_player_name_4);
        clPlayerNames[4] = findViewById(R.id.cl_player_name_5);
        clPlayerNames[5] = findViewById(R.id.cl_player_name_6);
        clPlayerNames[6] = findViewById(R.id.cl_player_name_7);
        clPlayerNames[7] = findViewById(R.id.cl_player_name_8);
        clPlayerNames[8] = findViewById(R.id.cl_player_name_9);

    }

    public void setSize() {
        clStartDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
        clStartDialog.getLayoutParams().height = MyConstant.getScreenHeight() * 80 / 100;

    }

}
