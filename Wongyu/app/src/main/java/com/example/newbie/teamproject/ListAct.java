package com.example.newbie.teamproject;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ListAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadDB();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadDB();
    }

    public void loadDB(){
        //deleteDatabase("test.db");
        SQLiteDatabase db = openOrCreateDatabase(
                "test.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
        db.execSQL("CREATE TABLE IF NOT EXISTS people"
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, contact TEXT);");

        Cursor c = db.rawQuery("SELECT * FROM people;", null);
        startManagingCursor(c);

        ListAdapter adapt = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                c,
                new String[]{"name","contact"},
                new int[]{android.R.id.text1, android.R.id.text2},0);
        setListAdapter(adapt);
        if(db!=null){
            db.close();
        }
    }
}
