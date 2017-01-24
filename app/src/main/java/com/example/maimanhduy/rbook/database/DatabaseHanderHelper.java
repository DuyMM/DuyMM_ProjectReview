package com.example.maimanhduy.rbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maimanhduy.rbook.model.BookInFireBase;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/13/2017.
 */

public class DatabaseHanderHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Manager_Book";


    // Tên bảng: Note.
    private static final String TABLE_FAVORITE = "Favorite_book";
    private static final String COLUMN_FAVORITE_ID = "IdBook";
    private static final String COLUMN_FAVORITE_TITLE = "Title";
    private static final String COLUMN_FAVORITE_CATEGORY = "Category";
    private static final String COLUMN_FAVORITE_LINK_BOOK = "LinkBook";
    private static final String COLUMN_FAVORITE_LINK_IMAGE = "LinkImage";

    public DatabaseHanderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String create_favorite_database = "CREATE TABLE " + TABLE_FAVORITE + " ( " + COLUMN_FAVORITE_ID + " TEXT, " + COLUMN_FAVORITE_TITLE + " TEXT, " + COLUMN_FAVORITE_CATEGORY + " TEXT, " + COLUMN_FAVORITE_LINK_BOOK + " TEXT, " + COLUMN_FAVORITE_LINK_IMAGE + " TEXT );";
            sqLiteDatabase.execSQL(create_favorite_database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNewFavoriteBook(BookInFireBase book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVORITE_ID, book.getId());
        values.put(COLUMN_FAVORITE_TITLE, book.getTitleBook());
        values.put(COLUMN_FAVORITE_CATEGORY, book.getBookCategory());
        values.put(COLUMN_FAVORITE_LINK_BOOK, book.getLinkBook());
        values.put(COLUMN_FAVORITE_LINK_IMAGE, book.getLinkImage());
        db.insert(TABLE_FAVORITE, null, values);
        db.close();
        Log.d(TAG, book.getId() + " " + book.getTitleBook() + " " + book.getBookCategory() + " " + book.getLinkBook() + " " + book.getLinkImage());
    }

    public void deleteFavoriteBook(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITE, COLUMN_FAVORITE_ID + " = ?", new String[]{id});
        db.close();
    }

    public ArrayList<BookInFireBase> getAllFavoriteBook() {
        ArrayList<BookInFireBase> arrFavorite = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                BookInFireBase book = new BookInFireBase();
                book.setId(cursor.getString(0));
                book.setTitleBook(cursor.getString(1));
                book.setBookCategory(cursor.getString(2));
                book.setLinkBook(cursor.getString(3));
                book.setLinkImage(cursor.getString(4));
                book.setAuthorName("");
                book.setViews("");
                arrFavorite.add(book);
            } while (cursor.moveToNext());
        }
        return arrFavorite;
    }
    public boolean checkFavoriteBookHaveInPhone(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectId = "SELECT * FROM "+ TABLE_FAVORITE+" WHERE "+ COLUMN_FAVORITE_ID+" = "+"'"+id+"'";
        Cursor cursor = db.rawQuery(selectId,null);
        if (cursor.moveToFirst()){
            db.close();
            return true;
        }else {
            db.close();
            return false;
        }
    }
}
