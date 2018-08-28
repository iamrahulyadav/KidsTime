package com.example.king.kidstime.mvp.Base;

import com.example.king.kidstime.DB.model.ToDoListModel;

import java.util.ArrayList;

/**
 * Created by KING on 14.12.2017.
 */

public interface BaseView {
    void setList(ArrayList<ToDoListModel> list);
    void update(ArrayList<ToDoListModel> list);

    void deleteDialog(final int id); //todo сплошной копипаст
    void updateStatusDialog(final int id); //todo сплошной копипаст, оптимизировать код
}
