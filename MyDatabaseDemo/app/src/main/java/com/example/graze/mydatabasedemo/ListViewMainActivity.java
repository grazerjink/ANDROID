package com.example.graze.mydatabasedemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListViewMainActivity extends AppCompatActivity {

    ListView listView;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);
        listView = (ListView) findViewById(R.id.listview);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buoc 1: Copy va ghi xuong inStorage
                String path = getFilesDir().getAbsolutePath() + "/DBCountry";
                File file = new File(path);
                if (!file.exists()) {
                    AssetManager assetManager = getAssets();
                    try {
                        BufferedInputStream bis = new BufferedInputStream(assetManager.open("DBCountry"));
                        FileOutputStream bfo = openFileOutput("DBCountry", MODE_PRIVATE);
                        byte[] buffer = new byte[512];
                        int len = 0;
                        while ((len = bis.read()) != -1) {
                            bfo.write(buffer, 0, len);
                        }
                        bfo.close();
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //Buoc 2 mo file database len
                SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
                //Buoc 3 thuc thi truy van lay du lieu
                Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null, null, null, null, null, null);
                List<Country> countries = null;
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        countries = new ArrayList<>();
                        do {
                            Country country = new Country();
                            country.set_id(cursor.getLong(cursor.getColumnIndex(Country.COL_ID)));
                            country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_EN)));
                            country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_VI)));
                            country.setFlag(cursor.getString(cursor.getColumnIndex(Country.COL_FLAG)));
                            country.setPopulation(cursor.getLong(cursor.getColumnIndex(Country.COL_POPULATION)));
                            countries.add(country);
                        }
                        while (cursor.moveToNext());
                    }
                    cursor.close();
                    sqLiteDatabase.close();
                }
                ListViewAdapter adapter = new ListViewAdapter(ListViewMainActivity.this, R.layout.item_list_view_layout, countries);
                listView.setAdapter(adapter);
            }
        });
    }
}
