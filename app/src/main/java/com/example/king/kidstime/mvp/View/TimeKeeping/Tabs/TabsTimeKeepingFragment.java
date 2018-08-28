package com.example.king.kidstime.mvp.View.TimeKeeping.Tabs;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.Presenter.TimeKeepingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KING on 03.01.2018.
 */

public class TabsTimeKeepingFragment extends Fragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager_TK)
    ViewPager viewPager;
    @BindView(R.id.addNewTime)
    FloatingActionButton btnAddNewTime;

    TabsPagerAdapter adapter;
    TimeKeepingPresenter presenter;

    public TabsTimeKeepingFragment() {

    }

    public static TabsTimeKeepingFragment newInstance() {
        TabsTimeKeepingFragment fragment = new TabsTimeKeepingFragment();
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
        final View view = inflater.inflate(R.layout.fragment_time_keeping, container, false);
        ButterKnife.bind(this, view);
        initTabs();


        btnAddNewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter = (TimeKeepingPresenter) adapter.getItem(viewPager.getCurrentItem()).getArguments().getSerializable("presenter");
                showInsertDialog();
            }
        });

        return view;
    }

    public void update(){
        adapter.notifyDataSetChanged();
    }

    private void initTabs() {
        adapter = new TabsPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        presenter = (TimeKeepingPresenter) adapter.getItem(viewPager.getCurrentItem()).getArguments().getSerializable("presenter");
        presenter.setPagerAdapter(adapter);
    }

    public void showInsertDialog(){
        final View viewDialog = getLayoutInflater().inflate(R.layout.insert_tk_dialog, null);
        final EditText timeStart = ButterKnife.findById(viewDialog ,R.id.timeStart);
        final EditText timeEnd = ButterKnife.findById(viewDialog ,R.id.timeEnd);
        final Spinner spinner = ButterKnife.findById(viewDialog ,R.id.chooseTask);

        timeStart.setFocusable(false);
        timeEnd.setFocusable(false);

        ArrayAdapter<ToDoListModel> arrayAdapter = new ArrayAdapter<>(this.getContext(),
                R.layout.support_simple_spinner_dropdown_item, presenter.getDbHelper().getList("ToDoList", 0));
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Добавить новое время?")
                .setView(viewDialog)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToDoListModel model = (ToDoListModel) spinner.getSelectedItem();
                        presenter.insert(model.getmId(), viewPager.getCurrentItem(), timeStart.getText().toString(), timeEnd.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Отменить", null)
                .create();
        dialog.show();

        timeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog timeDialog = new TimeDialog(getContext(), timeStart);

            }
        });
        timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog timeDialog = new TimeDialog(getContext(), timeEnd);
            }
        });
    }

}
