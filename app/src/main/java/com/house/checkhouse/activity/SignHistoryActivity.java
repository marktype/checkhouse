package com.house.checkhouse.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.SignHistoryAdapter;
import com.house.checkhouse.model.message.SignHistoryInfo;

import java.util.ArrayList;

public class SignHistoryActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_history);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.sign_list);
        SignHistoryAdapter adapter = new SignHistoryAdapter(this);
        adapter.setData(getData());
        mList.setAdapter(adapter);

    }

    private  ArrayList<SignHistoryInfo> getData(){
        ArrayList<SignHistoryInfo> list = new ArrayList<>();
        for (int i =0;i<5;i++){
            SignHistoryInfo info = new SignHistoryInfo();
            info.setData("11/1"+i);
            info.setWeek("星期四");
            info.setStatus("正常");
            info.setStartTime("9:00");
            info.setEndTime("18:00");
            info.setStartSign("建设路（上班签到）");
            info.setEndSign("建设路（下班签到）");
            list.add(info);
        }
        return list;
    }


}
