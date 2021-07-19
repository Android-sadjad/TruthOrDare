package com.example.truthordare.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> frgList;
    private final List<String> titleList;


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        frgList = new ArrayList<>();
        titleList = new ArrayList<>();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        return frgList.get(position);
    }

    @Nullable

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }


    @Override
    public int getCount() {
        return titleList.size();
    }


    public void addToList(Fragment fragment, String title) {

            frgList.add(fragment);
            titleList.add(title);

    }
}
