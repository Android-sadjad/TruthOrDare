package com.example.truthordare;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StartDialog extends Dialog {

    ConstraintLayout clStartDialog;

    Slider sliderPlayerNumber;
    TextView tvPlayerNumber;

    ConstraintLayout[] clPlayerNames;
    TextInputEditText[] tiePlayerNames;

    TextView tvStartGame;

    Context context;
    MyCallBack myCallBack;


    public StartDialog(Context context, MyCallBack myCallBack) {
        super(context);
        setContentView(R.layout.dialog_start);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.context = context;
        this.myCallBack = myCallBack;

        init();
        findViews();
        setSize();
        configuration();

    }

    private void init() {

        clPlayerNames = new ConstraintLayout[9];
        tiePlayerNames = new TextInputEditText[9];
    }

    private void findViews() {
        clStartDialog = findViewById(R.id.cl_start_dialog);

        sliderPlayerNumber = findViewById(R.id.slider_player_number);
        tvPlayerNumber = findViewById(R.id.tv_player_number);

        for (int i = 0; i < 9; i++) {

            int id = context.getResources().getIdentifier("cl_player_name_" + (i + 1), "id", context.getPackageName());
            clPlayerNames[i] = findViewById(id);

            id = context.getResources().getIdentifier("et_player_" + (i + 1), "id", context.getPackageName());
            tiePlayerNames[i] = findViewById(id);
        }

        tvStartGame = findViewById(R.id.tv_start_game);
    }

    public void setSize() {


        clStartDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 90 / 100;
        clStartDialog.getLayoutParams().height = MyConstant.getScreenHeight() * 90 / 100;
    }

    private void configuration() {

        sliderPlayerNumber.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {

                tvPlayerNumber.setText("تعداد بازیکن: " + (int) value + " نفر");

                for (int i = 2; i < value; i++) {
                    clPlayerNames[i].setVisibility(View.VISIBLE);
                }

                for (int i = (int) value; i < 9; i++) {
                    clPlayerNames[i].setVisibility(View.GONE);

                }
            }
        });

        tvStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean fullAllEditText = true;
                ArrayList<String> playerNameList = new ArrayList<>();


                for (int i = 0; i < 9; i++) {

                    if (clPlayerNames[i].getVisibility() == View.VISIBLE) {

                        if (tiePlayerNames[i].getText().length() == 0) {

                            fullAllEditText = false;
                            tiePlayerNames[i].setError("لطفا این فیلد را هم کامل کنید");
                            break;
                        } else
                            playerNameList.add(tiePlayerNames[i].getText().toString());


                    }
                }
                if (fullAllEditText == true) {
                    myCallBack.callBackPlayerList(playerNameList);
                    cancel();
                }

            }
        });

    }


}
