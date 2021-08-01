package com.example.truthordare.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
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

import com.example.truthordare.R;
import com.example.truthordare.activity.SettingActivity;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MySharedPreferences;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;

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

    ArrayList<Integer> randomNumberList;
    ArrayList<String> playerNameList;

    ArrayList<Integer> repetitiousTruthQuestion;
    ArrayList<Integer> repetitiousDareQuestion;

    TextView[] tvNames;
    ImageView[] ivColors;

    Setting setting;

MediaPlayer mpRound;

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
        setTextAndColorAndBackground();
        configuration();
        updateSetting();


    }


    private void init() {

        screenWidth = MyConstant.getScreenWidth();
        screenHeight = MyConstant.getScreenHeight();

        randomNumberList = new ArrayList<>();

        tvNames = new TextView[playerNameList.size()];
        ivColors = new ImageView[playerNameList.size()];

        setting = new Setting(getContext());

        repetitiousTruthQuestion = new ArrayList<>();
        repetitiousDareQuestion = new ArrayList<>();

        mpRound=MediaPlayer.create(getContext(),R.raw.rounding);


    }

    private void findViews(View view) {


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


        for (int i = 0; i < tvNames.length; i++) {

            int id = getContext().getResources().getIdentifier("tv_name_" + (i + 1), "id", getContext().getPackageName());
            tvNames[i] = view.findViewById(id);

            id = getContext().getResources().getIdentifier("iv_color_" + (i + 1), "id", getContext().getPackageName());
            ivColors[i] = view.findViewById(id);
        }


    }

    private void setViewSize() {


        ivCircleBackground.getLayoutParams().width = screenWidth * 80 / 100;
        ivCircleBackground.getLayoutParams().height = screenWidth * 80 / 100;

        llNamesBord.getLayoutParams().height = screenHeight * 30 / 100;
        llQuestions.getLayoutParams().height = screenHeight * 35 / 100;
        llTruthOrDare.getLayoutParams().height = screenHeight * 9 / 100;

        ivSetting.getLayoutParams().height = screenWidth * 13 / 100;
        ivSetting.getLayoutParams().width = screenWidth * 13 / 100;

    }

    private void setViewTranslation() {

        llQuestions.setTranslationY(llQuestions.getLayoutParams().height);
        llTruthOrDare.setTranslationY(llTruthOrDare.getLayoutParams().height);

    }

    private void setTextAndColorAndBackground() {


        for (int i = 0; i < tvNames.length; i++) {

            tvNames[i].setVisibility(View.VISIBLE);
            tvNames[i].setText(playerNameList.get(i));

            ivColors[i].setVisibility(View.VISIBLE);

        }


        int circleBackgroundId = getContext().getResources().getIdentifier("bg_circle_" + playerNameList.size(), "drawable", getContext().getPackageName());
        ivCircleBackground.setBackgroundResource(circleBackgroundId);


    }

    private void configuration() {

        ivCircleBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (mpRound.isPlaying()){
                    return;

                }

                if(setting.isCircleSound()){
                    mpRound.start();
                }

                int randomNumber = createRandomNumber();

                RotateAnimation rotate = new RotateAnimation(currentDegree,
                        3600 + randomNumber,
                        Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                rotate.setDuration(mpRound.getDuration());
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

                if (llQuestions.getTranslationY() == 0) {
                    downQuestionLayoutAnimation();
                }


            }
        });
        btnTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                downAnimation();
                tvTod.setText("حقیقت");
                showRandomTruthQuestion();

                upQuestionLayoutAnimation();


            }
        });
        btnDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downAnimation();
                tvTod.setText("جرعت");
                showRandomDareQuestion();
                upQuestionLayoutAnimation();


            }
        });
        btnCloseQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downQuestionLayoutAnimation();
            }
        });
        btnChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvTod.getText().equals("حقیقت")) {

                    showRandomTruthQuestion();
                } else if (tvTod.getText().equals("جرعت")) {
                    showRandomDareQuestion();
                }

            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(getActivity(), SettingActivity.class), MyConstant.REQUEST_CODE);
                downAnimation();
                downQuestionLayoutAnimation();
            }
        });


    }

    private int createRandomNumber() {

        int randomNumber = 0;
        while (true) {
            randomNumber = new Random().nextInt() % 360;
            if (!randomNumberList.contains(randomNumber)) {
                randomNumberList.add(randomNumber);
                break;
            }
        }

        randomNumber *= sign;
        sign *= -1;

        return randomNumber;

    }

    private void upAnimation() {

        llTruthOrDare.animate().translationY(0).setDuration(1000).setStartDelay(2000);
        llNamesBord.animate().translationY((llTruthOrDare.getLayoutParams().height * -1) - 5).setDuration(1000).setStartDelay(2000);
    }

    private void upAnimationWithoutDelay() {
        llTruthOrDare.animate().translationY(0).setDuration(1000).setStartDelay(0);
        llNamesBord.animate().translationY((llTruthOrDare.getLayoutParams().height * -1) - 5).setDuration(1000).setStartDelay(0);
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

    private void showRandomTruthQuestion() {

        ArrayList<String> truthQuestionList = new ArrayList<>();


        if (setting.isDefaultQuestion()) {

            truthQuestionList.addAll(new Questions(getActivity()).getTruthQuestionList());
        }

        if (setting.isMYQuestion()) {

            truthQuestionList.addAll(MySharedPreferences.getInstance(getContext()).getQuestions().getMyTruthQuestionList());
        }

        if (truthQuestionList.isEmpty())
            tvQuestion.setText("هیچ سوالی انتخاب نشده است");
        else {
            if (setting.isRepeatQuestion())
                tvQuestion.setText(truthQuestionList.get(createRandomNumber(truthQuestionList.size())));

            else
                tvQuestion.setText(truthQuestionList.get(createNonRepeatRandomNumber(truthQuestionList.size(), "truth")));


        }

    }

    private void showRandomDareQuestion() {

        ArrayList<String> dareQuestionList = new ArrayList<>();

        if (setting.isDefaultQuestion()) {

            dareQuestionList.addAll(new Questions(getActivity()).getDareQuestionList());
        }
        if (setting.isMYQuestion()) {

            dareQuestionList.addAll(MySharedPreferences.getInstance(getContext()).getQuestions().getMyDareQuestionList());
        }
        if (dareQuestionList.isEmpty())
            tvQuestion.setText("هیچ سوالی انتخاب نشده است");

        else {
            if (setting.isRepeatQuestion())
                tvQuestion.setText(dareQuestionList.get(createRandomNumber(dareQuestionList.size())));

            else
                tvQuestion.setText(dareQuestionList.get(createNonRepeatRandomNumber(dareQuestionList.size(), "dare")));


        }

    }

    private int createRandomNumber(int maximum) {


        int randomNumber = new Random().nextInt() % (maximum);
        if (randomNumber < 0)
            randomNumber *= -1;

        return randomNumber;
    }

    private int createNonRepeatRandomNumber(int maximum, String listType) {

        int randomNumber;
        if (listType.equals("truth")) {

            while (true) {
                if (repetitiousTruthQuestion.size() == maximum)
                    repetitiousTruthQuestion.clear();


                randomNumber = new Random().nextInt() % (maximum);
                if (randomNumber < 0)
                    randomNumber *= -1;

                if (!repetitiousTruthQuestion.contains(randomNumber)) {
                    repetitiousTruthQuestion.add(randomNumber);
                    break;
                }
            }
        } else {

            while (true) {
                if (repetitiousDareQuestion.size() == maximum)
                    repetitiousDareQuestion.clear();


                randomNumber = new Random().nextInt() % (maximum);
                if (randomNumber < 0)
                    randomNumber *= -1;

                if (!repetitiousDareQuestion.contains(randomNumber)) {
                    repetitiousDareQuestion.add(randomNumber);
                    break;
                }
            }

        }


        return randomNumber;
    }


    public void updateSetting() {


        setting = new Setting(getContext());

        int position = setting.getPosition();


        int id = getContext().getResources().getIdentifier("bottle_" + (position + 1), "drawable", getContext().getPackageName());
        ivBottle.setBackgroundResource(id);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MyConstant.REQUEST_CODE) {

            updateSetting();

        }

    }

}
