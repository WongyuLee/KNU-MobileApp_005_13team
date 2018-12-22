package com.example.newbie.teamproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askForPermission(Manifest.permission.SEND_SMS,0);
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,1);
    }

    private void askForPermission(String permission, Integer requestCode){
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permission)){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission},requestCode);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, ""+ permission + " is already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.button1 :
                Intent i = new Intent(this, DetectActivity.class);
                startActivity(i);
                break;
            case R.id.button2 :
                Intent i2 = new Intent(this, ContactActivity.class);
                startActivity(i2);
                break;
            case R.id.button3 :
                Intent i3 = new Intent(this, InfoActivity.class);
                startActivity(i3);
                break;
        }
    }
}