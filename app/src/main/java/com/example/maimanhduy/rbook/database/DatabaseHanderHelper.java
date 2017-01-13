package com.example.maimanhduy.rbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maimanhduy.rbook.model.FavoriteBook;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/13/2017.
 */

public class DatabaseHanderHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Book_Manager";


    // Tên bảng: Note.
    private static final String TABLE_FAVORITE = "Favorite";
    private static final String COLUMN_FAVORITE_ID ="Note_Id";
    private static final String COLUMN_FAVORITE_CATEGORY ="Note_Category";
    private static final String COLUMN_FAVORITE_DATE = "Note_Date";
    public DatabaseHanderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_database = "CREATE TABLE "+ TABLE_FAVORITE+"("+COLUMN_FAVORITE_ID+"TEXT PRIMARY KEY, "+COLUMN_FAVORITE_CATEGORY+" TEXT, "+ COLUMN_FAVORITE_DATE +" TEXT)";
        sqLiteDatabase.execSQL(create_database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addNewFavoriteBook(FavoriteBook book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVORITE_ID,book.getId());
        values.put(COLUMN_FAVORITE_CATEGORY,book.getCategory());
        values.put(COLUMN_FAVORITE_DATE,book.getDate());
        db.insert(TABLE_FAVORITE,null,values);
        db.close();
    }
    public void deleteFavoriteBook(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITE,COLUMN_FAVORITE_ID+" = ?",new String[]{id});
        db.close();
    }
    public ArrayList<FavoriteBook> getAllBook(){
    ArrayList<FavoriteBook> arrFavorite = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_FAVORITE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                FavoriteBook book = new FavoriteBook();
                book.setId(cursor.getString(0));
                book.setCategory(cursor.getString(1));
                book.setDate(cursor.getString(2));
                // Thêm vào danh sách.
                arrFavorite.add(book);
            } while (cursor.moveToNext());
        }
        return arrFavorite;
    }
}
