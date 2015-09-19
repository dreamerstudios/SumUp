package com.example.ali_r.sumup;

import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Ali-R on 04/09/2015.
 */
class MyCountDownTimer extends MainActivity {
    private long millisInFuture;
    private long countDownInterval;
    public MyCountDownTimer(long pMillisInFuture, long pCountDownInterval) {
        this.millisInFuture = pMillisInFuture;
        this.countDownInterval = pCountDownInterval;

    }


    public void Start()
    {

        final Handler handler = new Handler();

        final Runnable counter = new Runnable(){

            public void run(){
                if(millisInFuture <= 0) {
                    System.out.println("onFinishBitch");
                    //asj.setText(Html.fromHtml("<font color='red'>TIME IS UP!!!!</font>"));

                    //System.out.println("onFinishBitch");
                    setX();
                } else {
                    long sec = millisInFuture/1000;
                    //asj.setText(Html.fromHtml("<font color='gray'>TIME: </font><b>" + millisInFuture / 1000 + "</b>"));
                    //asj.setGravity(Gravity.CENTER);
                    millisInFuture -= countDownInterval;
                    handler.postDelayed(this, countDownInterval);
                }
            }
        };

        handler.postDelayed(counter, countDownInterval);
    }
}