package com.example.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MyTapsell;

import ir.tapsell.plus.TapsellPlusBannerType;

public class ExitDialog extends Dialog {
    RelativeLayout rvAdvertising;
    ConstraintLayout clExit;
    TextView tvNo;
    TextView tvYes;

    Activity activity;

    public ExitDialog(@NonNull Activity activity) {
        super(activity);
        setContentView(R.layout.dialog_exit);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.activity = activity;
        findViews();
        setViewSize();
        configuration();
        showAdvertising();
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

                activity.finish();
            }
        });


    }


    private void setViewSize() {

        clExit.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;


    }

    private void findViews() {
        clExit = findViewById(R.id.cl_delete_dialog);
        tvNo = findViewById(R.id.tv_no_delete);
        tvYes = findViewById(R.id.tv_yes_delete);


    }

    private void showAdvertising() {
        MyTapsell.showStandardBanner(activity, MyConstant.STANDARD_BANNER_HOME_PAGE, rvAdvertising, TapsellPlusBannerType.BANNER_250x250);


    }


}






