package com.example.truthordare.classes;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.truthordare.R;

public class MyIntent {


    public static void shareAppIntent(Context context) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            String shareMessage = context.getString(R.string.app_link_download)
                    + context.getString(R.string.app_name) + "\n\n"
                    + context.getString(R.string.app_id_caffe_bazaar)
                    + context.getPackageName() + "\n\n";

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_app)));
        } catch (Exception e) {
            Toast.makeText(context, context.getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
        }
    }

    public static void otherAppIntent(Context context) {
        try {
            Intent otherAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.developer_caffe_bazaar)));
            context.startActivity(otherAppIntent);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void commentIntent(Context context) {
        try {
            Uri commentUri = Uri.parse(context.getString(R.string.comment_caffe_bazaar) + context.getPackageName());
            Intent commentIntent = new Intent(Intent.ACTION_EDIT, commentUri);
            context.startActivity(commentIntent);
        } catch (Exception e) {
            Toast.makeText(context, context.getString(R.string.need_install_caffe_bazaar), Toast.LENGTH_LONG).show();
        }
    }

    public static void instagramPageIntent(Context context) {

        Uri instaPageUri = Uri.parse(context.getString(R.string.instagram_page_address));

        try {
            Intent instagramIntent = new Intent(Intent.ACTION_VIEW, instaPageUri);
            instagramIntent.setPackage(context.getString(R.string.instagram_package_name));
            context.startActivity(instagramIntent);

        } catch (ActivityNotFoundException e) {
            Intent instagramIntent = new Intent(Intent.ACTION_VIEW, instaPageUri);
            context.startActivity(instagramIntent);
        } catch (Exception e) {
            Toast.makeText(context, context.getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
        }
    }

    public static void emailIntent(Context context) {

        Intent emailIntent =new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,context.getString(R.string.email_address));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,context.getString(R.string.app_name));

        try {
            context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.send_email)));
        } catch (Exception e) {
            Toast.makeText(context, context.getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
        }
    }

}
