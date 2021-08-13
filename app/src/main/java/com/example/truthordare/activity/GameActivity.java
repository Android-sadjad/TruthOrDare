package com.example.truthordare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truthordare.R;
import com.example.truthordare.classes.MyConstant;
import com.example.truthordare.classes.MySharedPreferences;
import com.example.truthordare.model.Questions;
import com.example.truthordare.model.Setting;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

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



    ArrayList<Integer> randomNumberList;
    ArrayList<String> playerNameList;

    ArrayList<Integer> repetitiousTruthQuestion;
    ArrayList<Integer> repetitiousDareQuestion;

    TextView[] tvNames;
    ImageView[] ivColors;

    Setting setting;

    MediaPlayer mpRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_start_game);





        init();
        findViews();
        setViewSize();
        setViewTranslation();
        setTextAndColorAndBackground();
        configuration();
        updateSetting();


    }


    private void init() {

        playerNameList = getIntent().getExtras().getStringArrayList(MyConstant.PLAYER_NAME_LIST);

        screenWidth = MyConstant.getScreenWidth();
        screenHeight = MyConstant.getScreenHeight();

        randomNumberList = new ArrayList<>();

        tvNames = new TextView[playerNameList.size()];
        ivColors = new ImageView[playerNameList.size()];

        setting = new Setting(this);

        repetitiousTruthQuestion = new ArrayList<>();
        repetitiousDareQuestion = new ArrayList<>();

        mpRound = MediaPlayer.create(this, R.raw.rounding);


    }

    private void findViews() {




        ivCircleBackground = findViewById(R.id.iv_circle_background);
        ivBottle = findViewById(R.id.iv_bottle);

        llQuestions = findViewById(R.id.ll_question);
        llNamesBord = findViewById(R.id.ll_names_board);
        llTruthOrDare = findViewById(R.id.ll_truth_dare);

        btnDare = findViewById(R.id.btn_dare);
        btnTruth = findViewById(R.id.btn_truth);

        tvTod = findViewById(R.id.tv_tod);
        tvQuestion = findViewById(R.id.tv_qustion);
        btnChangeQuestion = findViewById(R.id.btn_change_question);
        btnCloseQuestion = findViewById(R.id.btn_close_question);


        for (int i = 0; i < tvNames.length; i++) {

            int id = getResources().getIdentifier("tv_name_" + (i + 1), "id", getPackageName());
            tvNames[i] = findViewById(id);

            id = getResources().getIdentifier("iv_color_" + (i + 1), "id", getPackageName());
            ivColors[i] = findViewById(id);
        }


    }

    private void setViewSize() {


        ivCircleBackground.getLayoutParams().width = screenWidth * 80 / 100;
        ivCircleBackground.getLayoutParams().height = screenWidth * 80 / 100;

        llNamesBord.getLayoutParams().height = screenHeight * 30 / 100;
        llQuestions.getLayoutParams().height = screenHeight * 35 / 100;
        llTruthOrDare.getLayoutParams().height = screenHeight * 9 / 100;



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


        int circleBackgroundId = getResources().getIdentifier("bg_circle_" + playerNameList.size(), "drawable", getPackageName());
        ivCircleBackground.setBackgroundResource(circleBackgroundId);


    }

    private void configuration() {

        ivCircleBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mpRound.isPlaying()) {
                    return;

                }

                if (setting.isCircleSound()) {
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
                tvTod.setText(R.string.truth);
                showRandomTruthQuestion();

                upQuestionLayoutAnimation();


            }
        });
        btnDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downAnimation();
                tvTod.setText(R.string.dare);
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

                if (tvTod.getText().toString().equals(getString(R.string.truth))) {

                    showRandomTruthQuestion();
                } else if (tvTod.getText().equals(getString(R.string.dare))) {
                    showRandomDareQuestion();
                }

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

            truthQuestionList.addAll(new Questions(this).getTruthQuestionList());
        }

        if (setting.isMYQuestion()) {

            truthQuestionList.addAll(MySharedPreferences.getInstance(this).getQuestions().getMyTruthQuestionList());
        }

        if (truthQuestionList.isEmpty())
            tvQuestion.setText(R.string.no_question_selected);
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

            dareQuestionList.addAll(new Questions(this).getDareQuestionList());
        }
        if (setting.isMYQuestion()) {

            dareQuestionList.addAll(MySharedPreferences.getInstance(this).getQuestions().getMyDareQuestionList());
        }
        if (dareQuestionList.isEmpty())
            tvQuestion.setText(R.string.no_question_selected);

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
        if (listType.equals(R.string.truth)) {

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


        setting = new Setting(this);

        int position = setting.getPosition();


        int id =getResources().getIdentifier("bottle_" + (position + 1), "drawable", getPackageName());
        ivBottle.setBackgroundResource(id);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        Log.i("aaaa","game");
        Toast.makeText(GameActivity.this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();

        if (requestCode == MyConstant.REQUEST_CODE) {

            updateSetting();

        }

    }
    @Override
    protected void onStop() {
        Log.i("aaaaa","stop");
        super.onStop();
    }



}