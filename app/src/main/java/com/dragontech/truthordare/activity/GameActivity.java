package com.dragontech.truthordare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dragontech.truthordare.R;
import com.dragontech.truthordare.classes.MyConstant;
import com.dragontech.truthordare.classes.MyIntent;
import com.dragontech.truthordare.classes.MySharedPreferences;
import com.dragontech.truthordare.classes.MyTapsell;
import com.dragontech.truthordare.classes.UseFullMethod;
import com.dragontech.truthordare.dialog.AboutUsDialog;
import com.dragontech.truthordare.dialog.CommentDialog;
import com.dragontech.truthordare.model.MyMediaPlayer;
import com.dragontech.truthordare.model.Questions;
import com.dragontech.truthordare.model.Setting;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    PieChart pieChart;
    int turnIndex = 0;
    private int screenWidth;
    private int screenHeight;
    private int sign = 1;
    private int currentDegree = 0;
    private boolean isUp = false;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ImageView ivBottle;

    private LinearLayout llNamesBord;
    private LinearLayout llTruthOrDare;
    private ConstraintLayout clQuestions;

    private TextView tvDare;
    private TextView tvTruth;
    private TextView tvChangeQuestion;
    private TextView tvCloseQuestion;
    private TextView tvTod;
    private TextView tvQuestion;
    private TextView tvToolbarTitle;

    private Questions questions;
    private Setting setting;

    private ArrayList<Integer> randomNumberList;
    private ArrayList<String> playerNameList;
    private ArrayList<Integer> repetitiousTruthQuestion;
    private ArrayList<Integer> repetitiousDareQuestion;

    private TextView[] tvNames;
    private ImageView[] ivColors;

    private Animation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        init();
        findViews();
        drawPieChart();
        setUpMenu();
        setUpView();
        configuration();
        applySetting();


    }

    @Override
    protected void onResume() {
        super.onResume();

        setting = new Setting(GameActivity.this);
        if (setting.isAppSound() && !MyMediaPlayer.mpAppSound.isPlaying()) {

            MyMediaPlayer.mpAppSound.start();
        }
    }


    private void init() {

        playerNameList = getIntent().getExtras().getStringArrayList(MyConstant.PLAYER_NAME_LIST);

        screenWidth = UseFullMethod.getScreenWidth();
        screenHeight = UseFullMethod.getScreenHeight();

        randomNumberList = new ArrayList<>();

        tvNames = new TextView[playerNameList.size()];
        ivColors = new ImageView[playerNameList.size()];

        setting = new Setting(this);
        questions = new Questions(this);

        repetitiousTruthQuestion = new ArrayList<>();
        repetitiousDareQuestion = new ArrayList<>();

        scaleAnimation = AnimationUtils.loadAnimation(GameActivity.this, R.anim.scale_x_animation);
    }

    private void findViews() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        ivBottle = findViewById(R.id.iv_bottle);
        pieChart = findViewById(R.id.pie_chart);

        clQuestions = findViewById(R.id.cl_question);
        llNamesBord = findViewById(R.id.ll_names_board);
        llTruthOrDare = findViewById(R.id.ll_truth_dare);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);


        tvDare = findViewById(R.id.tv_dare);
        tvTruth = findViewById(R.id.tv_truth);

        tvTod = findViewById(R.id.tv_tod);
        tvQuestion = findViewById(R.id.tv_question);
        tvChangeQuestion = findViewById(R.id.tv_change_question);
        tvCloseQuestion = findViewById(R.id.tv_close_question);


        for (int i = 0; i < tvNames.length; i++) {

            int id = getResources().getIdentifier("tv_name_" + (i + 1), "id", getPackageName());
            tvNames[i] = findViewById(id);

            id = getResources().getIdentifier("iv_color_" + (i + 1), "id", getPackageName());
            ivColors[i] = findViewById(id);
        }


    }

    private void drawPieChart() {

        PieData pieData;
        PieDataSet pieDataSet;
        List<PieEntry> pieEntryList = new ArrayList<>();

        for (int i = 0; i < playerNameList.size(); i++) {
            pieEntryList.add(new PieEntry(1));
        }

        int[] colors = new int[]{
                getResources().getColor(R.color.bg_blue),
                getResources().getColor(R.color.bg_red),
                getResources().getColor(R.color.bg_green),
                getResources().getColor(R.color.bg_pink),
                getResources().getColor(R.color.bg_purple),
                getResources().getColor(R.color.bg_yellow),
                getResources().getColor(R.color.bg_brown),
                getResources().getColor(R.color.bg_orange),
                getResources().getColor(R.color.bg_gray)
        };


        pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setColors(colors);
        pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();

        pieChart.setRotationEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);


    }

    private void setUpMenu() {
        navigationView.getMenu().removeItem(R.id.nav_exit);
    }

    private void setUpView() {

        setViewSize();
        setViewTranslation();
        setTextAndColorAndBackground();

        tvQuestion.setMovementMethod(new ScrollingMovementMethod());
        tvToolbarTitle.setVisibility(View.GONE);
    }

    private void setViewSize() {


        pieChart.getLayoutParams().width = screenWidth * 80 / 100;
        pieChart.getLayoutParams().height = screenWidth * 80 / 100;

        llNamesBord.getLayoutParams().height = screenHeight * 27 / 100;
        clQuestions.getLayoutParams().height = screenHeight * 37 / 100;

        llTruthOrDare.getLayoutParams().height = screenHeight * 10 / 100;
        tvTruth.getLayoutParams().height = screenHeight * 10 / 100;
        tvDare.getLayoutParams().height = screenHeight * 10 / 100;


    }

    private void setViewTranslation() {


        clQuestions.setTranslationY(clQuestions.getLayoutParams().height);
        llTruthOrDare.setTranslationY(llTruthOrDare.getLayoutParams().height);
    }

    private void setTextAndColorAndBackground() {


        for (int i = 0; i < tvNames.length; i++) {

            tvNames[i].setVisibility(View.VISIBLE);
            tvNames[i].setText(playerNameList.get(i));
            tvNames[i].setSelected(true);

            ivColors[i].setVisibility(View.VISIBLE);

            String shapeName = "bg_circle_color_" + (i + 1);
            int circleShapeId = getResources()
                    .getIdentifier(shapeName, "drawable", getPackageName());
            ivColors[i].setBackgroundResource(circleShapeId);

        }


    }


    public void onClick(View view) {

        if (setting.isButtonSound()) {
            MyMediaPlayer.mpBtnSound.start();
        }


        switch (view.getId()) {

            case R.id.iv_setting:
                startActivityForResult(new Intent(GameActivity.this, SettingActivity.class), MyConstant.REQUEST_CODE);
                break;

            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;

        }
    }

    public void navItemsOnClick(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.nav_home_page:
                setResult(MyConstant.FINISH_CODE);
                finish();
                break;
            case R.id.nav_my_question:
                openQuestionActivity(MyConstant.MY_LIST);
                break;

            case R.id.nav_default_question:
                openQuestionActivity(MyConstant.DEFAULT_LIST);
                break;

            case R.id.nav_hemayat:
                showInterstitialAdvertising();
                break;

            case R.id.nav_comment:
                new CommentDialog(this).show();
                break;

            case R.id.nav_share_app:
                MyIntent.shareAppIntent(GameActivity.this);
                break;

            case R.id.nav_about_us:
                new AboutUsDialog(this).show();
                break;

            case R.id.nav_other_app:
                if (UseFullMethod.isNetworkAvailable(this))
                    MyIntent.otherAppIntent(GameActivity.this);
                break;
        }

        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    private void showInterstitialAdvertising() {
        if (UseFullMethod.isNetworkAvailable(this))
            MyTapsell.showInterstitialAd(GameActivity.this, MyConstant.INTERSTITIAL_BANNER, null);
    }

    private void openQuestionActivity(String listName) {

        Intent intent = new Intent(GameActivity.this, QuestionActivity.class);
        intent.putExtra(MyConstant.LIST_TYPE, listName);
        startActivity(intent);

    }

    private void configuration() {

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (MyMediaPlayer.mpSpinSound.isPlaying()) {
                    return;
                }

                if (setting.isSpinSound()) {
                    MyMediaPlayer.mpSpinSound.start();
                }


                int randomNumber = createRandomNumber();

                int nextDegree = randomNumber + currentDegree % 360;

                RotateAnimation rotate = new RotateAnimation(currentDegree,
                        MyConstant.ROTATE_BOTTLE_NUMBER + nextDegree,
                        Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                rotate.setDuration(MyMediaPlayer.mpSpinSound.getDuration());
                rotate.setFillAfter(true);

                currentDegree = nextDegree;
                ivBottle.startAnimation(rotate);

                showPlayerTurn(currentDegree);
                if (llNamesBord.getTranslationY() == 0)
                    upAnimation(MyConstant.UP_ANIM_DELAY);
                else {
                    downAnimation();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            upAnimation(0);
                        }
                    }, MyConstant.UP_ANIM_DELAY);
                }

                if (clQuestions.getTranslationY() == 0) {
                    downQuestionLayoutAnimation();
                }


            }
        });
        tvTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downAnimation();
                tvTod.setText(R.string.truth);

                showRandomTruthQuestion();
                upQuestionLayoutAnimation();
            }
        });
        tvDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downAnimation();
                tvTod.setText(R.string.dare);
                showRandomDareQuestion();
                upQuestionLayoutAnimation();
            }
        });
        tvCloseQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivColors[turnIndex].clearAnimation();
                startScaleAnimation(v);
                downQuestionLayoutAnimation();
            }
        });
        tvChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScaleAnimation(v);

                if (tvTod.getText().toString().equals(getString(R.string.truth))) {
                    showRandomTruthQuestion();
                } else if (tvTod.getText().equals(getString(R.string.dare))) {
                    showRandomDareQuestion();
                }

            }
        });

    }

    private void showPlayerTurn(int degree) {

        ivColors[turnIndex].clearAnimation();

        degree %= 360;
        if (degree < 0)
            degree += 360;


        int sliceSize = 360 /  playerNameList.size();

        if (degree <= sliceSize)
            turnIndex = 0;
        else if (degree <= sliceSize * 2 && sliceSize * 2 <= 360)
            turnIndex = 1;
        else if (degree <= sliceSize * 3 && sliceSize * 3 <= 360)
            turnIndex = 2;
        else if (degree <= sliceSize * 4 && sliceSize * 4 <= 360)
            turnIndex = 3;
        else if (degree <= sliceSize * 5 && sliceSize * 5 <= 360)
            turnIndex = 4;
        else if (degree <= sliceSize * 6 && sliceSize * 6 <= 360)
            turnIndex = 5;
        else if (degree <= sliceSize * 7 && sliceSize * 7 <= 360)
            turnIndex = 6;
        else if (degree <= sliceSize * 8 && sliceSize * 8 <= 360)
            turnIndex = 7;
        else if (sliceSize * 9 <= 360)
            turnIndex = 8;


        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(MyConstant.ALPHA_ANIM_DURATION);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ivColors[turnIndex].startAnimation(alphaAnimation);
            }
        }, MyMediaPlayer.mpSpinSound.getDuration());


    }

    private int createRandomNumber() {

        int randomNumber = 0;
        while (true) {
            randomNumber = new Random().nextInt() % (MyConstant.MAX_RANDOM_NUMBER);

            if (randomNumberList.size() == MyConstant.MAX_RANDOM_NUMBER)
                randomNumberList.clear();

            if (!randomNumberList.contains(randomNumber)) {
                randomNumberList.add(randomNumber);
                break;
            }
        }

        randomNumber *= sign;
        sign *= -1;


        return randomNumber;

    }

    private void upAnimation(int delay) {

        llTruthOrDare.animate().translationY(0).setDuration(MyConstant.UP_ANIM_DURATION).setStartDelay(delay);
        llNamesBord.animate().translationY((llTruthOrDare.getLayoutParams().height * -1)).setDuration(MyConstant.UP_ANIM_DURATION).setStartDelay(delay);
    }

    private void downAnimation() {

        llTruthOrDare.animate().translationY(llTruthOrDare.getLayoutParams().height)
                .setDuration(MyConstant.DOWN_ANIM_DURATION).setStartDelay(0);


        llNamesBord.animate().translationY(0).setDuration(MyConstant.DOWN_ANIM_DURATION).setStartDelay(0);

    }

    private void upQuestionLayoutAnimation() {

        isUp = true;
        clQuestions.animate().translationY(0).setDuration(MyConstant.UP_ANIM_DURATION).setStartDelay(0);

    }

    private void downQuestionLayoutAnimation() {

        isUp = false;
        clQuestions.animate().translationY(clQuestions.getLayoutParams().height).setDuration(MyConstant.UP_ANIM_DURATION).setStartDelay(0);

    }

    private void showRandomTruthQuestion() {

        ArrayList<String> truthQuestionList = new ArrayList<>();


        if (setting.isDefaultQuestion()) {

            ArrayList<String> truthList = (questions.getTruthQuestionList());

            for (int i = 0; i < questions.getQuestionNumber(); i++) {
                truthQuestionList.add(truthList.get(i));
            }
        }

        if (setting.isMYQuestion()) {

            truthQuestionList.addAll(MySharedPreferences.getInstance(this).getQuestions().getMyTruthQuestionList());
        }

        if (truthQuestionList.isEmpty()) {
            tvQuestion.setText(R.string.no_question_selected);
            if (setting.isMYQuestion() && !setting.isDefaultQuestion())
                Toast.makeText(this, R.string.add_your_list_question, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, R.string.select_question_list, Toast.LENGTH_SHORT).show();
        } else {
            if (setting.isRepeatQuestion())
                tvQuestion.setText(truthQuestionList.get(createRandomNumber(truthQuestionList.size())));

            else
                tvQuestion.setText(truthQuestionList.get(createNonRepeatRandomNumber(truthQuestionList.size(), "truth")));

        }

    }

    private void showRandomDareQuestion() {

        ArrayList<String> dareQuestionList = new ArrayList<>();


        if (setting.isDefaultQuestion()) {

            ArrayList<String> dareList = (questions.getDareQuestionList());

            for (int i = 0; i < questions.getQuestionNumber(); i++) {
                dareQuestionList.add(dareList.get(i));
            }

        }
        if (setting.isMYQuestion()) {

            dareQuestionList.addAll(MySharedPreferences.getInstance(this).getQuestions().getMyDareQuestionList());
        }
        if (dareQuestionList.isEmpty()) {
            tvQuestion.setText(R.string.no_question_selected);
            if (setting.isMYQuestion() && !setting.isDefaultQuestion())
                Toast.makeText(this, R.string.add_your_list_question, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, R.string.select_question_list, Toast.LENGTH_SHORT).show();
        } else {
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
        if (listType.equals(getString(R.string.truth))) {

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

    public void applySetting() {


        setting = new Setting(this);

        int position = setting.getSelectedPhotoPosition();


        int id = getResources().getIdentifier("bottle_" + (position + 1), "drawable", getPackageName());
        ivBottle.setImageResource(id);

        if (!setting.isRepeatQuestion()) {
            repetitiousDareQuestion.clear();
            repetitiousTruthQuestion.clear();
        }


    }

    private void startScaleAnimation(View v) {
        v.startAnimation(scaleAnimation);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MyConstant.REQUEST_CODE) {
            applySetting();
        }

    }


    @Override
    public void onBackPressed() {
        if (isUp) {
            downQuestionLayoutAnimation();
        } else {
            finish();
        }
    }
}