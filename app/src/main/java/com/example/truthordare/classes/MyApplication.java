package com.example.truthordare.classes;

import android.app.Application;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;

public class MyApplication extends Application {

    @Override
    public void onCreate() {

        TapsellPlus.initialize(this, MyConstant.TAPSELL_KEY,new TapsellPlusInitListener() {
            @Override
            public void onInitializeSuccess(AdNetworks adNetworks) {

            }

            @Override
            public void onInitializeFailed(AdNetworks adNetworks,
                    AdNetworkError adNetworkError) {
            }
        });
        super.onCreate();
    }
}
