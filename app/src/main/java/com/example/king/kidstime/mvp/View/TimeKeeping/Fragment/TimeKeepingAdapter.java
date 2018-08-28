package com.example.king.kidstime.mvp.View.TimeKeeping.Fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.Presenter.TimeKeepingPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //holder.mId.setText(String.valueOf(position+1));
        holder.mId.setText(mValues.get(position).getTime_start() + " - " + mValues.get(position).getTime_end());
        holder.mContent.setText(mValues.get(position).getmText());
        //holder.mStatus.setText(mValues.get(position).getTime_start() + " - " + mValues.get(position).getTime_end());

        int status = mValues.get(position).getmStatus();
        switch (status){
            case 0:{
                holder.content_panel.setBackgroundColor(holder.mView.getResources().getColor(R.color.no_completed));
                break;
            }
            case 1:{
                holder.content_panel.setBackgroundColor(holder.mView.getResources().getColor(R.color.completed));
                break;
            }
            case 2:{
                holder.content_panel.setBackgroundColor(holder.mView.getResources().getColor(R.color.can_not));
                break;
            }
        }

        holder.content_panel.setOnTouchListener(new View.OnTouchListener() {
            long startTime;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:{
                        Log.d("StatusID", String.valueOf(mValues.get(position).getmStatus()));
                        startTime = System.currentTimeMillis();
                        break;
                    }

                    case MotionEvent.ACTION_UP:{

                        long totalTime = System.currentTimeMillis()- startTime;
                        long totalSec = totalTime / 1000;
                        if(totalSec>=0.2){
                            mTimeKeepengPresenter.showDeleteDialog(mValues.get(position).getmId());
                        } else {
                            mTimeKeepengPresenter.showUpdateDialog(mValues.get(position).getmId());
                        }

                        break;
                    }

                    case MotionEvent.ACTION_MOVE:{
                        break;
                    }
                    default: break;
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_TK)
        TextView mId;
        @BindView(R.id.content_TK)
        TextView mContent;
        @BindView(R.id.status_TK)
        TextView mStatus;
        @BindView(R.id.content_panel_TK)
        LinearLayout content_panel;

        private final View mView;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
        }
    }
}
