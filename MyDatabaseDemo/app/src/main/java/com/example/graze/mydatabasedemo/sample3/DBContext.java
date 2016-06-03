package com.example.graze.mydatabasedemo.sample3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.graze.mydatabasedemo.Country;

/**
 * Created by graze on 03/06/2016.
 */
public class DBContext extends SQLiteOpenHelper {

    final static String DB_NAME = "Country.db";
    final static int DB_VERSION = 1;

    public DBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Country.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Truong phai ko quan tam cau truc du lieu
        //Xoa sach, ghi de cai moi nhat
        //La truong phai do? nhat
        db.execSQL(Country.DROP_TABLE);
        onCreate(db);
    }
}
