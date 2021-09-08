package com.dragontech.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyIntent;
import com.dragontech.truthordare.classes.UseFullMethod;

public class BadCommentDialog extends Dialog {

    ConstraintLayout clBadComment;

    ImageView ivInstagram;
    ImageView ivEmail;

    Animation scaleAnimation;



    public BadCommentDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_bad_comment);
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

        clBadComment.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;

    }

    private void findViews() {
        clBadComment = findViewById(R.id.cl_bad_comment);
        ivInstagram = findViewById(R.id.iv_insta_bad_comment);
        ivEmail = findViewById(R.id.iv_email_bad_comment);

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
