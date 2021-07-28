package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.truthordare.R;
import com.example.truthordare.interfaces.MyCallBack;
import com.example.truthordare.classes.MyConstant;
import com.google.android.material.textfield.TextInputEditText;

public class AddQuestionDialog extends Dialog {
    Context context;
    LinearLayout llAddDialog;
    Button btnCloseDialog;
    Button btnAddDialog;
    MyCallBack myCallBack;
    RadioButton rbTruth;
    RadioButton rbDare;
    TextInputEditText tieTextQuestion;

    public AddQuestionDialog(Context context, MyCallBack myCallBack) {
        super(context);
        this.myCallBack=myCallBack;
        this.context=context;
        setContentView(R.layout.dialog_add_question);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        findView();
        setSize();
        configuration();

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

                if (tieTextQuestion.getText().length()==0){
                    tieTextQuestion.setError("لطفا متن سوال را وارد کنید");
                    return;
                }

                if(rbDare.isChecked()){
                    myCallBack.callBackAddList("dare",tieTextQuestion.getText().toString().trim());

            }
                else {
                    myCallBack.callBackAddList("truth",tieTextQuestion.getText().toString().trim());
                }
                cancel();
            }

        });


    }


    private void findView() {
        llAddDialog=findViewById(R.id.ll_add_dialog);
        btnAddDialog=findViewById(R.id.btn_add_question);
        btnCloseDialog=findViewById(R.id.btn_close_add_dialog);
        rbDare=findViewById(R.id.rb_dare);
        rbTruth=findViewById(R.id.rb_truth);
        tieTextQuestion=findViewById(R.id.et_text_question);

    }

    public void setSize() {



        llAddDialog.getLayoutParams().height = MyConstant.getScreenHeight() * 60 / 100;
        llAddDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 80 / 100;
    }



}
