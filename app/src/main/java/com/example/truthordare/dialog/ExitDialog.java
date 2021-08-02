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
    TextView noTv;
    TextView yesTv;
    Context context;

    public ExitDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_exit);
        this.context=context;
        findViews();
        setViewSize();
        configuration();
    }

    private void configuration() {

        noTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


        yesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                ((Activity) context).finish();
            }
        });


    }


    private void setViewSize() {

        clExit.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;


    }

    private void findViews() {
        clExit = findViewById(R.id.cl_exit);
        noTv = findViewById(R.id.cancle_btn);
        yesTv = findViewById(R.id.ok_btn);

    }
}






