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
import com.example.truthordare.model.Questions;

public class AdvertisingDialog extends Dialog {

    ConstraintLayout constraintLayout;
    TextView tvYes;
    TextView tvNO;
    Questions questions;
    CallBackMain callBackMain;
    Activity activity;

    public AdvertisingDialog(Activity activity, Questions questions, CallBackMain callBackMain) {
        super(activity);


        setContentView(R.layout.dialog_advertising);
        this.activity = activity;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.questions = questions;
        this.callBackMain = callBackMain;

        findViews();
        setViewsSize();
        configuration();
    }

    private void findViews() {

        constraintLayout = findViewById(R.id.cl_exit_dialog);
        tvYes = findViewById(R.id.tv_yes_exit);
        tvNO = findViewById(R.id.tv_no_exit);

    }

    private void setViewsSize() {

        constraintLayout.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;
    }

    private void configuration() {

        tvNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancel();
            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (questions.getQuestionNumber() >= MyConstant.MAX_QUESTION_NUMBER) {

                    Toast.makeText(activity, R.string.all_question_added_already, Toast.LENGTH_SHORT).show();
                    cancel();
                    return;
                }

                final boolean[] flag = {true};


                MyTapsell.showInterstitialAd(activity, MyConstant.INTERSTITIAL_VIDEO, new CallBackReward() {
                    @Override
                    public void onReward() {
                        flag[0] = false;

                        Toast.makeText(getContext(),R.string.ten_question_added, Toast.LENGTH_SHORT).show();
                        questions.setQuestionNumber((questions.getQuestionNumber() + 3));
                        questions.updateQuestions(getContext(), questions);

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
