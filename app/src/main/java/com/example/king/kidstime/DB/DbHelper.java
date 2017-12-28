package com.example.king.kidstime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.king.kidstime.DB.model.ToDoListModel;

import java.util.ArrayList;

/**
 * Created by KING on 10.11.2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "KidsTime_DB";
    public static final int DB_VER = 1;
    //ToDoList

    public static final String DB_TABLE_ToDoList = "ToDoList_Table";
    public static final String DB_COLUMN_ToDoList_ID = "ID";
    public static final String DB_COLUMN_ToDoList_TEXT = "Task";
    public static final String DB_COLUMN_ToDoList_STATUS = "Status";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("LOL", "CREATE");
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER NOT NULL);",
                DB_TABLE_ToDoList, DB_COLUMN_ToDoList_ID, DB_COLUMN_ToDoList_TEXT, DB_COLUMN_ToDoList_STATUS);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        String query = String.format("DELETE TABLE IF EXIST %s", DB_TABLE_ToDoList);
        db.execSQL(query);
        Log.d("LOL", "onUpgrade");
        onCreate(db);

    }

    public void insertNewTask(String task){
        Log.d("LOL", "insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_ToDoList_TEXT, task);
        values.put(DB_COLUMN_ToDoList_STATUS, "0");
        db.insertWithOnConflict(DB_TABLE_ToDoList, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE_ToDoList, DB_COLUMN_ToDoList_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateTask(int id, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DB_TABLE_ToDoList, values, DB_COLUMN_ToDoList_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


     public ArrayList<ToDoListModel> getTaskList() {
        ArrayList<ToDoListModel> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE_ToDoList, new String[]{DB_COLUMN_ToDoList_ID,DB_COLUMN_ToDoList_TEXT, DB_COLUMN_ToDoList_STATUS}, null, null, null, null, null);
         //String query = String.format("SELECT * FROM %s;",DB_TABLE_ToDoList); //работает
         //Cursor cursor = db.rawQuery(query, null); //работает

        while (cursor.moveToNext()) {
            int id = cursor.getColumnIndex(DB_COLUMN_ToDoList_ID);
            int text = cursor.getColumnIndex(DB_COLUMN_ToDoList_TEXT);
            int status = cursor.getColumnIndex(DB_COLUMN_ToDoList_STATUS);
            taskList.add(new ToDoListModel(cursor.getInt(id), cursor.getString(text), cursor.getInt(status)));
        }
        cursor.close();
        db.close();
        return taskList;
    }
}
