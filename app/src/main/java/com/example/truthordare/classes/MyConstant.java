package com.example.truthordare.classes;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.truthordare.R;

public class MyConstant {

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

        boolean connect=activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if(!connect)
            Toast.makeText(context, R.string.need_internet, Toast.LENGTH_SHORT).show();

        return connect;
    }

    public static final int BOTTLE_NUMBER=10;

    public static final String TAPSELL_KEY="kqeejibsfcireicosnsakmkrfpcspehismbolbqqmdfjetsrbdqjtphnhmendqotnreedh";

}
