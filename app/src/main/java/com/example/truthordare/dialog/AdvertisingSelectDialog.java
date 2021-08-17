package com.example.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MyTapsell;
import com.example.truthordare.interfaces.CallBackReward;
import com.example.truthordare.interfaces.CallBackUpdateSelect;
import com.example.truthordare.model.Setting;
import com.google.android.material.card.MaterialCardView;

public class AdvertisingSelectDialog extends Dialog {


    ConstraintLayout constraintLayout;
    MaterialCardView cvYes;
    MaterialCardView cvNo;
    Setting setting;
    CallBackUpdateSelect callBackUpdateSelect;
    Activity activity;


    public AdvertisingSelectDialog(Activity activity, Setting setting, CallBackUpdateSelect callBackUpdateSelect) {
        super(activity);

        setContentView(R.layout.dialog_advertising_select);
        this.activity = activity;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setting = setting;
        this.callBackUpdateSelect = callBackUpdateSelect;


        findViews();
        setViewsSize();
        configuration();
    }

    private void findViews() {

        constraintLayout = findViewById(R.id.cl_bottle_video);
        cvYes = findViewById(R.id.cv_yes_select_dialog);
        cvNo = findViewById(R.id.cv_no_select_dialog);

    }


    private void setViewsSize() {

        constraintLayout.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
    }

    private void configuration() {

        cvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });

        cvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final boolean[] flag = {true};

                MyTapsell.showInterstitialAd(activity, MyConstant.reward_based, new CallBackReward() {
                    @Override
                    public void myReward() {

                        flag[0] = false;
                        callBackUpdateSelect.updateSelect();

                    }

                    @Override
                    public void myError() {

                        if (flag[0]) {
                            Toast.makeText(activity, R.string.need_see_video, Toast.LENGTH_SHORT).show();

                        }

                    }
                });


                cancel();
            }
        });

    }

}
