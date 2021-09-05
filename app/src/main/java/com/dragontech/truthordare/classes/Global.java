package com.dragontech.truthordare.classes;

import android.app.Application;

import com.dragontech.truthordare.model.MyMediaPlayer;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;

public class Global extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initAppSound();
        initTapsell();
    }




    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        stopAppSound();
    }


    private void initAppSound() {

        MyMediaPlayer.createAppSound(this);
        MyMediaPlayer.createButtonSound(this);
        MyMediaPlayer.createSpinSound(this);
    }

    private void initTapsell() {

        TapsellPlus.initialize(this, MyConstant.TAPSELL_KEY, new TapsellPlusInitListener() {
            @Override
            public void onInitializeSuccess(AdNetworks adNetworks) {

            }

            @Override
            public void onInitializeFailed(AdNetworks adNetworks,
                                           AdNetworkError adNetworkError) {
            }
        });
        TapsellPlus.setGDPRConsent(this, true);
    }

    private void stopAppSound() {

        if (MyMediaPlayer.mpAppSound.isPlaying())
            MyMediaPlayer.mpAppSound.pause();
    }

}
