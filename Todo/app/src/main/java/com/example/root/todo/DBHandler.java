package com.example.root.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by root on 24/6/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todoDB.db";
    public static final String TABLE_TODO = "todo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TODO + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public void addtodo(Todo todo){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, todo.get_title());
        values.put(COLUMN_DESCRIPTION,todo.get_description());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TODO, null, values);
        db.close();
    }

//    public void deletetodo(long id){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE " + COLUMN_ID + "='" + id + "';");
//    }

    public Integer deletetodo(String id){
        SQLiteDatabase db = getWritableDatabase();
        int i = db.delete(TABLE_TODO,"_id=?",new String[]{id});
        return i;
    }

    public ArrayList<String> dbToarr(){
        ArrayList<String> a = new ArrayList();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";

        Cursor point = db.rawQuery(query,null);

        point.moveToFirst();

        while (!point.isAfterLast()){
            if(point.getString(point.getColumnIndex("title"))!=null && point.getString(point.getColumnIndex("description"))!=null){
                a.add(point.getString(point.getColumnIndex("title")));
            }
            point.moveToNext();
        }
        db.close();
        return a;
    }

    public ArrayList<String> dbToddesc(){
        ArrayList<String> a = new ArrayList();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";

        Cursor point = db.rawQuery(query,null);

        point.moveToFirst();

        while (!point.isAfterLast()){
            if(point.getString(point.getColumnIndex("description"))!=null && point.getString(point.getColumnIndex("title"))!=null){
                a.add(point.getString(point.getColumnIndex("description")));
            }
            point.moveToNext();
        }
        db.close();
        return a;
    }

    public ArrayList<Integer> dbToid(){
        ArrayList<Integer> a = new ArrayList();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";

        Cursor point = db.rawQuery(query,null);

        point.moveToFirst();

        while (!point.isAfterLast()){
            if(point.getString(point.getColumnIndex("description"))!=null && point.getString(point.getColumnIndex("title"))!=null){
                a.add(point.getInt(point.getColumnIndex("_id")));
            }
            point.moveToNext();
        }
        db.close();
        return a;
    }
}
