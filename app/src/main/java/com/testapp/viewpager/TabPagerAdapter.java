package com.testapp.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

public class TabPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> mFragmentList;
    //public int pageCount = 0;

    public TabPagerAdapter(FragmentManager fragmentManager,List<Fragment> fragments) {
        super(fragmentManager);
        this.mFragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        Timber.i("Fragment %s", fragment);
        mFragmentList.add(fragment);

    }

    public void removeFragment(int position) {
        Timber.i("Position %d", position);
        mFragmentList.remove(position);

    }


    public void clear() {
        mFragmentList = new ArrayList<>();
    }
}

