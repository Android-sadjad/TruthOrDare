package com.dragontech.truthordare.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.MyTapsell;
import com.dragontech.truthordare.classes.UseFullMethod;
import com.dragontech.truthordare.interfaces.CallBackMain;
import com.dragontech.truthordare.interfaces.CallBackReward;
import com.dragontech.truthordare.model.Questions;

public class AdvertisingQuestionDialog extends Dialog {

    ConstraintLayout constraintLayout;

    TextView tvYes;
    TextView tvNO;

    Questions questions;
    CallBackMain callBackMain;
    Activity activity;

    public AdvertisingQuestionDialog(Activity activity, Questions questions, CallBackMain callBackMain) {
        super(activity);

        setContentView(R.layout.dialog_advertising);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.activity = activity;
        this.questions = questions;
        this.callBackMain = callBackMain;

        findViews();
        setViewsSize();
        configuration();
    }

    private void findViews() {

        constraintLayout = findViewById(R.id.cl_ad_add_question_dialog);
        tvYes = findViewById(R.id.tv_yes);
        tvNO = findViewById(R.id.tv_no);
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

                if(UseFullMethod.isNetworkAvailable(getContext())){

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

                            Toast.makeText(getContext(), R.string.ten_question_added, Toast.LENGTH_SHORT).show();
                            questions.setQuestionNumber((questions.getQuestionNumber() + MyConstant.ADD_QUESTION_NUMBER));
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


            }
        });

    }
}
