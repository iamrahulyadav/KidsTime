package com.example.king.kidstime.DB.model;

/**
 * Created by KING on 27.12.2017.
 */

public class ToDoListModel {
    private int mId;
    private String mText;
    private int mStatus;

    private String time;
    private String deviation;

    public ToDoListModel(int id, String text, int status){
        this.mId = id;
        this.mText = text;
        this.mStatus = status;
    }
    public ToDoListModel(int id, String text, int status, String time, String deviation){
        this.mId = id;
        this.mText = text;
        this.mStatus = status;
        this.time = time;
        this.deviation = deviation;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation = deviation;
    }
}
