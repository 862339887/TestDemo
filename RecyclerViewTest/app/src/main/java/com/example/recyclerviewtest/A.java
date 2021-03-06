package com.example.recyclerviewtest;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class A extends RecyclerView.ViewHolder {

    public A(View itemView) {
        super(itemView);
    }
}

    public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
            IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }