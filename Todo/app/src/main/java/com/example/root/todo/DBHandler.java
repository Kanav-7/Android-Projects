package com.example.root.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.Key;
import java.util.ArrayList;

/**
 * Created by root on 24/6/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "todoDB.db";
    public static final String TABLE_TODO = "todo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DONE = "done";
    public static final String COLUMN_DESCRIPTION = "description";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TODO + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DONE + " INTEGER " +
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
        values.put(COLUMN_DONE,todo.get_done());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    public void deletetodo(Integer id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TODO + " WHERE " + COLUMN_ID + "=" + String.valueOf(id) + ";");
    }

//    public Integer deletetodo(String id){
//        SQLiteDatabase db = getWritableDatabase();
//        int i = db.delete(TABLE_TODO,"_id=?",new String[]{id});
//        return i;
//    }

    public int updatetodo(Todo todo){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, todo.get_title());
        values.put(COLUMN_DESCRIPTION,todo.get_description());
        values.put(COLUMN_DONE,todo.get_done());
        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLE_TODO,values, COLUMN_ID + " = ?",new String[]{String.valueOf(todo.get_id())});
    }
    public ArrayList<Todo> dbToarr(){
        ArrayList<Todo> a = new ArrayList();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";

        Cursor point = db.rawQuery(query,null);

        point.moveToFirst();

        while (!point.isAfterLast()){
            Todo temp = new Todo();
            if(point.getString(point.getColumnIndex("title"))!=null && point.getString(point.getColumnIndex("description"))!=null){
                temp.set_title(point.getString(point.getColumnIndex("title")));
                temp.set_description(point.getString(point.getColumnIndex("description")));
                temp.set_id(point.getInt(point.getColumnIndex("_id")));
                temp.set_done(point.getInt(point.getColumnIndex("done")));
                a.add(temp);
            }

            point.moveToNext();
        }
        db.close();
        return a;
    }


//    public ArrayList<String> dbToarr1(){
//        ArrayList<String> a = new ArrayList();
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";
//
//        Cursor point = db.rawQuery(query,null);
//
//        point.moveToFirst();
//
//        while (!point.isAfterLast()){
//            if(point.getString(point.getColumnIndex("title"))!=null && point.getString(point.getColumnIndex("description"))!=null){
//                a.add(point.getString(point.getColumnIndex("title")));
//            }
//            point.moveToNext();
//        }
//        db.close();
//        return a;
//    }
//
//    public ArrayList<String> dbToddesc(){
//        ArrayList<String> a = new ArrayList();
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";
//
//        Cursor point = db.rawQuery(query,null);
//
//        point.moveToFirst();
//
//        while (!point.isAfterLast()){
//            if(point.getString(point.getColumnIndex("description"))!=null && point.getString(point.getColumnIndex("title"))!=null){
//                a.add(point.getString(point.getColumnIndex("description")));
//            }
//            point.moveToNext();
//        }
//        db.close();
//        return a;
//    }
//
//    public ArrayList<Integer> dbToid(){
//        ArrayList<Integer> a = new ArrayList();
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";
//
//        Cursor point = db.rawQuery(query,null);
//
//        point.moveToFirst();
//
//        while (!point.isAfterLast()){
//            if(point.getString(point.getColumnIndex("description"))!=null && point.getString(point.getColumnIndex("title"))!=null){
//                a.add(point.getInt(point.getColumnIndex("_id")));
//            }
//            point.moveToNext();
//        }
//        db.close();
//        return a;
//    }
}
