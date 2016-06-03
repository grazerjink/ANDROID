package com.example.graze.mydatabasedemo.sample3;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.graze.mydatabasedemo.Country;
import com.example.graze.mydatabasedemo.ListViewAdapter;
import com.example.graze.mydatabasedemo.R;

import java.io.BufferedInputStream;
import java.io.File;
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
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnLoad.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnLoad){
            initDB();
            copyDB();
            setAdapter(getData(newDatabase));
        } else if(id == R.id.btnUpdate){
            updateData(newDatabase);
        } else if(id == R.id.btnInsert){
            insertData(newDatabase);
        } else if(id == R.id.btnDelete){
            deleteData(newDatabase);
        }
        //refesh ui
        setAdapter(getData(newDatabase));
    }

    void initDB(){
        //buoc 1: copy file db vao trong bo nho trong

        //kiem tra file db da ton tai trong internal chua,
        //da ton tai --> bo qua
        //chua ton tai --> copy
        path = getFilesDir().getAbsolutePath() + "/DBCountry";
        File file = new File(path);
        if (!file.exists()) {
            AssetManager assetManager = getAssets();
            try {
                BufferedInputStream bis = new BufferedInputStream(assetManager.open("DBCountry"));
                FileOutputStream fos = openFileOutput("DBCountry", MODE_PRIVATE);

                byte[] buffer = new byte[512];
                int i = 0;
                while ((i = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                }

                bis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Tao moi 1 bien newDatanase luu du lieu cua file database mi
    SQLiteDatabase newDatabase;
    //Copy noi dung database moi da tao truoc do
    void copyDB(){
        //Buoc 1 : lay lai noi dung hien co cua file DB cu~
        List<Country> countries = getData(openDB());
        //Buoc 2 : copy content sang file db moi
        DBContext dbContext = new DBContext(this);
        newDatabase = dbContext.getWritableDatabase();
        //Check neu "newDatabase" da co du lieu --> ko copy
        Cursor cursor = newDatabase.query(Country.TABLE_NAME, null,null,null,null,null,null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0){
                cursor.close();
                return;
            }
            cursor.close();
        }
        newDatabase.beginTransaction();
        for (Country country : countries) {
            newDatabase.insert(Country.TABLE_NAME,null, getContentValues(country));
        }
        newDatabase.setTransactionSuccessful();
        newDatabase.endTransaction();
    }

    ContentValues getContentValues(Country country){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Country.COL_NAME_EN, country.getNameEn());
        contentValues.put(Country.COL_NAME_VI, country.getNameVi());
        contentValues.put(Country.COL_FLAG, country.getFlag());
        contentValues.put(Country.COL_POPULATION, country.getPopulation());
        return contentValues;
    }


    List<Country> getData(SQLiteDatabase sqLiteDatabase){
        //buoc 2: mo file db
//        SQLiteDatabase sqLiteDatabase = openDB();

        //buoc 3: truy van du lieu tu table country
        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null, null, null, null, null,null);
        //trong qua trinh truy van co kha nag bi loi hoac trong truy van dc --> null
//        List<Country> countries = null;
        List<Country> countries =  new ArrayList<>();;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                //chac chan la co du lieu --> lay du lieu
//                countries = new ArrayList<>();
                do {
                    Country country = new Country();
                    country.set_id(cursor.getInt(cursor.getColumnIndex(Country.COL_ID)));
                    country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_EN)));
                    country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_VI)));
                    country.setFlag(cursor.getString(cursor.getColumnIndex(Country.COL_FLAG)));
                    country.setPopulation(cursor.getLong(cursor.getColumnIndex(Country.COL_POPULATION)));
                    countries.add(country);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return countries;
    }

    SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(path,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    void setAdapter(List<Country> countries){
        //buoc 4: load ds countries len listview
        ListViewAdapter adapter = new ListViewAdapter(ListViewMainActivity.this,
                R.layout.item_list_view_layout, countries);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(newDatabase != null)
            newDatabase.close();
    }

    void insertData(SQLiteDatabase database) {
        //Buoc 1 mo file
//        SQLiteDatabase database = openDB();
        //Init noi dung doi tuong can them
        Country country = new Country();
        long time = System.currentTimeMillis();
        country.setNameEn("NAME_ENG " + time);
        country.setNameVi("TIENG_VIET " + time);
        country.setFlag("scotland");
        country.setPopulation(time);
        //Tien hanh them du lieu
        database.insert(Country.TABLE_NAME, null, getContentValues(country));
        database.close();
    }
    long _ID;
    void deleteData(SQLiteDatabase database) {
        //buoc 1: mo db
//        SQLiteDatabase database = openDB();
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

    void updateData(SQLiteDatabase database){
        //buoc 1: mo db
//        SQLiteDatabase database = openDB();

        //buoc 2: update
        Country country = new Country();
        long time = System.currentTimeMillis();
        country.setNameEn("English " + time);
        country.setNameVi("Tieng_Viet " + time);
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
