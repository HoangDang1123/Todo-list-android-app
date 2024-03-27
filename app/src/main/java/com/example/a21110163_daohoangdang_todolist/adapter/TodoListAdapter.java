package com.example.a21110163_daohoangdang_todolist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a21110163_daohoangdang_todolist.OnItemClickListener;
import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.TodoList;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private ArrayList<TodoList> todoModalArrayList;
    private Context context;
    private OnItemClickListener listener;
    public TodoListAdapter(ArrayList<TodoList> todoModalArrayList, Context context) {
        this.todoModalArrayList = todoModalArrayList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        TodoList todoList = todoModalArrayList.get(position);
        holder.contentTxt.setText(todoList.getContent());
        holder.dateTxt.setText(todoList.getDate());
        holder.timeTxt.setText(todoList.getTime());
        holder.isDoneCb.setChecked(todoList.getIsDone());

        holder.isDoneCb.setOnClickListener(view -> {
            if(listener != null) {
                long itemId = getItemId(position);
                listener.onCheckBoxClick(position, itemId);
            }
        });

        holder.editBtn.setOnClickListener(view -> {
            if(listener != null) {
                long itemId = getItemId(position);
                listener.onEditButtonClick(position, itemId);
            }
        });

        holder.deleteBtn.setOnClickListener(view -> {
            if(listener != null) {
                long itemId = getItemId(position);
                listener.onDeleteButtonClick(position, itemId);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return todoModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView contentTxt, dateTxt, timeTxt;
        CheckBox isDoneCb;
        Button editBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            contentTxt = itemView.findViewById(R.id.contentTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            isDoneCb = itemView.findViewById(R.id.isDoneCb);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
