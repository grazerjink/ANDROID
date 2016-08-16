package app.kjproducts.badmintontrainer.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.model.Child;
import app.kjproducts.badmintontrainer.model.Comment;

/**
 * Created by KJ on 16/08/2016.
 */
public class CommentDAO {

    Context mContext;
    String path;

    public CommentDAO(Context context, String path) {
        this.mContext = context;
        this.path = path;
    }

    SQLiteDatabase openDB() {
        return SQLiteDatabase.openDatabase(path,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    public long insert(Comment comment) {
        SQLiteDatabase database = openDB();
        long id = database.insert(Comment.TABLE_NAME, null, mapContentValues(comment));
        database.close();
        return id;
    }

    public ContentValues mapContentValues(Comment comment) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put(Comment.COL_ID, comment.getId());
        contentValues.put( Comment.COL_USER,comment.getUser());
        contentValues.put(Comment.COL_COMMENT, comment.getComment());
        return contentValues;
    }

    public List<Comment> getAll() {
        SQLiteDatabase database = openDB();
        List<Comment> commentList = new ArrayList<>();
        Cursor cursor = database.query(Comment.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Comment comment = new Comment();
                    comment.setId(cursor.getLong(cursor.getColumnIndex(Comment.COL_ID)));
                    comment.setUser(cursor.getString(cursor.getColumnIndex(Comment.COL_USER)));
                    comment.setComment(cursor.getString(cursor.getColumnIndex(Comment.COL_COMMENT)));
                    commentList.add(comment);
                } while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
        }

        return commentList;
    }
}
