package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;

public class AdvertisingDialog extends Dialog {

    ConstraintLayout constraintLayout;
    TextView tvYes;
    TextView tvNO;

    public AdvertisingDialog(Context context) {
        super(context);

        setContentView(R.layout.dialog_advertising);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        findViews();
        setViewsSize();
        configuration();

    }

    private void findViews(){

        constraintLayout=findViewById(R.id.constraint_layout_show_advertising);
        tvYes=findViewById(R.id.tv_yes);
        tvNO=findViewById(R.id.tv_no);

    }

    private void setViewsSize() {

        constraintLayout.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
    }

    private void configuration(){

        tvNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "تبریک ۱۰ سوال به هر دسته بندی اضافه شد", Toast.LENGTH_SHORT).show();



            }
        });

    }
}
