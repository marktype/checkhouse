package com.house.checkhouse.activity;

import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.CheckHouseAdapter;
import com.house.checkhouse.model.message.CheckHouseInfo;
import com.house.checkhouse.model.message.HousesInfo;
import com.house.checkhouse.util.SaveUserInfoSharePreference;

import java.util.ArrayList;

/**
 * 第几栋
 */
public class BuildingHouseActivity extends BascActivity implements View.OnClickListener{
    private PopupWindow mPopWindow;
    private TextView mUnit;
    private SharedPreferences sp;
    private String type;   //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_house);
        sp = SaveUserInfoSharePreference.getShareSaveUserInfo(this);
        type = sp.getString(SaveUserInfoSharePreference.HOUSE_STATUS,null);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.list_houses);
        mUnit = (TextView) findViewById(R.id.unit_txt);
        selectTitle();


        CheckHouseAdapter adapter = new CheckHouseAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mUnit.setOnClickListener(this);
    }

    /**
     * 选择使用title（1、楼盘，2、预验房，3、交房陪验）
     */
    private void selectTitle(){
        LinearLayout mLoupanLin = (LinearLayout) findViewById(R.id.loupan_Lin);
        LinearLayout mYuyanFang = (LinearLayout) findViewById(R.id.yuyanfang_Lin);
        LinearLayout mJiaofuPeiyan = (LinearLayout) findViewById(R.id.jiaofupeiyan_Lin);
        switch (type){
            case "1":
                mLoupanLin.setVisibility(View.VISIBLE);
                mYuyanFang.setVisibility(View.GONE);
                mJiaofuPeiyan.setVisibility(View.GONE);
                break;
            case "2":
                mLoupanLin.setVisibility(View.GONE);
                mYuyanFang.setVisibility(View.VISIBLE);
                mJiaofuPeiyan.setVisibility(View.GONE);
                break;
            case "3":
                mLoupanLin.setVisibility(View.GONE);
                mYuyanFang.setVisibility(View.GONE);
                mJiaofuPeiyan.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }


    public ArrayList<CheckHouseInfo> setData(){
        ArrayList<CheckHouseInfo> list = new ArrayList<>();
        for (int i = 0;i<3;i++){
            CheckHouseInfo info = new CheckHouseInfo();
            info.setNum("2"+i+"F");
            info.setType(type);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.unit_txt:
                initPopView(view);
                break;
        }
    }

    /**
     * 单元数据
     */
    private ArrayList<String> getData(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1;i<5;i++){
            list.add(i+"单元");
        }
        return list;
    }
    /**
     * 初始化popWindow
     * @param view
     */
    public void initPopView(View view){
        if (mPopWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.item_popwindow_unit, null);
            mPopWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setBackgroundDrawable(new BitmapDrawable());
            ListView mList = (ListView) contentView.findViewById(R.id.unit_list);
            final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.txt_unit_item_layout,getData());
            mList.setAdapter(adapter);

            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String unit = (String) adapterView.getAdapter().getItem(i);
                    mUnit.setText(unit);
                    mPopWindow.dismiss();
                }
            });
        }
        mPopWindow.setOutsideTouchable(true);
//                mPopWindow.showAsDropDown(view, 100, 10);
        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            mPopWindow.showAsDropDown(view, 20, 0);

        }

    }
}
