package com.example.newbie.teamproject;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class DelAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        loadDB();
        findViewById(R.id.DelButton).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        EditText txt = null;
                        txt = (EditText) findViewById(R.id.SNUM);
                        String delname = String.valueOf(txt.getText());
                        Log.i("dsg","delete name is:"+delname);
                        SQLiteDatabase db =openOrCreateDatabase(
                                "test.db",
                                SQLiteDatabase.CREATE_IF_NECESSARY,
                                null);

                        db.execSQL("DELETE FROM people WHERE name="+"'"+delname+"';");
                        loadDB();
                    }
                }
        );
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
