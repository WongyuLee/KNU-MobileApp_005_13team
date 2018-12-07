package com.example.master.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.view.View;

public class DB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DB(Context context){
        super(context,"contactdb",null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableSpl="create table tb_contact("+
                "_id integer primary key autoincrement,"+
                "name not null,"+
                "phone,"+
                "email)";
        db.execSQL(tableSpl);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        if(newVersion==DATABASE_VERSION){
            db.execSQL("drop table tb_contact");
            onCreate(db);
        }
    }
}