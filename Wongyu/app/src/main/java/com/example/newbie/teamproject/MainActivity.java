package com.example.newbie.teamproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.button1 :
                Intent i = new Intent(this, DetectActivity.class);
                startActivity(i);
                break;
            case R.id.button2 :
                break;
            case R.id.button3 :
                break;
        }
    }
}