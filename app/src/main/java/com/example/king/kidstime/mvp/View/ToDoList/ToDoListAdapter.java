package com.example.king.kidstime.mvp.View.ToDoList;

import android.annotation.SuppressLint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.Presenter.ToDoListPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder>{

    private final ArrayList<ToDoListModel> mValues;
    private ToDoListPresenter mToDoListPresenter;

    public ToDoListAdapter(ArrayList<ToDoListModel> items, ToDoListPresenter presenter) {
        mValues = items;
        mToDoListPresenter = presenter;
    }

    public void addData(ArrayList<ToDoListModel> data){
        mValues.clear();
        mValues.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mIdView.setText(String.valueOf(position+1));
        holder.mContentView.setText(mValues.get(position).getmText());

        //holder.mStatusView.setText(mValues.get(position).getmStatus());
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
                        startTime = System.currentTimeMillis();
                        break;
                    }

                    case MotionEvent.ACTION_UP:{

                        long totalTime = System.currentTimeMillis()- startTime;
                        long totalSec = totalTime / 1000;
                        if(totalSec>=0.2){
                            mToDoListPresenter.showDeleteDialog(mValues.get(position).getmId());
                        } else {
                            mToDoListPresenter.showUpdateDialog(mValues.get(position).getmId());
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


        /*holder.content_panel.performClick(new Performc)
        }*/
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;
        @BindView(R.id.status)
        TextView mStatusView;
        @BindView(R.id.content_panel)
        LinearLayout content_panel;
        @BindView(R.id.cardPanel)
        CardView card_panel;

        public final View mView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }



        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
