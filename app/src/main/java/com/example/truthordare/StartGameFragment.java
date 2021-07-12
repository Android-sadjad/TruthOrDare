package com.example.truthordare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class StartGameFragment extends Fragment {

    ImageView ivBottle;
    ImageView ivStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_game,container,false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        configuraion();

    }

    private void configuraion() {

        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int randomNumber=new Random().nextInt()%360;
                randomNumber= (randomNumber>=0)?randomNumber:randomNumber*-1;


                Toast.makeText(getContext(), String.valueOf(randomNumber), Toast.LENGTH_SHORT).show();

                RotateAnimation rotate = new RotateAnimation(0, 1800-randomNumber, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(4000);
                rotate.setFillAfter(true);

                ivBottle.startAnimation(rotate);



            }
        });

    }

    private void findViews(View view) {

        ivStart=view.findViewById(R.id.iv_start);
        ivBottle=view.findViewById(R.id.iv_bottle);
    }
}
