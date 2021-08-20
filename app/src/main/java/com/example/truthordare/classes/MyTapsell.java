package com.example.truthordare.classes;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.Toast;

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


    public static void showStandardBanner(Activity activity, String zoneId, RelativeLayout relativeLayout,TapsellPlusBannerType bannerType) {

        TapsellPlus.requestStandardBannerAd(
                activity, zoneId,
                bannerType,
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


                        String rewardedResponseId = tapsellPlusAdModel.getResponseId();


                        TapsellPlus.showRewardedVideoAd(activity, rewardedResponseId,
                                new AdShowListener() {
                                    @Override
                                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onOpened(tapsellPlusAdModel);
                                        Toast.makeText(activity, "opened", Toast.LENGTH_SHORT).show();

                                        if(new Setting(activity).isAppSound())
                                             MyMediaPlayer.mpMainSound.pause();
                                    }

                                    @Override
                                    public void onClosed(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onClosed(tapsellPlusAdModel);
                                        if (callBackReward != null)
                                            callBackReward.myError();

                                        if(new Setting(activity).isAppSound())
                                             MyMediaPlayer.mpMainSound.start();
                                    }

                                    @Override
                                    public void onRewarded(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onRewarded(tapsellPlusAdModel);
                                        if (callBackReward != null)
                                            callBackReward.myReward();

                                    }

                                    @Override
                                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                                        super.onError(tapsellPlusErrorModel);
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
