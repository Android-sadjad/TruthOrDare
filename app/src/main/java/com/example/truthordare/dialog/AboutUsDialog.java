package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;

public class AboutUsDialog extends Dialog {

    ConstraintLayout clAboutUs;

    public AboutUsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.fragment_about_us);
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
