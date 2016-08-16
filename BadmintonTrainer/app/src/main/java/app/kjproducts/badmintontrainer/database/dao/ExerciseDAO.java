package app.kjproducts.badmintontrainer.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.model.Exercise;

/**
 * Created by KJ on 14/08/2016.
 */
public class ExerciseDAO {
    Context mContext;
    String path;

    public ExerciseDAO(Context mContext, String path) {
        this.mContext = mContext;
        this.path = path;
    }

    SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(path,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    //THEM DUY NHAT 1 DOI TUONG
    public long insert(Exercise exercise) {
        SQLiteDatabase database = openDB();
        //ContentValue la gi ? Mot ham de don gian hoa viec nhap kho khan
        long id = database.insert(Exercise.TABLE_NAME, null,
                mapContentValues(exercise));
        database.close();
        return id;
    }

    private ContentValues mapContentValues(Exercise exercise) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Exercise.COL_TITLE, exercise.getTitle());
        return contentValues;
    }


    public List<Exercise> getAll() {
        //buoc 2: mo file db
        SQLiteDatabase sqLiteDatabase = openDB();

        //buoc 3: truy van du lieu tu table country
        Cursor cursor = sqLiteDatabase.query(Exercise.TABLE_NAME, null, null, null, null, null,
                null);
        //trong qua trinh truy van co kha nag bi loi hoac trong truy van dc --> null
        List<Exercise> exercises = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                //chac chan la co du lieu --> lay du lieu
                exercises = new ArrayList<>();
                do {
                    Exercise exercise = new Exercise();
                    exercise.setId_index(cursor.getLong(cursor.getColumnIndex(Exercise.COL_ID_INDEX)));
                    exercise.setTitle(cursor.getString(cursor.getColumnIndex(Exercise.COL_TITLE)));
                    exercises.add(exercise);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return exercises;
    }
}
