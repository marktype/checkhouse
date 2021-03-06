package com.house.checkhouse.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.MyPagerAdapter;
import com.house.checkhouse.fragment.ProblemHouseTypeFragment;
import com.house.checkhouse.fragment.ProblemTimeFragment;

import java.util.ArrayList;
import java.util.List;

public class ProblemActivity extends BascActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<Fragment> mViewList = new ArrayList<>();//页卡视图集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        initWidget();
    }

    private void initWidget(){
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        initTab();
    }

    private void initTab(){

        //添加页卡视图
        mViewList.add(ProblemTimeFragment.newInstance("",""));
        mViewList.add(ProblemHouseTypeFragment.newInstance("",""));


        //添加页卡标题
        mTitleList.add("按时间排序");
        mTitleList.add("按户型排序");


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        mViewPager.setCurrentItem(3);    //跳转到哪个页面
    }
}
