package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyIntent;
import com.example.truthordare.classes.UseFullMethod;

public class AboutUsDialog extends Dialog {

    ConstraintLayout clAboutUs;

    ImageView ivInstagram;
    ImageView ivEmail;

    Animation scaleAnimation;

    public AboutUsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_about_us);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        init();
        findViews();
        setViewSize();
        configuration();
    }

    private void init() {

        scaleAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_animation);
    }

    private void setViewSize() {

        clAboutUs.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;

    }

    private void findViews() {
        clAboutUs = findViewById(R.id.cl_about_us);
        ivInstagram = findViewById(R.id.iv_insta);
        ivEmail = findViewById(R.id.iv_email);

    }

    private void configuration() {

        ivInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(scaleAnimation);
                MyIntent.instagramPageIntent(getContext());
            }
        });

        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                MyIntent.emailIntent(getContext());
            }
        });

    }
}
