package com.example.truthordare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class StartGameFragment extends Fragment {

    ImageView ivBottle;
    TextView tvStart;
    ImageView ivCircleBackground;
    LinearLayout llNamesBord;

    int width;
    int height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showStartDialog();
        findViews(view);
        setSize();
        configuraion();
    }

    private void showStartDialog() {

        StartDialog startDialog = new StartDialog(getContext());
        startDialog.show();
    }

    private void setSize() {

        width = MyConstant.getScreenWidth();
        height = MyConstant.getScreenHeight();

        int radius = width * 80 / 100;

        ivCircleBackground.getLayoutParams().width = radius;
        ivCircleBackground.getLayoutParams().height = radius;

        llNamesBord.getLayoutParams().height = height * 30 / 100;
    }

    private void configuraion() {

        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int randomNumber = new Random().nextInt() % 360;
                randomNumber = (randomNumber >= 0) ? randomNumber : randomNumber * -1;

                RotateAnimation rotate = new RotateAnimation(0, 1800 - randomNumber, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(4000);
                rotate.setFillAfter(true);

                ivBottle.startAnimation(rotate);


            }
        });

    }

    private void findViews(View view) {

        tvStart = view.findViewById(R.id.tv_start);
        ivBottle = view.findViewById(R.id.iv_bottle);
        ivCircleBackground = view.findViewById(R.id.iv_circle_background);
        llNamesBord = view.findViewById(R.id.ll_names_and_color);
    }
}
