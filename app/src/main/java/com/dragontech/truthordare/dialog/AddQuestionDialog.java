package com.dragontech.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.UseFullMethod;
import com.dragontech.truthordare.interfaces.CallBackAddQuestions;
import com.google.android.material.textfield.TextInputEditText;

public class AddQuestionDialog extends Dialog {

    private CallBackAddQuestions callBackAddQuestions;

    private ConstraintLayout clAddDialog;

    private TextView tvCloseDialog;
    private TextView tvAddDialog;

    private RadioButton rbTruth;
    private RadioButton rbDare;

    private TextInputEditText tieTextQuestion;

    public AddQuestionDialog(Context context, CallBackAddQuestions callBackAddQuestions) {

        super(context);
        setContentView(R.layout.dialog_add_question);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.callBackAddQuestions = callBackAddQuestions;

        findViews();
        setViewsSize();
        configuration();

    }

    private void findViews() {

        clAddDialog = findViewById(R.id.cl_add_dialog);

        tvAddDialog = findViewById(R.id.tv_add_question);
        tvCloseDialog = findViewById(R.id.tv_close_add_dialog);

        rbDare = findViewById(R.id.rb_dare);
        rbTruth = findViewById(R.id.rb_truth);

        tieTextQuestion = findViewById(R.id.et_text_question);
    }

    public void setViewsSize() {

        clAddDialog.getLayoutParams().width = UseFullMethod.getScreenWidth() * 90 / 100;
    }

    private void configuration() {

        tvCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        tvAddDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tieTextQuestion.getText().toString().trim().length() == 0) {
                    tieTextQuestion.setError(getContext().getString(R.string.please_enter_question));
                    return;
                }

                if (rbDare.isChecked()) {
                    callBackAddQuestions.addQuestionToList(MyConstant.DARE, tieTextQuestion.getText().toString().trim());

                } else {
                    callBackAddQuestions.addQuestionToList(MyConstant.TRUTH, tieTextQuestion.getText().toString().trim());
                }
                cancel();
            }

        });


    }


}
