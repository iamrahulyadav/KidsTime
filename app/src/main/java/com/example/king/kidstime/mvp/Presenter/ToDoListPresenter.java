package com.example.king.kidstime.mvp.Presenter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;

import com.example.king.kidstime.DB.DbHelper;
import com.example.king.kidstime.mvp.Base.BasePresenter;
import com.example.king.kidstime.mvp.View.ToDoList.ToDoListView;

/**
 * Created by KING on 26.12.2017.
 */

public class ToDoListPresenter extends BasePresenter<ToDoListView>{
    DbHelper dbHelper;

    public ToDoListPresenter(){

    }
    public ToDoListPresenter(DbHelper dbHelper){
        this.dbHelper = dbHelper;
    }


    public void delete(int id){
        dbHelper.deleteTask(id);
        getView().update(dbHelper.getTaskList());
    }

    public void insert(String task){
        dbHelper.insertNewTask(task);
        getView().update(dbHelper.getTaskList());
    }

    public void updateStatus(int id, int idStatus){
        ContentValues values = new ContentValues();
        values.put("Status", idStatus);
        dbHelper.updateTask(id, values);
        getView().update(dbHelper.getTaskList());
        //dbHelper.
    }



    public void showDeleteDialog(int id){
        getView().deleteDialog(id);
    }
    public void showUpdateDialog(int id){
        getView().updateStatusDialog(id);
    }


}
