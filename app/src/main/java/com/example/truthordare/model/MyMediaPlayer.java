package com.example.truthordare.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.truthordare.R;

public class MyMediaPlayer {

public static MediaPlayer mediaPlayer;

public static void createMediaPlayer(Context context){
    mediaPlayer=MediaPlayer.create(context, R.raw.dark_shadow);
   mediaPlayer.setLooping(true);

}



}
