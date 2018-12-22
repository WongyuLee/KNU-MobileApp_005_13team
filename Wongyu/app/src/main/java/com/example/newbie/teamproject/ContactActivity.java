package com.example.newbie.teamproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.button1 :
                Intent i = new Intent(this, ListAct.class);
                startActivity(i);
                break;
            case R.id.button2 :
                Intent i2 = new Intent(this, InsAct.class);
                startActivity(i2);
                break;
            case R.id.button3 :
                Intent i3 = new Intent(this, DelAct.class);
                startActivity(i3);
                break;
        }
    }
}
