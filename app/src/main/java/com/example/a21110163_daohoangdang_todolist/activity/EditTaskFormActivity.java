package com.example.a21110163_daohoangdang_todolist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a21110163_daohoangdang_todolist.DBHandler;
import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.fragment.TodayFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTaskFormActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    private EditText contentEdt, dateEdt, timeEdt;
    private Button updateBtn;
    private View root;
    private boolean isDatePickerShown = false, isTimePickerShown = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_form);

        root = findViewById(R.id.root);
        contentEdt = findViewById(R.id.contentEdt);
        dateEdt = findViewById(R.id.dateEdt);
        timeEdt = findViewById(R.id.timeEdt);
        updateBtn = findViewById(R.id.updateBtn);

        dbHandler = new DBHandler(EditTaskFormActivity.this);

        String originalContent = getIntent().getStringExtra("content");
        String originalDate = getIntent().getStringExtra("date");
        String originalTime = getIntent().getStringExtra("time");
        String position = getIntent().getStringExtra("id");

        contentEdt.setText(originalContent);
        dateEdt.setText(originalDate);
        timeEdt.setText(originalTime);

        dateEdt.setKeyListener(null);
        timeEdt.setKeyListener(null);

        // Xử lí sự kiện click ra ngoài EditText thì sẽ ẩn keyboard
        root.setOnTouchListener((view12, motionEvent) -> {
            String inputContent = contentEdt.getText().toString();
            String selectedDate = dateEdt.getText().toString();
            String selectedTime = timeEdt.getText().toString();
            if(inputContent.equals("")) {
                contentEdt.setText(originalContent);
            }
            if(selectedDate.equals("")) {
                dateEdt.setText(originalDate);
            }
            if(selectedTime.equals("")) {
                timeEdt.setText(originalTime);
            }
            contentEdt.clearFocus();
            dateEdt.clearFocus();
            timeEdt.clearFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            return false;
        });

        contentEdt.setOnFocusChangeListener((view1, hasFocus) -> {
            String inputContent = contentEdt.getText().toString();
            String selectedDate = dateEdt.getText().toString();
            String selectedTime = timeEdt.getText().toString();
            if(selectedDate.equals("")) {
                dateEdt.setText(originalDate);
            }
            if(selectedTime.equals("")) {
                timeEdt.setText(originalTime);
            }
            if(hasFocus && inputContent.equals(originalContent)) {
                contentEdt.setText("");
            }
        });

        dateEdt.setOnFocusChangeListener((view1, hasFocus) -> {
            String inputContent = contentEdt.getText().toString();
            String selectedDate = dateEdt.getText().toString();
            String selectedTime = timeEdt.getText().toString();
            if(inputContent.equals("")) {
                contentEdt.setText(originalContent);
            }
            if(selectedTime.equals("")) {
                timeEdt.setText(originalTime);
            }
            if(hasFocus && selectedDate.equals(originalDate)) {
                dateEdt.setText("");
            }
            showDatePickerDialog();
        });

        timeEdt.setOnFocusChangeListener((view1, hasFocus) -> {
            String inputContent = contentEdt.getText().toString();
            String selectedDate = dateEdt.getText().toString();
            String selectedTime = timeEdt.getText().toString();
            if(inputContent.equals("")) {
                contentEdt.setText(originalContent);
            }
            if(selectedDate.equals("")) {
                dateEdt.setText(originalDate);
            }
            if(hasFocus && selectedTime.equals(originalTime)) {
                timeEdt.setText("");
            }
            showTimePickerDialog();
        });

        updateBtn.setOnClickListener(view1 -> {
            String inputContent = contentEdt.getText().toString();
            String selectedDate = dateEdt.getText().toString();
            String selectedTime = timeEdt.getText().toString();
            boolean isDone = false;
            dbHandler.updateTask(Integer.valueOf(position), inputContent, selectedDate, selectedTime, isDone);
            Toast.makeText(EditTaskFormActivity.this, "Task has been updated.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view1.getContext(), HomePageActivity.class);
            startActivity(intent);
        });
    }

    // Hiển thị Calendar để chọn date
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateSelectedDate(calendar);
            isDatePickerShown = false; // Đánh dấu rằng DatePickerDialog đã được đóng
        };

        // Lấy ngày, tháng, năm ban đầu từ Calendar
        int initialYear = calendar.get(Calendar.YEAR);
        int initialMonth = calendar.get(Calendar.MONTH);
        int initialDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                initialYear,
                initialMonth,
                initialDay
        );

        // Xử lý sự kiện khi DatePickerDialog bị hủy
        datePickerDialog.setOnCancelListener(dialog -> {
            isDatePickerShown = true;
            dateEdt.clearFocus();
        });

        // Hiển thị DatePickerDialog nếu chưa được hiển thị trước đó hoặc muốn mở lại
        if (!isDatePickerShown) {
            datePickerDialog.show();
            isDatePickerShown = true;
        }
    }

    private void updateSelectedDate(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = sdf.format(calendar.getTime());
        dateEdt.setText(selectedDate);
        dateEdt.clearFocus();
    }

    //Hiển thị Clock để chọn time
    private void showTimePickerDialog() {
        if(!isTimePickerShown) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(EditTaskFormActivity.this,
                    (timePicker, hourOfDay, minuteOfDay) -> {
                        String selectedTime = hourOfDay + ":" + minuteOfDay;
                        timeEdt.setText(selectedTime);
                        timeEdt.clearFocus();
                        isTimePickerShown = false;
                    },
                    hour,
                    minute,
                    false
            );
            timePickerDialog.show();
            isTimePickerShown = true;
        }
    }
}