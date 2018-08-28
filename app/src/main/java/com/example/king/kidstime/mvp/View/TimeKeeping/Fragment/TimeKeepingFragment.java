package com.example.king.kidstime.mvp.View.TimeKeeping.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.Presenter.TimeKeepingPresenter;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeKeepingFragment extends Fragment implements TimeKeepengView{

    @BindView(R.id.timeKeepingList_recycler)
    RecyclerView recyclerView;

    private TimeKeepingAdapter adapter;
    private ArrayList<ToDoListModel> mList = null;
    private static final TimeKeepingPresenter mTKPresenter = new TimeKeepingPresenter();

    public TimeKeepingFragment() {
    }

    public static TimeKeepingFragment newInstance(int day_id) {
        TimeKeepingFragment fragment = new TimeKeepingFragment();
        Bundle args = new Bundle();
        args.putInt("day_id", day_id);
        args.putSerializable("presenter", (Serializable) mTKPresenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);
        ButterKnife.bind(this, view);

        mTKPresenter.attachView(this);
        mTKPresenter.attachContext(getContext());
        mTKPresenter.setDay_id(getArguments().getInt("day_id"));
        mTKPresenter.getList();

        adapter = new TimeKeepingAdapter(mList, mTKPresenter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void setList(ArrayList<ToDoListModel> list) {
        mList = list;
    }

    @Override
    public void update(ArrayList<ToDoListModel> list) {
        Log.d("Update", "update");
        adapter.addData(list);
    }

    @Override
    public void deleteDialog(final int id) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Вы хотите удалить запись?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTKPresenter.delete(id);
                    }
                })
                .setNegativeButton("Нет", null)
                .create();
        dialog.show();
    }

    @Override
    public void updateStatusDialog(final int id) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Выполнил задачу?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTKPresenter.updateStatus(id, 1);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTKPresenter.updateStatus(id, 0);
                    }
                })
                .setNeutralButton("Не могу", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTKPresenter.updateStatus(id, 2);
                    }
                })
                .create();
        dialog.show();
    }


}
