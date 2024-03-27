package com.example.a21110163_daohoangdang_todolist;

public class TodoList {
    private int id;
    private String content;
    private String date;
    private String time;
    private boolean isDone;

    public TodoList(int id, String content, String date, String time, boolean isDone) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.time = time;
        this.isDone = isDone;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}

    public boolean getIsDone() {return isDone;}
    public void setIsDone(boolean isDone) {this.isDone = isDone;}
}
