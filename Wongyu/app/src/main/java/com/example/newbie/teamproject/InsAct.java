package com.example.newbie.teamproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins);
    }

    public void onClickButton(View v){
        EditText txt = null;
        txt = (EditText)findViewById(R.id.editText1);
        String name = txt.getText().toString();
        txt = (EditText)findViewById(R.id.editText2);
        String contact = txt.getText().toString();

        String sql = "INSERT INTO people (name, contact) VALUES('"+name+"','"+contact+"');";
        SQLiteDatabase db = openOrCreateDatabase(
                "test.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
        db.execSQL(sql);
        finish();
    }
}
