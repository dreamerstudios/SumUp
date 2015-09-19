package com.example.ali_r.sumup;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.pm.ResolveInfo;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.facebook.FacebookSdk;

import com.google.ads.AdSize;

import com.google.android.gms.ads.*;
import com.google.ads.*;


import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ali-R on 29/08/2015.
 */
public class MainMenu extends MainActivity{
    //TextView asj;
    int largest;
    private AdView mAdView;

    CallbackManager callbackManager;
    ShareDialog shareDialog;


    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);

        final RadioButton x1 = (RadioButton) findViewById(R.id.play);
        //buttonEffect(x1);
        x1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainMenu.this, MainActivity.class));
            }
        });
        final RadioButton x2 = (RadioButton) findViewById(R.id.instructions);
        //buttonEffect(x2);
        x2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainMenu.this, InstructionOne.class));
            }
        });


        List<Contact> contacts = db.getAllContacts();
        int[] dataarray = new int[contacts.size() + 1];
        int i = 0;
        dataarray[0] = 0;


        for (Contact cn : contacts) {
            if((cn.getID()%2)!=0) {
                db.deleteContact(cn);
            }
            else{
            dataarray[i + 1] = Integer.parseInt(cn.getPhoneNumber());

            i++;}
        }

        largest = dataarray[0];

        for (int j = 1; j < dataarray.length; j++) {
            if (dataarray[j] > largest) {
                largest = dataarray[j];
                System.out.println("here " + largest);
            }
            //System.out.println("here " + largest);
        }

       TextView higherscore = (TextView)findViewById(R.id.highscore);
        higherscore.setText(largest + "");

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("I got a hiscore of "+largest+".")
                .setContentUrl(Uri.parse("https://play.google.com/store/search?q=addo%2081&hl=en"))
                .setContentDescription("Try ADDO 81 Challenge to beat my score!")
                .build();

        Button hiscore = new Button(this);
        //hiscore = (Button) findViewById(R.id.shareButton);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        final ShareButton shareButton = (ShareButton) findViewById(R.id.fb_share_button);

        shareButton.setShareContent(content);


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}





