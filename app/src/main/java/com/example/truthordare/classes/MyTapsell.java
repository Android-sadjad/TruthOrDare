package com.example.truthordare.classes;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.truthordare.interfaces.CallBackReward;
import com.example.truthordare.model.MyMediaPlayer;

import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.AdShowListener;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusBannerType;
import ir.tapsell.plus.model.TapsellPlusAdModel;
import ir.tapsell.plus.model.TapsellPlusErrorModel;

public class MyTapsell {


    public static void showStandardBanner(Activity activity, String zoneId, RelativeLayout relativeLayout) {

        TapsellPlus.requestStandardBannerAd(
                activity, zoneId,
                TapsellPlusBannerType.BANNER_320x50,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        String standardBannerResponseId = tapsellPlusAdModel.getResponseId();

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

                    @Override
                    public void error(String message) {
                    }
                });
    }

    public static void showInterstitialAd(Activity activity, String zoneId, CallBackReward callBackReward) {
        TapsellPlus.requestRewardedVideoAd(
                activity,
                zoneId,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        //Ad is ready to show
                        //Put the ad's responseId to your responseId variable
                        String rewardedResponseId = tapsellPlusAdModel.getResponseId();
                        Toast.makeText(activity, "response", Toast.LENGTH_SHORT).show();




                        TapsellPlus.showRewardedVideoAd(activity, rewardedResponseId,
                                new AdShowListener() {
                                    @Override
                                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onOpened(tapsellPlusAdModel);
                                        Toast.makeText(activity, "opened", Toast.LENGTH_SHORT).show();
                                        MyMediaPlayer.mpMainSound.pause();
                                    }

                                    @Override
                                    public void onClosed(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onClosed(tapsellPlusAdModel);
                                       callBackReward.myError();
                                       MyMediaPlayer.mpMainSound.start();
                                    }

                                    @Override
                                    public void onRewarded(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onRewarded(tapsellPlusAdModel);
                                        callBackReward.myReward();

                                    }

                                    @Override
                                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                                        super.onError(tapsellPlusErrorModel);
                                        Toast.makeText(activity, "erorrrrrrrr", Toast.LENGTH_SHORT).show();
                                    }
                                });


                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();

                    }

                });
    }


}
