package com.example.a21110163_daohoangdang_todolist.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.a21110163_daohoangdang_todolist.DBHandler;
import com.example.a21110163_daohoangdang_todolist.OnItemClickListener;
import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.TodoList;
import com.example.a21110163_daohoangdang_todolist.activity.AddTaskFormActivity;
import com.example.a21110163_daohoangdang_todolist.activity.EditTaskFormActivity;
import com.example.a21110163_daohoangdang_todolist.adapter.TodoListAdapter;

import java.util.ArrayList;

public class TodayFragment extends Fragment {
    private ArrayList<TodoList> todoModalArrayList;
    private DBHandler dbHandler;
    private TodoListAdapter todoListAdapter;
    private RecyclerView todoListRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        todoModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getContext());

        // getting our course array
        // list from db handler class.
        todoModalArrayList = dbHandler.readTodoList();

        // on below line passing our array list to our adapter class.
        todoListAdapter = new TodoListAdapter(todoModalArrayList, getContext());
        todoListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onCheckBoxClick(int position, long itemId) {
                TodoList item = todoModalArrayList.get(position);
                item.setIsDone(!item.getIsDone());

                int id = item.getId();
                String inputContent = item.getContent();
                String selectedDate = item.getDate();
                String selectedTime = item.getTime();
                boolean isDone = item.getIsDone();
                dbHandler.updateTask(id, inputContent, selectedDate, selectedTime, isDone);
            }

            @Override
            public void onEditButtonClick(int position, long itemId) {
                TodoList item = todoModalArrayList.get(position);

                int id = item.getId();
                String inputContent = item.getContent();
                String selectedDate = item.getDate();
                String selectedTime = item.getTime();
                boolean isDone = item.getIsDone();
                onClickGoToUpdateTask(id, inputContent, selectedDate, selectedTime, isDone);
            }

            @Override
            public void onDeleteButtonClick(int position, long itemId) {
                TodoList item = todoModalArrayList.get(position);

                int id = item.getId();
                dbHandler.deleteTask(id);
                getActivity().finish();
                startActivity(getActivity().getIntent());
            }
        });

        todoListRV = view.findViewById(R.id.recycler_view);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        todoListRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        todoListRV.setAdapter(todoListAdapter);

        Button addTaskBtn = view.findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), AddTaskFormActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void onClickGoToUpdateTask(int position, String inputContent, String selectedDate, String selectedTime, boolean isDone) {
        Intent intent = new Intent(getContext(), EditTaskFormActivity.class);
        intent.putExtra("id", String.valueOf(position));
        intent.putExtra("content", inputContent);
        intent.putExtra("date", selectedDate);
        intent.putExtra("time", selectedTime);
        startActivity(intent);
    }
}