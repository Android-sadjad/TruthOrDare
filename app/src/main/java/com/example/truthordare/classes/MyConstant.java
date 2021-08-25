package com.example.truthordare.classes;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.truthordare.R;

public class MyConstant {


    public static final int SPLASH_SCREEN_DURATION = 3000;
    public static final int BOTTLE_NUMBER = 27;
    public static final int REQUEST_CODE = 1;
    public static final int MAX_QUESTION_NUMBER = 50;

    public static final String SHARED_PREFERENCES_NAME="question_list";
    public static final String SETTING="setting";

    public static final String PLAYER_NAME_LIST = "player_name_list";
    public static final String LIST_TYPE ="list_type" ;
    public static final String TRUTH = "truth";
    public static final String DARE = "dare";
    public static final String MY_LIST = "my_list";
    public static final String DEFAULT_LIST = "default_list";
    public static final String MY_TRUTH = "my_truth";
    public static final String MY_DARE = "my_dare";
    public static final String QUESTION_NUMBER = "question_number";


    public static final String TAPSELL_KEY = "kqeejibsfcireicosnsakmkrfpcspehismbolbqqmdfjetsrbdqjtphnhmendqotnreedh";
    public static final String STANDARD_BANNER_HOME_PAGE = "61026ec203fb244413fcd8ee";
    public static final String interstitial_BANNER = "610274f524c82d5311e6338c";
    public static final String reward_based = "61056351bd57ce68f9fa9aff";



    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        boolean connect = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (!connect)
            Toast.makeText(context, R.string.need_internet, Toast.LENGTH_SHORT).show();

        return connect;
    }

}
