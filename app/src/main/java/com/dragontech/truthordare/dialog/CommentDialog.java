package com.dragontech.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyIntent;
import com.dragontech.truthordare.classes.UseFullMethod;

public class CommentDialog extends Dialog {

    private ConstraintLayout clComment;

    private TextView tvNo;
    private TextView tvYes;

    private Activity activity;

    private BadCommentDialog badCommentDialog;

    public CommentDialog(@NonNull Activity activity) {
        super(activity);

        setContentView(R.layout.dialog_comment);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.activity = activity;

        init();
        findViews();

        setViewSize();
        configuration();

    }


    private void init() {

        badCommentDialog = new BadCommentDialog(activity);


    }

    private void findViews() {

        clComment = findViewById(R.id.cl_comment_dialog);
        tvNo = findViewById(R.id.tv_no_comment);
        tvYes = findViewById(R.id.tv_yes_comment);

    }

    private void setViewSize() {

        clComment.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;
    }

    private void configuration() {

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
                badCommentDialog.show();
            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UseFullMethod.isNetworkAvailable(activity)) {
                    Toast.makeText(activity, getContext().getString(R.string.thanks_comment), Toast.LENGTH_LONG).show();
                    MyIntent.commentIntent(activity);
                    cancel();
                }
            }
        });

    }
}






