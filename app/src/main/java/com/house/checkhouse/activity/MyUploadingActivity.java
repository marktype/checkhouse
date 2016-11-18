package com.house.checkhouse.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.TaskItemAdapter;
import com.house.checkhouse.model.message.TaskItem;

import java.util.ArrayList;

public class MyUploadingActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_uploading);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.uploading_list);
        TaskItemAdapter adapter = new TaskItemAdapter(this);
        adapter.setData(getData());
        mList.setAdapter(adapter);
    }

    private ArrayList<TaskItem> getData(){
        ArrayList<TaskItem> itemList = new ArrayList<>();
        for (int j = 0;j<4;j++){
            TaskItem item = new TaskItem();
            item.setTitle("xxxx小区可以查看xxx");
            item.setContent("**验收");
            itemList.add(item);
        }
        return itemList;
    }

}
