package com.example.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.interfaces.CallBackDeleteItem;

public class DeleteItemDialog extends Dialog {

    ConstraintLayout clDelete;
    TextView tvNo;
    TextView tvYes;
CallBackDeleteItem callBackDeleteItem;
    Activity activity;



    public DeleteItemDialog(@NonNull Context context, CallBackDeleteItem callBackDeleteItem) {
        super(context);
        setContentView(R.layout.dialog_delete);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.callBackDeleteItem=callBackDeleteItem;
        this.activity=activity;

        findViews();
        setViewSize();
        configuration();
    }

    private void configuration() {

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackDeleteItem.deleteItem();
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

    private void setViewSize() {
        clDelete.getLayoutParams().width= MyConstant.getScreenWidth()*90/100;

    }

    private void findViews() {
        clDelete=findViewById(R.id.cl_delete_dialog);
        tvYes=findViewById(R.id.tv_yes_delete);
        tvNo=findViewById(R.id.tv_no_delete);
    }
}
