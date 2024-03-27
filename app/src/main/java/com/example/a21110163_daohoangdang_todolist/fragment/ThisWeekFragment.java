package com.example.a21110163_daohoangdang_todolist.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.TodoList;
import com.example.a21110163_daohoangdang_todolist.adapter.TodoListAdapter;

public class ThisWeekFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_this_week, container, false);

        return view;
    }
}