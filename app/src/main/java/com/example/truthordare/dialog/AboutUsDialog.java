package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;

public class AboutUsDialog extends Dialog {

    ConstraintLayout clAboutUs;

    public AboutUsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_about_us);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        findViews();
        setViewSize();
    }


    private void setViewSize() {

   clAboutUs.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;


    }

    private void findViews() {
        clAboutUs = findViewById(R.id.cl_about_us);

    }
}
