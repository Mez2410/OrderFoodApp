package com.example.vuphi.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by vuphi on 3/29/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> arrayFragment = new ArrayList<>();
    ArrayList<String> arrayTitle = new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayTitle.size();
    }

    public void AddFragment(Fragment fragment, String title){
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
