package com.example.truthordare.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.R;
import com.example.truthordare.interfaces.CallBackPlayerList;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;
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
    CallBackPlayerList callBackPlayerList;

    Switch switchDefaultQuestion;
    Switch switchMyQuestion;

    Setting setting;


    public StartDialog(Context context,  CallBackPlayerList callBackPlayerList) {
        super(context);
        setContentView(R.layout.dialog_start);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.context = context;
        this.callBackPlayerList = callBackPlayerList;

        init();
        findViews();
        setSize();
        applySetting();
        configuration();

    }

    private void init() {

        clPlayerNames = new ConstraintLayout[9];
        tiePlayerNames = new TextInputEditText[9];

        switchDefaultQuestion =findViewById(R.id.switch_default_question);
        switchMyQuestion =findViewById(R.id.switch_my_question);

        setting=new Setting(context);
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

    public void applySetting(){

        switchDefaultQuestion.setChecked(setting.isDefaultQuestion());
        switchMyQuestion.setChecked(setting.isMYQuestion());

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

                if (setting.isButtonSound()){
                    MyMediaPlayer.mpBtnSound.start();
                }



                boolean fullAllEditText = true;
                ArrayList<String> playerNameList = new ArrayList<>();


                for (int i = 0; i < 9; i++) {

                    if (clPlayerNames[i].getVisibility() == View.VISIBLE) {

                        if (tiePlayerNames[i].getText().length() == 0) {

                            fullAllEditText = false;
                            tiePlayerNames[i].setError(getContext().getString(R.string.please_enter_this_field));
                            break;
                        } else
                            playerNameList.add(tiePlayerNames[i].getText().toString());


                    }
                }
                if (fullAllEditText == true) {
                    callBackPlayerList.getPlayerList(playerNameList);
                    cancel();
                }
              }


        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        setting.setDefaultQuestion(switchDefaultQuestion.isChecked());
        setting.setMYQuestion(switchMyQuestion.isChecked());

        setting.updateSetting(context,setting);

    }
}
