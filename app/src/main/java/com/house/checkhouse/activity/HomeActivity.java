package com.house.checkhouse.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.fragment.HomeFragment;
import com.house.checkhouse.fragment.MessageFragment;
import com.house.checkhouse.fragment.MyFragment;

public class HomeActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initTab();
    }


    //自定义tab
    public View setTabMenu(String name, int image) {
        View v = LayoutInflater.from(HomeActivity.this).inflate(R.layout.tab_own_item_layout, null);
        TextView menuText = (TextView) v.findViewById(R.id.tab_menu_txt);
        ImageView menuImg = (ImageView) v.findViewById(R.id.tab_image);
        if (TextUtils.isEmpty(name)){
            menuText.setVisibility(View.GONE);
        }else {
            menuText.setVisibility(View.VISIBLE);
            menuText.setText(name);
        }
        menuImg.setImageResource(image);
        return v;
    }

    public void initTab() {
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //使用fragment代替activity转换实现
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(setTabMenu("首页", R.drawable.tab_item1_selector)), HomeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(setTabMenu("消息", R.drawable.tab_item2_selector)), MessageFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(setTabMenu("我的", R.drawable.tab_item3_selector)), MyFragment.class, null);

    }
}
