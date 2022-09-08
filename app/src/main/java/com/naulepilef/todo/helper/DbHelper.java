package com.naulepilef.todo.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static String NAME_DB = "DB_TASKS";
    public static int VERSION = 1;

    public static String TABLE_TASKS = "tasks";

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT NOT NULL ); ";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "SUCCESS ON CREATE TABLE | ");
        }catch(Exception e){
            Log.i("INFO DB", "ERROR ON CREATE TABLE | " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_TASKS + " ;";

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "SUCCESS ON UPDATE TABLE | ");
        }catch(Exception e){
            Log.i("INFO DB", "ERROR ON UPDATE TABLE | " + e.getMessage());
        }
    }
}
