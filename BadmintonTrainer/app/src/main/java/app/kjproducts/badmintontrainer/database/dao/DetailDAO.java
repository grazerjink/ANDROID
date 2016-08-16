package app.kjproducts.badmintontrainer.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.model.Detail;

/**
 * Created by KJ on 14/08/2016.
 */
public class DetailDAO {
    Context mContext;
    String path;

    public DetailDAO(Context mContext, String path) {
        this.mContext = mContext;
        this.path = path;
    }

    SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(path,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    public List<Detail> getAll() {
        SQLiteDatabase db = openDB();
        List<Detail> details = new ArrayList<>();
        //Duyet database dung Cursor nhu da hoc
        Cursor duyet = db.query(Detail.TABLE_NAME, null, null, null, null, null, null);
        //Da co du lieu Cursor
        //Xet truong hop
        if (duyet != null) { //lam tiep
            if (duyet.moveToFirst()) {
                do {
                    Detail detail = new Detail();
                    detail.setId_detail(duyet.getLong(duyet.getColumnIndex(Detail.COL_ID_DETAIL)));
                    detail.setId(duyet.getLong(duyet.getColumnIndex(Detail.COL_ID)));
                    detail.setImage(duyet.getString(duyet.getColumnIndex(Detail.COL_IMAGE)));
                    detail.setContent(duyet.getString(duyet.getColumnIndex(Detail.COL_CONTENT)));
                    details.add(detail);
                } while (duyet.moveToNext());
            }
            duyet.close();
        }
        db.close();
        return details;
    }

    public long insert(Detail detail) {
        //getWrite la OpenHelper
        SQLiteDatabase database = openDB();
        //ContentValue la gi ? Mot ham de don gian hoa viec nhap kho khan
        long id = database.insert(Detail.TABLE_NAME, null, mapContentValues(detail));
        database.close();
        return id;
    }

    private ContentValues mapContentValues(Detail detail) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Detail.COL_ID_DETAIL, detail.getId_detail());
        contentValues.put(Detail.COL_IMAGE, detail.getImage());
        contentValues.put(Detail.COL_CONTENT, detail.getContent());
        return contentValues;
    }
}
