package com.example.king.kidstime.mvp.View.TimeKeeping;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.Presenter.TimeKeepingPresenter;
import com.example.king.kidstime.mvp.Presenter.ToDoListPresenter;

import java.util.ArrayList;
import java.util.List;

public class TimeKeepingAdapter extends RecyclerView.Adapter<TimeKeepingAdapter.ViewHolder> {

    private final ArrayList<ToDoListModel> mValues;
    private TimeKeepingPresenter mTimeKeepengPresenter;

    public TimeKeepingAdapter(ArrayList<ToDoListModel> items, TimeKeepingPresenter presenter) {
        mValues = items;
        mTimeKeepengPresenter = presenter;
    }

    public void addData(ArrayList<ToDoListModel> data){
        mValues.clear();
        mValues.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
