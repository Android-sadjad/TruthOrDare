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
import com.example.truthordare.interfaces.CallBackUpdateList;
import com.example.truthordare.model.Questions;

public class AdvertisingDialog extends Dialog {

    ConstraintLayout constraintLayout;
    TextView tvYes;
    TextView tvNO;
    Questions questions;
    CallBackUpdateList callBackUpdateList;
    Activity activity;

    public AdvertisingDialog(Activity activity, Questions questions, CallBackUpdateList callBackUpdateList) {
        super(activity);


        setContentView(R.layout.dialog_advertising);
        this.activity = activity;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.questions = questions;
        this.callBackUpdateList = callBackUpdateList;

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

        constraintLayout.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
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


                MyTapsell.showInterstitialAd(activity, MyConstant.reward_based, new CallBackReward() {
                    @Override
                    public void myReward() {
                        flag[0] = false;

                        Toast.makeText(getContext(),R.string.ten_question_added, Toast.LENGTH_SHORT).show();
                        questions.setQuestionNumber((questions.getQuestionNumber() + 3));
                        questions.updateQuestions(getContext(), questions);

                        callBackUpdateList.updateCallBack();
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
