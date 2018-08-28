package com.example.king.kidstime.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.king.kidstime.DB.model.ToDoListModel;

import java.util.ArrayList;

/**
 * Created by KING on 10.11.2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "KidsTime_DB";
    private static final int DB_VER = 1;

    //ToDoList

    private static final String DB_TABLE_ToDoList = "ToDoList_Table";
    private static final String DB_COLUMN_ToDoList_ID = "ID";
    private static final String DB_COLUMN_ToDoList_TEXT = "Task";
    private static final String DB_COLUMN_ToDoList_STATUS = "Status";

    //TabsTimeKeepingFragment
    private static final String DB_TABLE_TimeKeeping = "TimeKeeping_Table";
    private static final String DB_COLUMN_TimeKeeping_ID = "ID_TK";
    private static final String DB_COLUMN_TimeKeeping_Day_ID = "Day_ID";
    private static final String DB_COLUMN_TimeKeeping_DATE = "Date";
    private static final String DB_COLUMN_TimeKeeping_Time_Start = "TimeStart";
    private static final String DB_COLUMN_TimeKeeping_Time_end = "TimeEnd";
    private static final String DB_COLUMN_TimeKeeping_Deviation = "Deviation";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    private void createTimeKeeping(SQLiteDatabase db){
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL);",
                DB_TABLE_TimeKeeping, DB_COLUMN_TimeKeeping_ID, DB_COLUMN_ToDoList_ID, DB_COLUMN_TimeKeeping_Day_ID ,DB_COLUMN_TimeKeeping_DATE, DB_COLUMN_TimeKeeping_Time_Start, DB_COLUMN_TimeKeeping_Time_end,
                DB_COLUMN_ToDoList_STATUS, DB_COLUMN_TimeKeeping_Deviation);
        db.execSQL(query);
    }

    private void createToDoList(SQLiteDatabase db){
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER NOT NULL);",
                DB_TABLE_ToDoList, DB_COLUMN_ToDoList_ID, DB_COLUMN_ToDoList_TEXT, DB_COLUMN_ToDoList_STATUS);
        db.execSQL(query);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createToDoList(db);
        createTimeKeeping(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        String query = String.format("DELETE TABLE IF EXIST %s", DB_TABLE_ToDoList);
        db.execSQL(query);
        query = String.format("DELETE TABLE IF EXIST %s", DB_TABLE_TimeKeeping);
        db.execSQL(query);
        onCreate(db);

    }

    //TIME KEEPING
    public void insertNewTime(int id_task, int day_id, String date, String time_start, String time_end, int status, String deviation){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_ToDoList_ID, String.valueOf(id_task));
        values.put(DB_COLUMN_TimeKeeping_Day_ID, String.valueOf(day_id));
        values.put(DB_COLUMN_TimeKeeping_DATE, date);
        values.put(DB_COLUMN_TimeKeeping_Time_Start, time_start);
        values.put(DB_COLUMN_TimeKeeping_Time_end, time_end);
        values.put(DB_COLUMN_ToDoList_STATUS, String.valueOf(status));
        values.put(DB_COLUMN_TimeKeeping_Deviation, deviation);

        Log.d("insert", String.valueOf(day_id));
        db.insertWithOnConflict(DB_TABLE_TimeKeeping, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTK(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE_TimeKeeping, DB_COLUMN_TimeKeeping_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateTK(int id, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DB_TABLE_TimeKeeping, values, DB_COLUMN_TimeKeeping_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    private ArrayList<ToDoListModel> getTKList(int day_id) {
        ArrayList<ToDoListModel> TKList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT %s, %s, %s, %s, %s, a.%s, %s FROM %s a INNER JOIN %s b ON a.ID = b.ID WHERE %s = %s;",
                DB_COLUMN_TimeKeeping_ID, DB_COLUMN_ToDoList_TEXT, DB_COLUMN_TimeKeeping_DATE, DB_COLUMN_TimeKeeping_Time_Start,
                DB_COLUMN_TimeKeeping_Time_end, DB_COLUMN_ToDoList_STATUS,DB_COLUMN_TimeKeeping_Deviation,
                DB_TABLE_TimeKeeping, DB_TABLE_ToDoList, DB_COLUMN_TimeKeeping_Day_ID, String.valueOf(day_id)); //работает

        Cursor cursor = db.rawQuery(query, null); //работает

        while (cursor.moveToNext()) {
            int id = cursor.getColumnIndex(DB_COLUMN_TimeKeeping_ID);
            //int text = cursor.getColumnIndex("TimeKeeping_Table."+DB_COLUMN_ToDoList_TEXT);
            int text = cursor.getColumnIndex(DB_COLUMN_ToDoList_TEXT);
            int date = cursor.getColumnIndex(DB_COLUMN_TimeKeeping_DATE);
            int start= cursor.getColumnIndex(DB_COLUMN_TimeKeeping_Time_Start);
            int end= cursor.getColumnIndex(DB_COLUMN_TimeKeeping_Time_end);
            int status = cursor.getColumnIndex(DB_COLUMN_ToDoList_STATUS);
            int deviation = cursor.getColumnIndex(DB_COLUMN_TimeKeeping_Deviation);

            TKList.add(new ToDoListModel(cursor.getInt(id), cursor.getString(text), cursor.getInt(status), cursor.getString(date),
                    cursor.getString(start), cursor.getString(end), cursor.getString(deviation)));
        }
        cursor.close();
        db.close();
        return TKList;
    }

    //TO DO LIST
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

    private ArrayList<ToDoListModel> getTaskList() {
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

    public ArrayList<ToDoListModel> getList(String key, int day_id){
        switch (key){
            case "ToDoList": return getTaskList();
            case "TKList": return getTKList(day_id);
            default: return null;
        }
    }
}
