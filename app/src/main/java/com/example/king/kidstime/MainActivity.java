package com.example.king.kidstime;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.king.kidstime.DB.DbHelper;
import com.example.king.kidstime.mvp.View.BaseActivity;
import com.example.king.kidstime.mvp.View.ToDoList.ToDoListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnToDoList)
    ImageButton btnToDoList;
    @BindView(R.id.btnTimeKeeping)
    ImageButton btnTimeKeeping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), BaseActivity.class);
                intent.putExtra("index", "ToDoList");
                view.getContext().startActivity(intent);
            }
        });

        btnTimeKeeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BaseActivity.class);
                intent.putExtra("index", "TabsTimeKeepingFragment");
                view.getContext().startActivity(intent);
            }
        });
    }
}
