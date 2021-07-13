package com.example.truthordare;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Display;
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
    ImageView ivCircleBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_game,container,false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        findViews(view);
        setSize();
        configuraion();
        drawss();

    }

    private void setSize() {
        Display displaySize = getActivity().getWindowManager().getDefaultDisplay();
        Point size=new Point();
        displaySize.getSize(size);

        int width=size.x*80/100;
        ivCircleBackground.getLayoutParams().width=width;
        ivCircleBackground.getLayoutParams().height=width;

    }

    private void drawss() {
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        Paint paint3 = new Paint();
        Paint paint4 = new Paint();
        Paint paint5 = new Paint();
        final RectF rect = new RectF();
        int mRadius = 130;
        //Example values
        rect.set(getWidth()/2- mRadius, getHeight()/2 - mRadius, getWidth()/2 + mRadius, getHeight()/2 + mRadius);
        paint1.setColor(Color.GREEN);
        paint1.setStrokeWidth(mRadius/2);
        paint1.setAntiAlias(true);
        paint1.setStrokeCap(Paint.Cap.BUTT);
        paint1.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setStrokeWidth(mRadius/2);
        paint2.setAntiAlias(true);
        paint2.setStrokeCap(Paint.Cap.BUTT);
        paint2.setStyle(Paint.Style.STROKE);
        paint3.setColor(Color.BLUE);
        paint3.setStrokeWidth(5);
        paint3.setAntiAlias(true);
        paint3.setStrokeCap(Paint.Cap.BUTT);
        paint3.setStyle(Paint.Style.STROKE);
        Canvas canvas=new Canvas();
        canvas.drawArc(rect, 0, 60, false, paint1);
        canvas.drawArc(rect, 60, 60, false, paint2);
        canvas.drawArc(rect, 120, 60, false, paint1);
        canvas.drawArc(rect, 180, 60, false, paint2);
        canvas.drawArc(rect, 240, 60, false, paint1);
        canvas.drawArc(rect, 300, 60, false, paint2);

        canvas.drawLine(getWidth()/2,
                getHeight()/2, getWidth()/2-mRadius/2, getHeight()/2-mRadius/2,paint3);

        canvas.drawLine(getWidth()/2,
                getHeight()/2, getWidth()/2+mRadius/2, getHeight()/2-mRadius/2,paint3);

        canvas.drawLine(getWidth()/2,
                getHeight()/2, getWidth()/2-mRadius/2, getHeight()/2+mRadius/2,paint3);

        canvas.drawLine(getWidth()/2,
                getHeight()/2, getWidth()/2+mRadius/2, getHeight()/2+mRadius/2,paint3);

        canvas.drawLine(getWidth()/2,
                getHeight()/2, getWidth()/2-mRadius/4-mRadius/2, getHeight()/2,paint3);

        canvas.drawLine(getWidth()/2,
                getHeight()/2, getWidth()/2+mRadius/4+mRadius/2, getHeight()/2,paint3);

        paint4.setColor(Color.BLACK);

        canvas.drawCircle(getWidth()/2, getHeight()/2, mRadius/2, paint4);

        paint5.setColor(Color.YELLOW);
        paint5.setStrokeWidth(3);
        paint5.setAntiAlias(true);
        paint5.setStrokeCap(Paint.Cap.BUTT);
        paint5.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2, getHeight()/2, mRadius/2, paint5);
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
        ivCircleBackground=view.findViewById(R.id.iv_circle_background);
    }


    public int getWidth(){



        return 300;
    }

    public int getHeight(){



        return 300;
    }
}
