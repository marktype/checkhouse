package com.house.checkhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.CheckItemAdapter;
import com.house.checkhouse.model.message.HousesInfo;

import java.util.ArrayList;

public class CheckDetialActivity extends BascActivity implements View.OnClickListener{
    private ImageView mStatus,mBuWei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detial);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.list_houses);
        mStatus = (ImageView) findViewById(R.id.zhuangtai_txt);
        mBuWei = (ImageView) findViewById(R.id.buwei_txt);
        TextView mNewProblem = (TextView) findViewById(R.id.add_problem_txt);


        CheckItemAdapter adapter = new CheckItemAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mStatus.setOnClickListener(this);
        mBuWei.setOnClickListener(this);
        mNewProblem.setOnClickListener(this);
    }

    public ArrayList<HousesInfo> setData(){
        ArrayList<HousesInfo> list = new ArrayList<>();
        for (int i = 0;i<5;i++){
            HousesInfo info = new HousesInfo();
            info.setType(1);
            info.setName(i+"-内外卧室检查");
            list.add(info);
        }
        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhuangtai_txt:
                    isOpenStatus();
                break;
            case R.id.buwei_txt:
                    isOpenBuwei();
                break;
            case R.id.add_problem_txt:
                Intent intent = new Intent(this,AddProblemActivity.class);
                startActivity(intent);
                break;
        }
    }
    private Boolean zhunagtaiFlag = false,buweiFlag = false;


    public void isOpenStatus(){
        if (zhunagtaiFlag){
            mStatus.setImageResource(R.mipmap.zhuangtai);
            zhunagtaiFlag = false;
        }else {
            mStatus.setImageResource(R.mipmap.zhuangtai_on);
            zhunagtaiFlag = true;
        }
        if (buweiFlag){
        buweiFlag = false;
        }
        mBuWei.setImageResource(R.mipmap.buwei);
    }
    public void isOpenBuwei(){
        if (buweiFlag){
            mBuWei.setImageResource(R.mipmap.buwei);
            buweiFlag = false;
        }else {
            mBuWei.setImageResource(R.mipmap.buwei_on);
            buweiFlag = true;
        }
        if (zhunagtaiFlag){
        zhunagtaiFlag = false;
        }
        mStatus.setImageResource(R.mipmap.zhuangtai);
    }
}
