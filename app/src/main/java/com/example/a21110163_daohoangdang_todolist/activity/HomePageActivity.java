package com.example.a21110163_daohoangdang_todolist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.a21110163_daohoangdang_todolist.DBHandler;
import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.TodoList;
import com.example.a21110163_daohoangdang_todolist.adapter.TodoListAdapter;
import com.example.a21110163_daohoangdang_todolist.adapter.ViewPagerAdapter2;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<TodoList> todoModalArrayList;
    private DBHandler dbHandler;
    private TodoListAdapter todoListAdapter;
    private RecyclerView todoListRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager2);
        ViewPagerAdapter2 viewPagerAdapter = new ViewPagerAdapter2(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}