package com.example.truthordare.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.truthordare.R;

public class MyMediaPlayer {

    public static MediaPlayer mpMainSound;
    public static MediaPlayer mpBtnSound;

    public static void createMediaPlayer(Context context) {
        mpMainSound = MediaPlayer.create(context, R.raw.dark_shadow);
        mpMainSound.setLooping(true);

    }


    public static void createButtonSound(Context context) {

        mpBtnSound = MediaPlayer.create(context, R.raw.btn_sound);


    }


}
