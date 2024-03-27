package com.example.a21110163_daohoangdang_todolist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.example.a21110163_daohoangdang_todolist.R;
import com.example.a21110163_daohoangdang_todolist.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

// 21110163 - Đào Hoàng Đăng
public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    TextView title;

    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabMode);
        mViewPager = findViewById(R.id.view_pager);
        title = findViewById(R.id.title);

        title.setText(getResources().getString(R.string.title));
        TextPaint paint = title.getPaint();
        float width = paint.measureText(getResources().getString(R.string.title));
        Shader textShader = new LinearGradient(0, 0, width, title.getTextSize(),
                new int[]{
                        Color.parseColor("#553bdd"),
                        Color.parseColor("#fe2485"),
                }, null, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}