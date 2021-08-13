package com.example.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.activity.MainActivity;
import com.example.truthordare.classes.MyConstant;

public class ExitDialog extends Dialog {

    ConstraintLayout clExit;
    TextView tvNO;
    TextView tvYes;
    Activity activity;

    public ExitDialog(@NonNull Activity activity) {
        super(activity);
        setContentView(R.layout.dialog_exit);
        this.activity=activity;
        findViews();
        setViewSize();
        configuration();
    }

    private void configuration() {

        tvNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.finish();
            }
        });


    }


    private void setViewSize() {

        clExit.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;


    }

    private void findViews() {
        clExit = findViewById(R.id.cl_exit);
        tvNO = findViewById(R.id.cancle_btn);
        tvYes = findViewById(R.id.ok_btn);

    }
}






