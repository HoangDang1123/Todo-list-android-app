package com.example.a21110163_daohoangdang_todolist.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.a21110163_daohoangdang_todolist.fragment.OthersFragment;
import com.example.a21110163_daohoangdang_todolist.fragment.ThisMonthFragment;
import com.example.a21110163_daohoangdang_todolist.fragment.ThisWeekFragment;
import com.example.a21110163_daohoangdang_todolist.fragment.TodayFragment;

public class ViewPagerAdapter2 extends FragmentPagerAdapter {
    public ViewPagerAdapter2(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TodayFragment();
            case 1:
                return new ThisWeekFragment();
            case 2:
                return new ThisMonthFragment();
            case 3:
                return new OthersFragment();
            default:
                return new TodayFragment();
        }
    }

    @Override
    public int getCount() {
        return 4; // số lượng tab
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title = "Today";
                break;
            case 1:
                title = "This week";
                break;
            case 2:
                title = "This month";
                break;
            case 3:
                title = "Others";
                break;
        }
        return title;
    }
}
