package com.example.truthordare;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StartDialog extends Dialog {
ConstraintLayout clStartDialog;

    public StartDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_start);
        clStartDialog =findViewById(R.id.cl_start_dialog);
    }


  public void setSize(){
        clStartDialog.getLayoutParams().width=MyConstant.getScreenWidth()*90/100;
      clStartDialog.getLayoutParams().height=MyConstant.getScreenHeight()*70/100;



  }





}
