package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.HouseGridViewAdapter;
import com.house.checkhouse.model.message.HousesInfo;

import java.util.ArrayList;

public class RidgepoleActivity extends BascActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ridgepole);
        initWidget();
    }

    private void initWidget(){
        GridView mGrid = (GridView) findViewById(R.id.loupan_grid);
        HouseGridViewAdapter adapter = new HouseGridViewAdapter(this);
        adapter.setData(setData());
        mGrid.setAdapter(adapter);

        mGrid.setOnItemClickListener(this);
    }

    public ArrayList<HousesInfo> setData(){
        ArrayList<HousesInfo> list = new ArrayList<>();
        for (int i = 0;i<6;i++){
            HousesInfo info = new HousesInfo();
            info.setName(i+"栋");
            list.add(info);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,BuildingHouseActivity.class);
        startActivity(intent);
    }
}
