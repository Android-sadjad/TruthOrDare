package com.example.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.activity.MainActivity;
import com.example.truthordare.classes.MyIntent;
import com.example.truthordare.classes.UseFullMethod;

public class CommentDialog extends Dialog {

    ConstraintLayout clComment;
    TextView tvNo;
    TextView tvYes;

    Activity activity;

    public CommentDialog(@NonNull Activity activity) {
        super(activity);
        setContentView(R.layout.dialog_comment);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.activity = activity;
        findViews();
        setViewSize();
        configuration();

    }

    private void configuration() {

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cancel();



            }
        });


        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyIntent.commentIntent(activity);


            }
        });


    }


    private void setViewSize() {

        clComment.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;


    }

    private void findViews() {
        clComment = findViewById(R.id.cl_comment_dialog);
        tvNo = findViewById(R.id.tv_no_comment);
        tvYes = findViewById(R.id.tv_yes_comment);


    }




}






