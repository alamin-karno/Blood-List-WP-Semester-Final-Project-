package com.karno.wpfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "User.db";
    private static String TABLE_NAME = "User";
    public static String COL_ID = "Id";
    public static String COL_NAME = "Name";
    public static String COL_AGE = "Age";
    public static String COL_SEX = "Sex";
    public static String COL_CITY = "City";
    public static String COL_BLOOD = "BloodGroup";
    private static int VERSION = 1;

    private String CREATE_TABLE = "create table "+TABLE_NAME+" ("+COL_ID+" Integer primary key autoincrement, "+COL_NAME+" TEXT, "+COL_AGE+" TEXT,"+COL_SEX+" TEXT,"+COL_CITY+" TEXT,"+COL_BLOOD+ " TEXT)";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertDate(String name,String age,String sex,String city,String blood){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AGE,age);
        contentValues.put(COL_SEX,sex);
        contentValues.put(COL_CITY,city);
        contentValues.put(COL_BLOOD,blood);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

        return id;
    }


    public Cursor searchData(int ID){

        String SEARCH_QUERY = "select * from "+TABLE_NAME+" where "+COL_ID+" ="+ID;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SEARCH_QUERY,null);

        return cursor;

    }
}
