package com.example.android.jjnic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.scheme;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tx = (TextView) findViewById(R.id.textview1);
        TextView tx2 = (TextView) findViewById(R.id.textview2);
        TextView tx3 = (TextView) findViewById(R.id.textview3);
        TextView tx4 = (TextView) findViewById(R.id.textview4);
        TextView tx5 = (TextView) findViewById(R.id.textview5);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/comica2.ttf");
        Typeface headerfont = Typeface.createFromAsset(getAssets(), "fonts/bada.TTF");

        tx.setTypeface(custom_font);
        tx2.setTypeface(custom_font);
        tx3.setTypeface(custom_font);
        tx4.setTypeface(custom_font);
        tx5.setTypeface(headerfont);


    }




    public static String FACEBOOK_URL = "https://www.facebook.com/popay.erg/";
    public static String FACEBOOK_PAGE_ID = "467992566553817";

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
//whatever instagram
public void onClickInsta(View v) {
    Intent likeIng = new Intent(Intent.ACTION_VIEW);
    Uri uri = Uri.parse("http://instagram.com/_u/skasskase");
    Intent i = new Intent(Intent.ACTION_VIEW, uri);
    i.setPackage("com.instagram.android");
    try {
        startActivity(i);

        } catch (ActivityNotFoundException e) {

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/skasskase")));
        }

}

    public void onClickFcbk(View v) {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
        }


    public void onClickDial(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "2130044491"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



}}
