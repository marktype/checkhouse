package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.HouseGridViewAdapter;
import com.house.checkhouse.model.message.HousesInfo;

import java.util.ArrayList;

public class HousesActivity extends BascActivity implements AdapterView.OnItemClickListener{
    public static final String TITLE_NAME="titlename";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        initWidget();
    }

    private void initWidget(){
        GridView mGrid = (GridView) findViewById(R.id.loupan_grid);
        TextView mTitleName = (TextView) findViewById(R.id.title_name);
        String name = getIntent().getStringExtra(TITLE_NAME);
        mTitleName.setText(name);

        HouseGridViewAdapter adapter = new HouseGridViewAdapter(this);
        adapter.setData(setData());
        mGrid.setAdapter(adapter);

        mGrid.setOnItemClickListener(this);
    }

    public ArrayList<HousesInfo> setData(){
        ArrayList<HousesInfo> list = new ArrayList<>();
        for (int i = 0;i<7;i++){
            HousesInfo info = new HousesInfo();
            info.setName("砖石广场"+i);
            list.add(info);
        }
        return list;
     }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,AHousesActivity.class);
        startActivity(intent);
    }
}
