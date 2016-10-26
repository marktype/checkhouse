package com.house.checkhouse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/10/21.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mViewList;
    private List<String> mTitleList;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> mViewList, List<String> mTitleList) {
        super(fm);
        this.mViewList = mViewList;
        this.mTitleList = mTitleList;
    }


    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mTitleList.get(position%mTitleList.size());
    }
}