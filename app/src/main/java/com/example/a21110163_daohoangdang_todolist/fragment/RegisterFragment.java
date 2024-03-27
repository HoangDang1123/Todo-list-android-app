package com.example.a21110163_daohoangdang_todolist.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.activity.HomePageActivity;
import com.example.a21110163_daohoangdang_todolist.activity.MainActivity;
import com.google.android.material.tabs.TabLayout;

public class RegisterFragment extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        View root = mainActivity.findViewById(R.id.root);
        TabLayout tab = mainActivity.findViewById(R.id.tabMode);

        View fragment_root = view.findViewById(R.id.root);
        EditText username = view.findViewById(R.id.username);
        EditText password = view.findViewById(R.id.password);
        Button register = view.findViewById(R.id.register);

        // Xử lí sự kiện click ra ngoài EditText thì sẽ ẩn keyboard
        root.setOnTouchListener((view12, motionEvent) -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();
            if(inputUsername.equals("")) {
                username.setText(getString(R.string.prompt_username));
            }
            if(inputPassword.equals("")) {
                password.setText(getString(R.string.prompt_password));
            }
            username.clearFocus();
            password.clearFocus();
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            return false;
        });
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();
                if(inputUsername.equals("")) {
                    username.setText(getString(R.string.prompt_username));
                }
                if(inputPassword.equals("")) {
                    password.setText(getString(R.string.prompt_password));
                }
                username.clearFocus();
                password.clearFocus();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();
                if(inputUsername.equals("")) {
                    username.setText(getString(R.string.prompt_username));
                }
                if(inputPassword.equals("")) {
                    password.setText(getString(R.string.prompt_password));
                }
                username.clearFocus();
                password.clearFocus();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();
                if(inputUsername.equals("")) {
                    username.setText(getString(R.string.prompt_username));
                }
                if(inputPassword.equals("")) {
                    password.setText(getString(R.string.prompt_password));
                }
                username.clearFocus();
                password.clearFocus();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            }
        });
        fragment_root.setOnTouchListener((view12, motionEvent) -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();
            if(inputUsername.equals("")) {
                username.setText(getString(R.string.prompt_username));
            }
            if(inputPassword.equals("")) {
                password.setText(getString(R.string.prompt_password));
            }
            username.clearFocus();
            password.clearFocus();
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(fragment_root.getWindowToken(), 0);
            return false;
        });

        username.setOnFocusChangeListener((view1, hasFocus) -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();
            if(inputPassword.equals("")) {
                password.setText(getString(R.string.prompt_password));
            }
            if (hasFocus && inputUsername.equals(getString(R.string.prompt_username))) {
                username.setText("");
            }
        });

        password.setOnFocusChangeListener((view1, hasFocus) -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();
            if(inputUsername.equals("")) {
                username.setText(getString(R.string.prompt_username));
            }
            if (hasFocus && inputPassword.equals(getString(R.string.prompt_password))) {
                password.setText("");
            }
        });
        register.setOnClickListener(view1 -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();
            if (!(inputUsername.equals(getString(R.string.prompt_username)) ||
                    inputPassword.equals(getString(R.string.prompt_password)))) {
                Intent intent = new Intent(view1.getContext(), HomePageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}