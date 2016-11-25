package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.ProjectAdapter;
import com.house.checkhouse.model.message.ProjectInfo;

import java.util.ArrayList;

public class ProjectInfoActivity extends BascActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.project_list);

        ProjectAdapter adapter = new ProjectAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(this);
    }

    private ArrayList<ProjectInfo> setData(){
        ArrayList<ProjectInfo> list = new ArrayList<>();
        for (int i = 0;i<4;i++){
            ProjectInfo info = new ProjectInfo();
            info.setImage("http://i1.hexunimg.cn/2014-08-15/167580248.jpg");
            info.setTime("2016.10.25");
            info.setContent("[通知] 成都市光华街xxxx小区整改完成，可前往查看");
            list.add(info);
        }

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, WaitTaskDetialActivity.class);
        startActivity(intent);
    }
}
