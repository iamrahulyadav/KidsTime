package com.example.king.kidstime.mvp.View.ToDoList;

import com.example.king.kidstime.DB.model.ToDoListModel;
import com.example.king.kidstime.mvp.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by KING on 27.12.2017.
 */

public interface ToDoListView extends BaseView{
    void update(ArrayList<ToDoListModel> list);
    void deleteDialog(final int id);
    void updateStatusDialog(final int id);
}
