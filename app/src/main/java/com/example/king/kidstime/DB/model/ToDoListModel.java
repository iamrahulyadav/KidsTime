package com.example.king.kidstime.DB.model;

/**
 * Created by KING on 27.12.2017.
 */

public class ToDoListModel {
    private int mId;
    private String mText;
    private int mStatus;

    private String mDate;
    private String time_start;
    private String time_end;
    private String deviation;

    public ToDoListModel(int id, String text, int status){
        this.mId = id;
        this.mText = text;
        this.mStatus = status;
    }
    public ToDoListModel(int id, String text, int status, String date, String time_start, String time_end, String deviation){
        this.mId = id;
        this.mText = text;
        this.mStatus = status;
        this.mDate = date;
        this.time_start = time_start;
        this.time_end = time_end;
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

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation = deviation;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return mText;
    }
}
