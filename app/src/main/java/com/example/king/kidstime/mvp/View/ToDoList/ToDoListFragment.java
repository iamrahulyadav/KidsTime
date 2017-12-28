package com.example.king.kidstime.mvp.View.ToDoList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.king.kidstime.DB.DbHelper;
import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.Presenter.ToDoListPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ToDoListFragment extends Fragment implements ToDoListView{

    @BindView(R.id.toDoList_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.addNewTask)
    FloatingActionButton btnAddNewTask;

    ToDoListAdapter adapter;
    private ArrayList<ToDoListModel> mList = null;
    private ToDoListPresenter mToDoListPresenter;
    DbHelper dbHelper = new DbHelper(this.getContext());

    public ToDoListFragment(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
        mList = dbHelper.getTaskList();
    }
    public static ToDoListFragment newInstance(DbHelper dbHelper) {
        ToDoListFragment fragment = new ToDoListFragment(dbHelper);

        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.activity_todolist, container, false);


        mToDoListPresenter = new ToDoListPresenter(dbHelper);
        mToDoListPresenter.attachView(this);
        ButterKnife.bind(this, view);

        adapter = new ToDoListAdapter(mList, mToDoListPresenter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        return view;
    }

    private void deleteTask(){

    }


    private void addTask(){
        final EditText taskEditText = new EditText(getContext());
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Добавить новую задачу")
                //.setMessage("Введите тект задачи") todo добавить что то информативное :)
                .setView(taskEditText)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String task = String.valueOf(taskEditText.getText());
                        mToDoListPresenter.insert(task);
                    }
                })
                .setNegativeButton("Отменить", null)
                .create();
        dialog.show();
    }

    @Override
    public void update(ArrayList<ToDoListModel> list) {
        adapter.addData(list);
    }

    @Override
    public void deleteDialog(final int id) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Вы хотите удалить запись?")
                //.setMessage("Введите тект задачи") todo добавить что то информативное :)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mToDoListPresenter.delete(id);
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
                //.setMessage("Введите тект задачи") todo добавить что то информативное :)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mToDoListPresenter.updateStatus(id, 1);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mToDoListPresenter.updateStatus(id, 0);
                    }
                })
                .setNeutralButton("Не могу", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mToDoListPresenter.updateStatus(id, 2);
                    }
                })
                .create();
        dialog.show();
    }
}
