package com.house.checkhouse.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.MyPagerAdapter;
import com.house.checkhouse.customer.CustomDialog;
import com.house.checkhouse.fragment.CheckListFragment;
import com.house.checkhouse.fragment.ImageFragment;

import java.util.ArrayList;
import java.util.List;

public class CheckModelActivity extends BascActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mSure;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<Fragment> mViewList = new ArrayList<>();//页卡视图集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_model);
        initWidget();
    }

    private void initWidget(){
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mSure = (ImageView) findViewById(R.id.sure_txt);
        initTab();

        mSure.setOnClickListener(this);
    }

    private void initTab(){

        //添加页卡视图
        mViewList.add(ImageFragment.newInstance("",""));
        mViewList.add(CheckListFragment.newInstance("",""));


        //添加页卡标题
        mTitleList.add("图纸模式");
        mTitleList.add("检查项模式");


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        mViewPager.setCurrentItem(3);    //跳转到哪个页面
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sure_txt:
                getDiaLog("当前01栋1单元204房间未发现有任何需要修改的地方，是否验收该楼盘，并提醒房产公司交付验收？");
                break;
        }
    }

    /**
     *
     * @param message
     */
    private void getDiaLog(String message){
        final CustomDialog dialog = new CustomDialog(this);
        dialog.setTitle("楼盘验收确认");
        dialog.setMessageText(message);
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
