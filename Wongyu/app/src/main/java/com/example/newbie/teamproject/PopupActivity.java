package com.example.newbie.teamproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class PopupActivity extends Activity {
    TextView TimeView;
    private int time = 30;
    private Timer timer;
    private final android.os.Handler handler = new android.os.Handler();
    private String phoneNo = "010-3675-1851";
    private String text = "차량사고발생!\n";
    private String Longitude;
    private String Latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        TimeView = (TextView)findViewById(R.id.time1);

        Intent intent = getIntent();
        Longitude = intent.getStringExtra("Longitude");
        Latitude = intent.getStringExtra("Latitude");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    public void update(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(time<0){
                    try{
                        String result = text + "사고위치 - 위도: " + Longitude + "\n경도:" + Latitude;
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo,null,result,null,null);
                    }catch (Exception e){
                        Log.i("report","fail");
                        e.printStackTrace();
                    }
                    timer.cancel();
                }else{
                    TimeView.setText(String.valueOf(time));
                    time--;
                }
            }
        };
        handler.post(runnable);

    }
    public void mOnClose(View v){
        timer.cancel();
        this.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
