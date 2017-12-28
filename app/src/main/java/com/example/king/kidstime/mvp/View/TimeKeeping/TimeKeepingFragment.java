package com.example.king.kidstime.mvp.View.TimeKeeping;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.king.kidstime.R;

public class TimeKeepingFragment extends Fragment implements TimeKeepengView{


    public TimeKeepingFragment() {
    }

    public static TimeKeepingFragment newInstance() {
        TimeKeepingFragment fragment = new TimeKeepingFragment();

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
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);
        //todo задать адаптер
        return view;
    }
}
