package com.example.a21110163_daohoangdang_todolist.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.a21110163_daohoangdang_todolist.fragment.SignInFragment;
import com.example.a21110163_daohoangdang_todolist.fragment.RegisterFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SignInFragment();
            case 1:
                return new RegisterFragment();
            default:
                return new SignInFragment();
        }
    }

    @Override
    public int getCount() {
        return 2; // số lượng tab
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title = "SIGN IN";
                break;
            case 1:
                title = "REGISTER";
                break;
        }
        return title;
    }
}
