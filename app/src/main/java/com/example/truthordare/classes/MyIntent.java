package com.example.truthordare.classes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;



import static android.os.Build.VERSION_CODES.R;

public class MyIntent {


    public static void shareAppIntent(Context context) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            String shareMessage = "لینک دانلود برنامه " + "جرعت یا حقیقت" + "\n\n";
            shareMessage += "http://cafebazaar.ir/app/?id=" + "com.ttp.salawatcount" + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "اشتراک گذاری برنامه"));
        } catch (Exception e) {
            Toast.makeText(context,"خطای ناشناخته", Toast.LENGTH_SHORT).show();
        }
    }

//    public static void otherAppIntent(Context context) {
//        try {
//            Intent otherAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/developer/mohammad9731"));
//            context.startActivity(otherAppIntent);
//        } catch (Exception e) {
//
//            //Toast.makeText(context, R.string.need_internet, Toast.LENGTH_SHORT).show();
//        }
//    }

    public static void commentIntent(Context context) {
        try {
            Intent commentIntent = new Intent(Intent.ACTION_EDIT, Uri.parse("bazaar://details?id="+ "com.example.truthordare.classes"));
            context.startActivity(commentIntent);
        } catch (Exception e) {
            Toast.makeText(context, "برای ثبت نظر باید اپلیکیشن بازار را نصب داشته باشید", Toast.LENGTH_LONG).show();
        }
    }

}
