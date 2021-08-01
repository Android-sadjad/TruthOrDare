package com.example.truthordare.dialog;

import android.app.Activity;
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
import com.example.truthordare.classes.MyTapsell;
import com.example.truthordare.interfaces.CallBackReward;
import com.example.truthordare.interfaces.CallBackUpdateList;
import com.example.truthordare.interfaces.CallBackUpdateSelect;
import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;

public class AdvertisingSelectDialog extends Dialog {


    ConstraintLayout constraintLayout;
    TextView tvYes;
    TextView tvNO;
    Setting setting;
    CallBackUpdateSelect callBackUpdateSelect;
    Activity activity;
//    boolean[] lockFlag;
//    int position;

    public AdvertisingSelectDialog(Activity activity, Setting setting, CallBackUpdateSelect callBackUpdateSelect) {
        super(activity);

        setContentView(R.layout.dialog_advertising_select);
        this.activity=activity;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setting=setting;
        this.callBackUpdateSelect=callBackUpdateSelect;
//this.lockFlag=lockFlags;
//this.position=position;

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

                final boolean[] flag = {true};

                MyTapsell.showInterstitialAd(activity, MyConstant.reward_based, new CallBackReward() {
                    @Override
                    public void myReward() {

                        flag[0] =false;
                        callBackUpdateSelect.updateSelect();

                    }

                    @Override
                    public void myError() {

                        if (flag[0])

                        {
                            Toast.makeText(activity, "برای دریافت جایزه باید ویدیو تا انتها مشاهده شود.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });







                cancel();
            }
        });

    }

}
