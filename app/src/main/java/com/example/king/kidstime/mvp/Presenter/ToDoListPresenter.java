package com.example.king.kidstime.mvp.Presenter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Parcel;

import com.example.king.kidstime.DB.DbHelper;
import com.example.king.kidstime.mvp.Base.BasePresenter;
import com.example.king.kidstime.mvp.View.ToDoList.ToDoListView;

import java.io.Serializable;

/**
 * Created by KING on 26.12.2017.
 */

public class ToDoListPresenter extends BasePresenter<ToDoListView>{
    public final String key = "ToDoList";
    public ToDoListPresenter(){
    }

    public void delete(int id){
        getDbHelper().deleteTask(id);
        getView().update(getDbHelper().getList(key, 0));

    }

    public void insert(String task){
        getDbHelper().insertNewTask(task);
        getView().update(getDbHelper().getList(key, 0));
    }

    public void updateStatus(int id, int idStatus){
        ContentValues values = new ContentValues();
        values.put("Status", idStatus);

        getDbHelper().updateTask(id, values);
        getView().update(getDbHelper().getList(key, 0));
    }

    public void showDeleteDialog(int id){
        getView().deleteDialog(id);
    }
    public void showUpdateDialog(int id){
        getView().updateStatusDialog(id);
    }

    public void getList(){
        super.getList(key, 0);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
