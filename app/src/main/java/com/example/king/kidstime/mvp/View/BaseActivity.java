package com.example.king.kidstime.mvp.View;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.king.kidstime.DB.DbHelper;
import com.example.king.kidstime.R;
import com.example.king.kidstime.mvp.View.ToDoList.ToDoListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        dbHelper = new DbHelper(this);

        Fragment fragment = null;
        String index = this.getIntent().getStringExtra("index");

        switch (index){
            case "ToDoList":{
                fragment = ToDoListFragment.newInstance(dbHelper);
                break;
            }
            case "TimeKeeping":{
                break;
            }
        }

        if(fragment!=null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.BaseframeLayout, fragment, fragment.getTag());
            fragmentTransaction.commit();
        }

    }
}
