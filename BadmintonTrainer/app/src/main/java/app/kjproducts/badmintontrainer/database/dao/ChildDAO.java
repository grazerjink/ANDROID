package app.kjproducts.badmintontrainer.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.model.Child;

/**
 * Created by KJ on 14/08/2016.
 */
public class ChildDAO {
    Context mContext;
    String path;

    public ChildDAO(Context context, String path) {
        this.mContext = context;
        this.path = path;
    }

    SQLiteDatabase openDB() {
        return SQLiteDatabase.openDatabase(path,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    public long insert(Child childs) {
        SQLiteDatabase database = openDB();
        long id = database.insert(Child.TABLE_NAME, null, mapContentValues(childs));
        database.close();
        return id;
    }

    public ContentValues mapContentValues(Child childs) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Child.COL_CHILD, childs.getChildName());
        contentValues.put(Child.COL_ID_DETAIL, childs.getId_detail());
        return contentValues;
    }

    public List<Child> getAll() {
        SQLiteDatabase database = openDB();
        List<Child> childList = new ArrayList<>();
        Cursor cursor = database.query(Child.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Child child = new Child();
                    child.setId_child(cursor.getLong(cursor.getColumnIndex(Child.COL_ID_CHILD)));
                    child.setChildName(cursor.getString(cursor.getColumnIndex(Child.COL_CHILD)));
                    child.setId_detail(cursor.getLong(cursor.getColumnIndex(Child.COL_ID_DETAIL)));
                    child.setChildImage(cursor.getString(cursor.getColumnIndex(Child.COL_IMAGE)));
                    childList.add(child);
                } while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
        }

        return childList;
    }
}
