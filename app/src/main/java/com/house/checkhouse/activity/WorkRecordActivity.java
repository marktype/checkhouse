package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.MyPagerAdapter;
import com.house.checkhouse.fragment.FinishRecordFragment;
import com.house.checkhouse.fragment.WaitRecordFragment;

import java.util.ArrayList;
import java.util.List;

public class WorkRecordActivity extends BascActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<Fragment> mViewList = new ArrayList<>();//页卡视图集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_record);
        initWidget();
    }

    private void initWidget(){
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        ImageView mWrite = (ImageView) findViewById(R.id.write_img);
        initTab();

        mWrite.setOnClickListener(this);
    }

    private void initTab(){

        //添加页卡视图
        mViewList.add(WaitRecordFragment.newInstance("",""));
        mViewList.add(FinishRecordFragment.newInstance("",""));


        //添加页卡标题
        mTitleList.add("未完成");
        mTitleList.add("已完成");


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        mViewPager.setCurrentItem(3);    //跳转到哪个页面
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.write_img:
                Intent intent = new Intent(this,AddWorkRecordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
