package com.example.a21110163_daohoangdang_todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "todolist";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mytodolists";
    private static final String ID_COL = "id";
    private static final String CONTENT_COL = "content";
    private static final String DATE_COL = "date";
    private static final String TIME_COL = "time";
    private static final String IS_DONE_COL = "isDone";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CONTENT_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT,"
                + IS_DONE_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewTask(String content, String date, String time, boolean isDone) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(CONTENT_COL, content);
        values.put(DATE_COL, date);
        values.put(TIME_COL, time);
        values.put(IS_DONE_COL, isDone);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public void updateTask(int id, String content, String date, String time, boolean isDone) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(CONTENT_COL, content);
        values.put(DATE_COL, date);
        values.put(TIME_COL, time);
        values.put(IS_DONE_COL, isDone);

        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        Log.d("id", String.valueOf(id));
    }

    public void deleteTask(int id) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};

        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<TodoList> readTodoList() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorTodoLists = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<TodoList> todoModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorTodoLists.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                todoModalArrayList.add(new TodoList(
                        cursorTodoLists.getInt(0),
                        cursorTodoLists.getString(1),
                        cursorTodoLists.getString(2),
                        cursorTodoLists.getString(3),
                        cursorTodoLists.getInt(4) == 1));
            } while (cursorTodoLists.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorTodoLists.close();
        return todoModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

