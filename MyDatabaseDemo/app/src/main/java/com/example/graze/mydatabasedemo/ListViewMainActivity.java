package com.example.graze.mydatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListViewMainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    Button btnLoad;
    Button btnInsert;
    Button btnDelete;
    Button btnUpdate;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);
        listView = (ListView) findViewById(R.id.listview);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnInsert) {
            insertData();
            Toast.makeText(ListViewMainActivity.this, "INSERT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btnDelete) {
            deleteData();
        } else if (id == R.id.btnUpdate) {
            updateData();
        } else if (id == R.id.btnLoad) {
            initData();
        }
        //refesh ui
        setAdapter(getData());
    }

    void initData() {
        //Buoc 1 : Copy file DB vao Internal Storage
        path = getFilesDir().getAbsolutePath() + "/DBCountry";
        File file = new File(path);
        if (!file.exists()) {
            AssetManager assetManager = getAssets();
            try {
                BufferedInputStream bis = new BufferedInputStream(assetManager.open("DBCountry"));
                FileOutputStream fos = openFileOutput("DBCountry", MODE_PRIVATE);
                byte[] buffer = new byte[512];
                int len = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                bis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    List<Country> getData() {
        //Luon luon phai mo file
        SQLiteDatabase sqLiteDatabase = openDB();
        //Truy van
        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null, null, null, null, null, null);
        List<Country> countries = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
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
        return countries;
    }

    SQLiteDatabase openDB() {
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    void setAdapter(List<Country> countries) {
        ListViewAdapter adapter = new ListViewAdapter(ListViewMainActivity.this, R.layout.item_list_view_layout, countries);
        listView.setAdapter(adapter);
    }

    ContentValues getContentValues(Country country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Country.COL_NAME_EN, country.getNameEn());
        contentValues.put(Country.COL_NAME_VI, country.getNameVi());
        contentValues.put(Country.COL_FLAG, country.getFlag());
        contentValues.put(Country.COL_POPULATION, country.getPopulation());
        return contentValues;
    }

    String lastestNameEn;

    void insertData() {
        //Buoc 1 mo file
        SQLiteDatabase database = openDB();
        //Init noi dung doi tuong can them
        Country country = new Country();
        long time = System.currentTimeMillis();
        country.setNameEn("Name English" + time);
        country.setNameVi("Ten Tieng Viet" + time);
        country.setFlag("scotland");
        country.setPopulation(time);
        //Tien hanh them du lieu
        database.insert(Country.TABLE_NAME, null, getContentValues(country));
        lastestNameEn = country.getNameEn();
        database.close();
    }
    long _ID;
    void deleteData() {
        //buoc 1: mo db
        SQLiteDatabase database = openDB();
        Cursor cursor = database.query(Country.TABLE_NAME,null,null,null,null,null,Country.COL_ID + " DESC LIMIT 1");
        if(cursor != null) {
            if(cursor.moveToFirst())
                _ID = cursor.getLong(cursor.getColumnIndex(Country.COL_ID));
        } else {
            Toast.makeText(ListViewMainActivity.this, "NOT HAVING DATA TO DELETE", Toast.LENGTH_SHORT).show();
        }
        //buoc 2: delete
        int count = database.delete(Country.TABLE_NAME,
                Country.COL_ID + " = " + _ID,
                null);
        if (count > 0)
            Toast.makeText(ListViewMainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ListViewMainActivity.this, "Delete Fail", Toast.LENGTH_SHORT).show();


        //buoc 3: close DB
        database.close();
    }

    void updateData(){
        //buoc 1: mo db
        SQLiteDatabase database = openDB();

        //buoc 2: update
        Country country = new Country();
        long time = System.currentTimeMillis();
        country.setNameEn("English Name" + time);
        country.setNameVi("Tieng Viet" + time);
        country.setFlag("unitedkingdom");
        country.setPopulation(time);

        Cursor cursor = database.query(Country.TABLE_NAME,null,null,null,null,null,Country.COL_ID + " DESC LIMIT 1");
        if(cursor != null) {
            if(cursor.moveToFirst())
                _ID = cursor.getLong(cursor.getColumnIndex(Country.COL_ID));
        } else {
            Toast.makeText(ListViewMainActivity.this, "NOTHING TO UPDATE", Toast.LENGTH_SHORT).show();
        }

        int count = database.update(Country.TABLE_NAME, getContentValues(country),
                Country.COL_ID + " =" +_ID, null);
        if(count> 0)
            Toast.makeText(ListViewMainActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ListViewMainActivity.this, "Update Fail", Toast.LENGTH_SHORT).show();
        //buoc 3: close DB
        database.close();
    }
}
