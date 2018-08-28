package com.example.king.kidstime.mvp.Presenter;

import android.content.ContentValues;
import android.os.Parcel;
import android.util.Log;

import com.example.king.kidstime.mvp.Base.BasePresenter;
import com.example.king.kidstime.mvp.View.TimeKeeping.Fragment.TimeKeepengView;
import com.example.king.kidstime.mvp.View.TimeKeeping.Tabs.TabsPagerAdapter;

/**
 * Created by KING on 28.12.2017.
 */

public class TimeKeepingPresenter extends BasePresenter<TimeKeepengView> {
    public final String key = "TKList";
    private int day_id;
    private TabsPagerAdapter adapter;

    public TimeKeepingPresenter(){

    }

    public void delete(int id){
        getDbHelper().deleteTK(id);
        getView().update(getDbHelper().getList(key, day_id));
        adapter.notifyDataSetChanged();
        //todo delete
    }


    public void insert(int id, int day_id, String time_start, String time_end){
        getDbHelper().insertNewTime(id, day_id,"02-01-2018", time_start, time_end, 0,"");
        getView().update(getDbHelper().getList(key, day_id));
    }

    public void updateStatus(int id, int idStatus){
        ContentValues values = new ContentValues();
        values.put("Status", idStatus);
        Log.d("ID", String.valueOf(id) + " " + String.valueOf(idStatus));

        getDbHelper().updateTK(id, values);
        getView().update(getDbHelper().getList(key, day_id));
        adapter.notifyDataSetChanged();
    }

    public void getList(){
        super.getList(key, day_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }


    public void showDeleteDialog(int id){
        getView().deleteDialog(id);
    }
    public void showUpdateDialog(int id){
        getView().updateStatusDialog(id);
    }

    public void setPagerAdapter(TabsPagerAdapter adapter) {
        this.adapter = adapter;
    }
}
