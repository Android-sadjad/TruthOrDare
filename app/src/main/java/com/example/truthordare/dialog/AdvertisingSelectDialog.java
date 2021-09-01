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
import com.example.truthordare.classes.UseFullMethod;
import com.example.truthordare.interfaces.CallBackMain;
import com.example.truthordare.interfaces.CallBackReward;
import com.example.truthordare.model.Setting;

public class AdvertisingSelectDialog extends Dialog {


    ConstraintLayout constraintLayout;
    TextView tvYes;
    TextView tvNo;
    Setting setting;
    CallBackMain callBackMain;
    Activity activity;


    public AdvertisingSelectDialog(Activity activity, Setting setting, CallBackMain callBackMain) {
        super(activity);

        setContentView(R.layout.dialog_advertising_select);
        this.activity = activity;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setting = setting;
        this.callBackMain = callBackMain;


        findViews();
        setViewsSize();
        configuration();
    }

    private void findViews() {

        constraintLayout = findViewById(R.id.cl_delete_dialog);
        tvYes = findViewById(R.id.tv_yes_delete);
        tvNo = findViewById(R.id.tv_no_delete);

    }


    private void setViewsSize() {

        constraintLayout.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;
    }

    private void configuration() {

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final boolean[] flag = {true};

                MyTapsell.showInterstitialAd(activity, MyConstant.INTERSTITIAL_VIDEO, new CallBackReward() {
                    @Override
                    public void onReward() {

                        flag[0] = false;
                        callBackMain.callBack();

                    }

                    @Override
                    public void onError() {

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
