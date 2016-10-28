package com.house.checkhouse.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.CheckHouseAdapter;
import com.house.checkhouse.model.message.CheckHouseInfo;
import com.house.checkhouse.model.message.HousesInfo;

import java.util.ArrayList;

public class BuildingHouseActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_house);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.list_houses);

        CheckHouseAdapter adapter = new CheckHouseAdapter(this);
        adapter.setData(setData());

        mList.setAdapter(adapter);

    }

    public ArrayList<CheckHouseInfo> setData(){
        ArrayList<CheckHouseInfo> list = new ArrayList<>();
        for (int i = 0;i<3;i++){
            CheckHouseInfo info = new CheckHouseInfo();
            info.setNum("2"+i+"F");
            ArrayList<HousesInfo> infoList = new ArrayList<>();
            for (int j = 0;j<6;j++){
                HousesInfo housesInfo = new HousesInfo();
                housesInfo.setName("270"+j);
                if (j%2 == 0){
                    housesInfo.setType(1);
                }
                infoList.add(housesInfo);
            }
            info.setList(infoList);
            list.add(info);
        }
        return list;
    }


}
