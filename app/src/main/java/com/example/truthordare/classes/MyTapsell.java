package com.example.truthordare.classes;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.truthordare.R;
import com.example.truthordare.interfaces.CallBackReward;
import com.example.truthordare.model.MyMediaPlayer;
import com.example.truthordare.model.Setting;

import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.AdShowListener;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusBannerType;
import ir.tapsell.plus.model.TapsellPlusAdModel;
import ir.tapsell.plus.model.TapsellPlusErrorModel;

public class MyTapsell {

    final static int MAX_REQUEST_NUMBER = 10;
    static int standardAdCounter = 0;
    static int interstitialAdCounter = 0;

    public static void showStandardBanner(Activity activity, String zoneId, RelativeLayout relativeLayout, TapsellPlusBannerType bannerType) {


        TapsellPlus.requestStandardBannerAd(
                activity, zoneId,
                bannerType,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        standardAdCounter = MAX_REQUEST_NUMBER;
                        String standardBannerResponseId = tapsellPlusAdModel.getResponseId();

                        requestStandardBanner(activity, standardBannerResponseId, relativeLayout);
                    }

                    @Override
                    public void error(String message) {

                        while (standardAdCounter++ < MAX_REQUEST_NUMBER) {
                            showStandardBanner(activity, zoneId, relativeLayout, bannerType);
                        }
                    }
                });
    }

    private static void requestStandardBanner(Activity activity, String standardBannerResponseId, ViewGroup relativeLayout) {

        TapsellPlus.showStandardBannerAd(activity, standardBannerResponseId,
                relativeLayout,
                new AdShowListener() {
                    @Override
                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onOpened(tapsellPlusAdModel);

                    }

                    @Override
                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                        super.onError(tapsellPlusErrorModel);

                    }
                });

    }

    public static void showInterstitialAd(Activity activity, String zoneId, CallBackReward callBackReward) {

        Toast.makeText(activity, activity.getString(R.string.please_wait), Toast.LENGTH_SHORT).show();
        TapsellPlus.requestRewardedVideoAd(
                activity,
                zoneId,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        interstitialAdCounter = MAX_REQUEST_NUMBER;
                        String rewardedResponseId = tapsellPlusAdModel.getResponseId();

                        requestInterstitialBanner(activity, rewardedResponseId, callBackReward);

                    }

                    @Override
                    public void error(String message) {

                        while (interstitialAdCounter++ < MAX_REQUEST_NUMBER) {
                            showInterstitialAd(activity, zoneId, callBackReward);
                        }

                    }

                });
    }

    private static void requestInterstitialBanner(Activity activity, String rewardedResponseId, CallBackReward callBackReward) {

        TapsellPlus.showRewardedVideoAd(activity, rewardedResponseId,
                new AdShowListener() {
                    @Override
                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onOpened(tapsellPlusAdModel);
                        if(callBackReward==null){
                            Toast.makeText(activity, activity.getString(R.string.hemayat_text), Toast.LENGTH_LONG).show();
                        }

                        if (new Setting(activity).isAppSound())
                            MyMediaPlayer.mpAppSound.pause();
                    }

                    @Override
                    public void onClosed(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onClosed(tapsellPlusAdModel);
                        if (callBackReward != null)
                            callBackReward.onError();
                        else
                            Toast.makeText(activity, R.string.thank_you, Toast.LENGTH_LONG).show();

                        if (new Setting(activity).isAppSound())
                            MyMediaPlayer.mpAppSound.start();
                    }

                    @Override
                    public void onRewarded(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onRewarded(tapsellPlusAdModel);
                        if (callBackReward != null)
                            callBackReward.onReward();


                    }

                    @Override
                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                        super.onError(tapsellPlusErrorModel);
                        Toast.makeText(activity, activity.getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();


                    }
                });

    }
}
