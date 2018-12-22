package com.example.newbie.teamproject;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        TimeView = (TextView)findViewById(R.id.time1);
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
