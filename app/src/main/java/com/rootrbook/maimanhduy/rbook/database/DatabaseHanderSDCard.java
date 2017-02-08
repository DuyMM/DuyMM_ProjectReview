package com.rootrbook.maimanhduy.rbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rootrbook.maimanhduy.rbook.model.BookOnSdCard;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/16/2017.
 */

public class DatabaseHanderSDCard extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 2;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Local_Book";
    private static final String TABLE_BOOK_SD = "Sdcard_book";
    private static final String COLUMN_SDBOOK_ID = "Id";
    private static final String COLUMN_SDBOOK_TITLE = "Title";
    private static final String COLUMN_SDBOOK_BOOK = "Book";
    private static final String COLUMN_SDBOOK_IMAGE = "Image";
    private static final String COLUMN_SDBOOK_AUTHOR = "Category";
    public DatabaseHanderSDCard(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_book_on_sd_card = "CREATE TABLE " + TABLE_BOOK_SD + " (" + COLUMN_SDBOOK_ID + " TEXT, " + COLUMN_SDBOOK_TITLE + " TEXT, " + COLUMN_SDBOOK_BOOK + " TEXT, " + COLUMN_SDBOOK_IMAGE + " TEXT, " + COLUMN_SDBOOK_AUTHOR + " TEXT);";
        sqLiteDatabase.execSQL(create_book_on_sd_card);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void deleteBookOnSdCard(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK_SD, COLUMN_SDBOOK_ID + " = ?", new String[]{id});
        db.close();
    }
    public void addNewBookOnSdCard(BookOnSdCard book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SDBOOK_ID, book.getId());
        values.put(COLUMN_SDBOOK_TITLE, book.getTitle());
        values.put(COLUMN_SDBOOK_BOOK,book.getLinkBook());
        values.put(COLUMN_SDBOOK_IMAGE,book.getLinkImage());
        values.put(COLUMN_SDBOOK_AUTHOR, book.getCategory());
        db.insert(TABLE_BOOK_SD, null, values);
        db.close();
    }
    public ArrayList<BookOnSdCard> getAllBookOnSdCard(){
        ArrayList<BookOnSdCard> arrBookOnSDCard = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BOOK_SD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                BookOnSdCard book = new BookOnSdCard();
                book.setId(cursor.getString(0));
                book.setTitle(cursor.getString(1));
                book.setLinkBook(cursor.getString(2));
                book.setLinkImage(cursor.getString(3));
                book.setCategory(cursor.getString(4));
                arrBookOnSDCard.add(book);
            } while (cursor.moveToNext());
        }
        return arrBookOnSDCard;
    }
    public boolean checkIdIsHaveOnSdCard(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectId = "SELECT * FROM "+ TABLE_BOOK_SD+" WHERE "+ COLUMN_SDBOOK_ID+" = "+"'"+id+"'";
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
