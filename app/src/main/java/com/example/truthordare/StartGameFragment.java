package com.example.truthordare;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class StartGameFragment extends Fragment {


    int width;
    int height;
    int sign = 1;
    int currentDegree = 0;
    LinearLayout llQustion;
    LinearLayout llTruthOrdare;
    ImageView ivCircleBackground;
    ImageView ivBottle;
    Button btnChangeQuestion;
    Button btnCloseQustion;
    Button btnDare;
    Button btnTruth;
    TextView tvQuestion;
    TextView tvTod;
    ImageView ivSetting;
    ImageView ivMenu;



    LinearLayout llNamesBord;

    ArrayList<Integer> randomNumberList;
    ArrayList<String> playerNameList;

    TextView[] tvNames;
    ImageView[] ivColors;


    public StartGameFragment(ArrayList<String> playerNameList) {
        this.playerNameList = new ArrayList<>();
        this.playerNameList = playerNameList;

    }


    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init();
        findViews(view);

        setTextAndColor();
        setSize();
        setHeight();
        configuration();

    }


    private void setHeight() {

        llQustion.setTranslationY(llQustion.getLayoutParams().height);
        llTruthOrdare.setTranslationY(llTruthOrdare.getLayoutParams().height);

    }

    private void init() {


        randomNumberList = new ArrayList<>(360);


        tvNames = new TextView[9];
        ivColors = new ImageView[9];

    }

    private void setTextAndColor() {


        for (int i = 0; i < playerNameList.size(); i++) {

            tvNames[i].setVisibility(View.VISIBLE);
            tvNames[i].setText(playerNameList.get(i));

            ivColors[i].setVisibility(View.VISIBLE);

        }

    }

    private void findViews(View view) {

        ivCircleBackground = view.findViewById(R.id.iv_circle_background);
        ivBottle = view.findViewById(R.id.iv_bottle);
        llQustion = view.findViewById(R.id.ll_question);
        tvTod = view.findViewById(R.id.tv_tod);
        tvQuestion = view.findViewById(R.id.tv_qustion);
        btnChangeQuestion = view.findViewById(R.id.btn_change_question);
        btnCloseQustion = view.findViewById(R.id.btn_close_question);
        btnDare = view.findViewById(R.id.btn_dare);
        btnTruth = view.findViewById(R.id.btn_truth);

        ivSetting = view.findViewById(R.id.iv_setting);
        ivMenu = view.findViewById(R.id.iv_menu);

        llNamesBord = view.findViewById(R.id.ll_names_and_color);
        llTruthOrdare = view.findViewById(R.id.ll_truth_dare);

        tvNames[0] = view.findViewById(R.id.tv_name_1);
        tvNames[1] = view.findViewById(R.id.tv_name_2);
        tvNames[2] = view.findViewById(R.id.tv_name_3);
        tvNames[3] = view.findViewById(R.id.tv_name_4);
        tvNames[4] = view.findViewById(R.id.tv_name_5);
        tvNames[5] = view.findViewById(R.id.tv_name_6);
        tvNames[6] = view.findViewById(R.id.tv_name_7);
        tvNames[7] = view.findViewById(R.id.tv_name_8);
        tvNames[8] = view.findViewById(R.id.tv_name_9);

        ivColors[0] = view.findViewById(R.id.iv_color_1);
        ivColors[1] = view.findViewById(R.id.iv_color_2);
        ivColors[2] = view.findViewById(R.id.iv_color_3);
        ivColors[3] = view.findViewById(R.id.iv_color_4);
        ivColors[4] = view.findViewById(R.id.iv_color_5);
        ivColors[5] = view.findViewById(R.id.iv_color_6);
        ivColors[6] = view.findViewById(R.id.iv_color_7);
        ivColors[7] = view.findViewById(R.id.iv_color_8);
        ivColors[8] = view.findViewById(R.id.iv_color_9);

    }

    private void setSize() {

        width = MyConstant.getScreenWidth();
        height = MyConstant.getScreenHeight();

        int circleRadius = width * 85 / 100;

        ivCircleBackground.getLayoutParams().width = circleRadius;
        ivCircleBackground.getLayoutParams().height = circleRadius;

        llNamesBord.getLayoutParams().height = height * 30 / 100;
        llQustion.getLayoutParams().height = height * 35 / 100;
        llTruthOrdare.getLayoutParams().height = height * 9 / 100;

        ivSetting.getLayoutParams().height=width*13/100;
        ivSetting.getLayoutParams().width=width*13/100;

        ivMenu.getLayoutParams().height=width*13/100;
        ivMenu.getLayoutParams().width=width*13/100;

    }

    private void configuration() {

        ivCircleBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int randomNumber;

                while (true) {
                    randomNumber = new Random().nextInt() % 360;
                    if (!randomNumberList.contains(randomNumber)) {
                        randomNumberList.add(randomNumber);
                        break;
                    }
                }

                randomNumber *= sign;
                sign *= -1;

                RotateAnimation rotate = new RotateAnimation(currentDegree,
                        3600 + randomNumber,
                        Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                rotate.setDuration(2000);
                rotate.setFillAfter(true);

                currentDegree = randomNumber;
                ivBottle.startAnimation(rotate);

                if (llNamesBord.getTranslationY() == 0)
                    up();
                else {
                    down();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            up_zero_delay();
                        }
                    }, 2000);
                }


            }
        });
        btnTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                down();
                tvTod.setText("حقیقت");
                tvQuestion.setText("اخرین بار کی اب خوردی");
                upQuestionLayout();


            }
        });
        btnDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down();
                tvTod.setText("جرعت");
                tvQuestion.setText("برو آب بخور");
                upQuestionLayout();


            }
        });

        btnCloseQustion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downQuestionLayout();
            }
        });


    }

    private void up() {
        llTruthOrdare.animate().translationY(0).setDuration(1000).setStartDelay(2000);
        llNamesBord.animate().translationY((llTruthOrdare.getLayoutParams().height * -1)-10).setDuration(1000).setStartDelay(2000);
    }

    private void up_zero_delay() {
        llTruthOrdare.animate().translationY(0).setDuration(1000).setStartDelay(0);
        llNamesBord.animate().translationY((llTruthOrdare.getLayoutParams().height * -1)-10).setDuration(1000).setStartDelay(0);
    }

    private void down() {

        llTruthOrdare.animate().translationY(llTruthOrdare.getLayoutParams().height)
                .setDuration(500).setStartDelay(0);


        llNamesBord.animate().translationY(0).setDuration(500).setStartDelay(0);


    }

    private void downQuestionLayout() {

        llQustion.animate().translationY(llQustion.getLayoutParams().height).setDuration(1000).setStartDelay(0);

    }


    private void upQuestionLayout() {

        llQustion.animate().translationY(0).setDuration(1000).setStartDelay(0);


    }


}
