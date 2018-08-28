package com.example.king.kidstime.mvp.Base;

import android.content.Context;
import android.os.Parcelable;

import com.example.king.kidstime.DB.DbHelper;

import java.io.Serializable;

/**
 * Created by KING on 14.12.2017.
 */

public abstract class BasePresenter <V extends BaseView> implements Serializable, Parcelable {

    private V mView;
    private Context context;
    private DbHelper dbHelper;

    public final void attachView(V view) {
        if (view == null) {
            throw new NullPointerException("View must not be null");
        }
                mView = view;
    }

    public final void attachContext(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public final void detachView() {
        mView = null;
    }

    public final V getView() {
        return mView;
    }


    public final boolean isViewAttached() {
        return mView != null;
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void getList(String key, int day_id){
        mView.setList(dbHelper.getList(key, day_id));
    }

}
