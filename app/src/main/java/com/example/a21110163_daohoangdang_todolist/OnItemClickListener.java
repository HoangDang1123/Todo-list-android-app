package com.example.a21110163_daohoangdang_todolist;

public interface OnItemClickListener {
    void onCheckBoxClick(int position, long itemId);
    void onEditButtonClick(int position, long itemId);
    void onDeleteButtonClick(int position, long itemId);
}