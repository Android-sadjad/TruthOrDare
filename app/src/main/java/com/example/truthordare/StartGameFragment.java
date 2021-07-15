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

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class StartGameFragment extends Fragment {

    ImageView ivBottle;
    TextView tvStart;
    ImageView ivCircleBackground;
    LinearLayout llNamesBord;

    int width;
    int height;
    int sign=1;
    int currentDegree=0;

    ArrayList<Integer> randomNumberList;
    ArrayList<String> playerNameList;

    TextView[]tvNames;
    ImageView[]ivColors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        randomNumberList=new ArrayList<>(360);
        playerNameList=new ArrayList<>();

        showStartDialog();
        findViews(view);
        setSize();
        configuraion();


    }

    private void setTextAndColor() {


        for (int i=0;i<playerNameList.size();i++){

            tvNames[i].setVisibility(View.VISIBLE);
            tvNames[i].setText(playerNameList.get(i));

            ivColors[i].setVisibility(View.VISIBLE);

        }

    }


    private void showStartDialog() {

        StartDialog startDialog = new StartDialog(getContext(), new MyCallBack() {
            @Override
            public void callBackPlayerList(ArrayList<String> playerName) {
                playerNameList=playerName;
                setTextAndColor();
            }
        });




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

                int randomNumber;

                while (true){
                    randomNumber = new Random().nextInt() % 360;
                    if (!randomNumberList.contains(randomNumber)){
                        randomNumberList.add(randomNumber);
                        break;
                    }
                }


                randomNumber*=sign;
                sign*=-1;

                RotateAnimation rotate = new RotateAnimation(currentDegree, 3600 + randomNumber, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(4000);
                rotate.setFillAfter(true);

                currentDegree=randomNumber;
                ivBottle.startAnimation(rotate);
            }
        });

    }

    private void findViews(View view) {

        tvStart = view.findViewById(R.id.tv_start);
        ivBottle = view.findViewById(R.id.iv_bottle);
        ivCircleBackground = view.findViewById(R.id.iv_circle_background);
        llNamesBord = view.findViewById(R.id.ll_names_and_color);

        tvNames=new TextView[9];
        ivColors=new ImageView[9];

        tvNames[0]=view.findViewById(R.id.tv_name_1);
        tvNames[1]=view.findViewById(R.id.tv_name_2);
        tvNames[2]=view.findViewById(R.id.tv_name_3);
        tvNames[3]=view.findViewById(R.id.tv_name_4);
        tvNames[4]=view.findViewById(R.id.tv_name_5);
        tvNames[5]=view.findViewById(R.id.tv_name_6);
        tvNames[6]=view.findViewById(R.id.tv_name_7);
        tvNames[7]=view.findViewById(R.id.tv_name_8);
        tvNames[8]=view.findViewById(R.id.tv_name_9);

        ivColors[0]=view.findViewById(R.id.iv_color_1);
        ivColors[1]=view.findViewById(R.id.iv_color_2);
        ivColors[2]=view.findViewById(R.id.iv_color_3);
        ivColors[3]=view.findViewById(R.id.iv_color_4);
        ivColors[4]=view.findViewById(R.id.iv_color_5);
        ivColors[5]=view.findViewById(R.id.iv_color_6);
        ivColors[6]=view.findViewById(R.id.iv_color_7);
        ivColors[7]=view.findViewById(R.id.iv_color_8);
        ivColors[8]=view.findViewById(R.id.iv_color_9);



    }
}
