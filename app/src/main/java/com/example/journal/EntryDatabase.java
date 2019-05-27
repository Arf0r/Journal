package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {
    public static EntryDatabase instance;

    // Constructor for database
    public EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // If there is no database yet, make one
    public static EntryDatabase getInstance(Context context){
        if (instance == null) {
            instance = new EntryDatabase(context, "db", null, 1);
        }
        return instance;
    }

    // Create a table in the database
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table entries (_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", Title TEXT, Content TEXT, Mood TEXT, Time DATETIME default current_timestamp);");
    }

    // If that table is updated, replace new with old
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entries");
        onCreate(db);
    }

    // Select all data in the database and returns cursor
    public Cursor selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        String data = "SELECT * FROM" + " entries";
        Cursor cursor = db.rawQuery(data,null);
        return cursor;
    }

    // Add journal to database
    public void insert(JournalEntry JournalEntry) {

        SQLiteDatabase EntryDatabase = getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put("title", JournalEntry.getTitle());
        ContentValues.put("content", JournalEntry.getContent());
        ContentValues.put("mood", JournalEntry.getMood());
        ContentValues.put("time", JournalEntry.getTimestamp());
        EntryDatabase.insert("entries", null, ContentValues);
    }

    // Delete Journal from database
    static void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.execSQL("DELETE FROM entries WHERE _id = " + id);
    }
}
