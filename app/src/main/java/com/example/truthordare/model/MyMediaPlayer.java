package com.example.truthordare.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.truthordare.R;

public class MyMediaPlayer {

    public static MediaPlayer mpAppSound;
    public static MediaPlayer mpBtnSound;
    public static MediaPlayer mpSpinSound;

    public static void createAppSound(Context context) {
        mpAppSound = MediaPlayer.create(context, R.raw.app_sound);
        mpAppSound.setLooping(true);
    }

    public static void createButtonSound(Context context) {
        mpBtnSound = MediaPlayer.create(context, R.raw.button_sound);

    }

    public static void createSpinSound(Context context) {
        mpSpinSound = MediaPlayer.create(context, R.raw.spin_sound);

    }

}
