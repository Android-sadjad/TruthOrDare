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

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class StartGameFragment extends Fragment {

    int screenWidth;
    int screenHeight;
    int sign = 1;
    int currentDegree = 0;

    ImageView ivCircleBackground;
    ImageView ivBottle;

    LinearLayout llNamesBord;
    LinearLayout llTruthOrDare;
    LinearLayout llQuestions;

    Button btnDare;
    Button btnTruth;

    Button btnChangeQuestion;
    Button btnCloseQuestion;

    TextView tvTod;
    TextView tvQuestion;

    ImageView ivSetting;
    ImageView ivMenu;

    ArrayList<Integer> randomNumberList;
    ArrayList<String> playerNameList;

    TextView[] tvNames;
    ImageView[] ivColors;


    public StartGameFragment(ArrayList<String> playerNameList) {

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
        setViewSize();
        setViewTranslation();
        setTextAndColor();
        configuration();

    }


    private void init() {

        screenWidth = MyConstant.getScreenWidth();
        screenHeight = MyConstant.getScreenHeight();

        randomNumberList = new ArrayList<>(360);

        tvNames = new TextView[9];
        ivColors = new ImageView[9];
    }

    private void findViews(View view) {

        ivMenu = view.findViewById(R.id.iv_menu);
        ivSetting = view.findViewById(R.id.iv_setting);

        ivCircleBackground = view.findViewById(R.id.iv_circle_background);
        ivBottle = view.findViewById(R.id.iv_bottle);

        llQuestions = view.findViewById(R.id.ll_question);
        llNamesBord = view.findViewById(R.id.ll_names_board);
        llTruthOrDare = view.findViewById(R.id.ll_truth_dare);

        btnDare = view.findViewById(R.id.btn_dare);
        btnTruth = view.findViewById(R.id.btn_truth);

        tvTod = view.findViewById(R.id.tv_tod);
        tvQuestion = view.findViewById(R.id.tv_qustion);
        btnChangeQuestion = view.findViewById(R.id.btn_change_question);
        btnCloseQuestion = view.findViewById(R.id.btn_close_question);


        for (int i = 0; i < 9; i++) {

            int id = getContext().getResources().getIdentifier("tv_name_" + (i + 1), "id", getContext().getPackageName());
            tvNames[i] = view.findViewById(id);

            id = getContext().getResources().getIdentifier("iv_color_" + (i + 1), "id", getContext().getPackageName());
            ivColors[i] = view.findViewById(id);
        }

/*
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
*/

    }

    private void setViewSize() {

        int circleRadius = screenWidth * 80 / 100;

        ivCircleBackground.getLayoutParams().width = circleRadius;
        ivCircleBackground.getLayoutParams().height = circleRadius;

        llNamesBord.getLayoutParams().height = screenHeight * 30 / 100;
        llQuestions.getLayoutParams().height = screenHeight * 35 / 100;
        llTruthOrDare.getLayoutParams().height = screenHeight * 9 / 100;

        ivSetting.getLayoutParams().height = screenWidth * 13 / 100;
        ivSetting.getLayoutParams().width = screenWidth * 13 / 100;

        ivMenu.getLayoutParams().height = screenWidth * 13 / 100;
        ivMenu.getLayoutParams().width = screenWidth * 13 / 100;

    }

    private void setViewTranslation() {

        llQuestions.setTranslationY(llQuestions.getLayoutParams().height);
        llTruthOrDare.setTranslationY(llTruthOrDare.getLayoutParams().height);

    }

    private void setTextAndColor() {


        for (int i = 0; i < playerNameList.size(); i++) {

            tvNames[i].setVisibility(View.VISIBLE);
            tvNames[i].setText(playerNameList.get(i));

            ivColors[i].setVisibility(View.VISIBLE);

        }

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
                    upAnimation();
                else {
                    downAnimation();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            upAnimationWithoutDelay();
                        }
                    }, 2000);
                }


            }
        });
        btnTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                downAnimation();
                tvTod.setText("حقیقت");
                tvQuestion.setText("اخرین بار کی اب خوردی");
                upQuestionLayoutAnimation();


            }
        });
        btnDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downAnimation();
                tvTod.setText("جرعت");
                tvQuestion.setText("برو آب بخور");
                upQuestionLayoutAnimation();


            }
        });

        btnCloseQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downQuestionLayoutAnimation();
            }
        });


    }



    private void upAnimation() {

        llTruthOrDare.animate().translationY(0).setDuration(1000).setStartDelay(2000);
        llNamesBord.animate().translationY((llTruthOrDare.getLayoutParams().height * -1) - 10).setDuration(1000).setStartDelay(2000);
    }

    private void upAnimationWithoutDelay() {
        llTruthOrDare.animate().translationY(0).setDuration(1000).setStartDelay(0);
        llNamesBord.animate().translationY((llTruthOrDare.getLayoutParams().height * -1) - 10).setDuration(1000).setStartDelay(0);
    }

    private void downAnimation() {

        llTruthOrDare.animate().translationY(llTruthOrDare.getLayoutParams().height)
                .setDuration(500).setStartDelay(0);


        llNamesBord.animate().translationY(0).setDuration(500).setStartDelay(0);

    }

    private void upQuestionLayoutAnimation() {

        llQuestions.animate().translationY(0).setDuration(1000).setStartDelay(0);

    }

    private void downQuestionLayoutAnimation() {

        llQuestions.animate().translationY(llQuestions.getLayoutParams().height).setDuration(1000).setStartDelay(0);

    }

}
