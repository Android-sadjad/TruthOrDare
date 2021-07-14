package com.example.truthordare;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StartDialog extends Dialog {
    ConstraintLayout clStartDialog;

    public StartDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_start);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        findViews();
        setSize();

    }

    private void findViews() {

        clStartDialog = findViewById(R.id.cl_start_dialog);
    }

    public void setSize() {
        clStartDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
        clStartDialog.getLayoutParams().height = MyConstant.getScreenHeight() * 80 / 100;

    }

}
