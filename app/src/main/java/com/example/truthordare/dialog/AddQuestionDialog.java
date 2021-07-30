package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.interfaces.CallBackAddQuestions;
import com.google.android.material.textfield.TextInputEditText;

public class AddQuestionDialog extends Dialog {


    CallBackAddQuestions callBackAddQuestions;

    LinearLayout llAddDialog;

    Button btnCloseDialog;
    Button btnAddDialog;

    RadioButton rbTruth;
    RadioButton rbDare;

    TextInputEditText tieTextQuestion;

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

        llAddDialog = findViewById(R.id.ll_add_dialog);

        btnAddDialog = findViewById(R.id.btn_add_question);
        btnCloseDialog = findViewById(R.id.btn_close_add_dialog);

        rbDare = findViewById(R.id.rb_dare);
        rbTruth = findViewById(R.id.rb_truth);

        tieTextQuestion = findViewById(R.id.et_text_question);

    }

    public void setViewsSize() {

        llAddDialog.getLayoutParams().height = MyConstant.getScreenHeight() * 60 / 100;
        llAddDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 80 / 100;
    }

    private void configuration() {

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        btnAddDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tieTextQuestion.getText().length() == 0) {
                    tieTextQuestion.setError("لطفا متن سوال را وارد کنید");
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
