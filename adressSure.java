package com.example.master.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class adressSure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_sure);

        TextView nameView = (TextView) findViewById(R.id.result_name);
        TextView PhoneView = (TextView) findViewById(R.id.result_Phone);
        TextView EmailView = (TextView) findViewById(R.id.result_email);

        DB helper = new DB(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor =db.rawQuery("select name, Phone, Email from tb_contact order by _id limit 1",null);
        while(cursor.moveToNext()){
            nameView.setText(cursor.getString(0));
            PhoneView.setText(cursor.getString(1));
            EmailView.setText(cursor.getString(2));
        }

    }
}