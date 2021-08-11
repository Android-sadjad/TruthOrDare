package com.example.truthordare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.interfaces.CallBackPlayerList;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StartFragment extends Fragment {


    CardView cvPlayer;

    ConstraintLayout[] clPlayerNames;
    TextInputEditText[] tiePlayerNames;

    TextView tvStartGame;
    TextView tvPlayerNumber;

    ImageView ivRightArrow;
    ImageView ivLeftArrow;



    Setting setting;

    int counter = 2;
StartGameFragment startGameFragment;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);
        findViews(view);
        setSize();
        configuration();


    }


    private void init(View view) {

        clPlayerNames = new ConstraintLayout[9];
        tiePlayerNames = new TextInputEditText[9];

        setting = new Setting(getContext());
    }

    private void findViews(View view) {

        ivRightArrow = view.findViewById(R.id.iv_right_arrow);
        ivLeftArrow = view.findViewById(R.id.iv_left_arrow);
        tvPlayerNumber = view.findViewById(R.id.tv_player_number);




        for (int i = 0; i < 9; i++) {

            int id = getContext().getResources().getIdentifier("cl_player_name_" + (i + 1), "id", getContext().getPackageName());
            clPlayerNames[i] = view.findViewById(id);

            id = getContext().getResources().getIdentifier("et_player_" + (i + 1), "id", getContext().getPackageName());
            tiePlayerNames[i] = view.findViewById(id);

        }

        tvStartGame = view.findViewById(R.id.tv_start_game);
        cvPlayer = view.findViewById(R.id.cv_start);
    }

    public void setSize() {


        cvPlayer.getLayoutParams().height = MyConstant.getScreenHeight() * 60 / 100;
    }



    private void configuration() {


        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter >= 9) {
                    Toast.makeText(getContext(), getString(R.string.max_number), Toast.LENGTH_SHORT).show();
                    return;
                }


                tvPlayerNumber.setText(String.valueOf(++counter));
                setVisibility();

            }
        });


        ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (counter <= 2) {
                    Toast.makeText(getContext(), getString(R.string.min_number), Toast.LENGTH_SHORT).show();
                    return;
                }


                tvPlayerNumber.setText(String.valueOf(--counter));
                setVisibility();


            }
        });


        tvStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setting.isButtonSound()) {
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

                   // callBackPlayerList.getPlayerList(playerNameList);
                    ////////////////////////////////////////////////////////
                    getChildFragmentManager().beginTransaction()
                            .add(R.id.fl_game_container,new StartGameFragment(playerNameList)).commit();
                }
            }


        });

    }

    private void setVisibility() {
        for (int i = 2; i < counter; i++) {
            clPlayerNames[i].setVisibility(View.VISIBLE);
        }

        for (int i = (int) counter; i < 9; i++) {
            clPlayerNames[i].setVisibility(View.GONE);

        }


    }




}
