package com.dragontech.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.UseFullMethod;
import com.dragontech.truthordare.interfaces.CallBackMain;

public class DeleteItemDialog extends Dialog {

    private ConstraintLayout clDelete;

    private TextView tvNo;
    private TextView tvYes;

    private CallBackMain callBackMain;

    public DeleteItemDialog(@NonNull Context context, CallBackMain callBackMain) {
        super(context);
        setContentView(R.layout.dialog_delete);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.callBackMain = callBackMain;

        findViews();
        setViewSize();
        configuration();
    }

    private void findViews() {
        clDelete = findViewById(R.id.cl_delete_dialog);
        tvYes = findViewById(R.id.tv_yes);
        tvNo = findViewById(R.id.tv_no);
    }

    private void setViewSize() {

        clDelete.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;
    }

    private void configuration() {

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackMain.callBack();
                cancel();

            }
        });

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });

    }
}
