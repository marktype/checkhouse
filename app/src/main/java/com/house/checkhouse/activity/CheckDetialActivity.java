package com.house.checkhouse.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.house.checkhouse.BascActivity;
import com.house.checkhouse.R;
import com.house.checkhouse.adapter.CheckItemAdapter;
import com.house.checkhouse.model.message.HousesInfo;
import com.house.checkhouse.util.CheckConstants;
import com.house.checkhouse.util.SaveUserInfoSharePreference;

import java.util.ArrayList;

/**
 * 检验人员检查详细列表
 */

public class CheckDetialActivity extends BascActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView mStatus,mBuWei;
    private PopupWindow mPopProWindow;
    private ImageView mBgTxt;
    private SharedPreferences sp;
    private String type;   //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detial);
        sp = SaveUserInfoSharePreference.getShareSaveUserInfo(this);
        type = sp.getString(SaveUserInfoSharePreference.HOUSE_STATUS,null);
        initWidget();
    }

    private void initWidget(){
        ListView mList = (ListView) findViewById(R.id.list_houses);
        mStatus = (ImageView) findViewById(R.id.zhuangtai_txt);
        mBuWei = (ImageView) findViewById(R.id.buwei_txt);
        TextView mNewProblem = (TextView) findViewById(R.id.add_problem_txt);
        mBgTxt = (ImageView) findViewById(R.id.bg_txt);


        CheckItemAdapter adapter = new CheckItemAdapter(this);
        adapter.setData(setData());
        mList.setAdapter(adapter);

        mStatus.setOnClickListener(this);
        mBuWei.setOnClickListener(this);
        mNewProblem.setOnClickListener(this);
        mList.setOnItemClickListener(this);
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
                getPopWindow(mStatus,setStatusList());
                break;
            case R.id.buwei_txt:
                    isOpenBuwei();
                getPopWindow(mBuWei,setBuweiList());
                break;
            case R.id.add_problem_txt:
                switch (type){
                    case CheckConstants.STATUS_ONE:
                        Intent intent = new Intent(this,AddProblemActivity.class);
                        startActivity(intent);
                        break;
                    case CheckConstants.STATUS_TWO:
                        intent = new Intent(this,PrepareAddProblemActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    /**
     * 状态
     */
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

    /**
     * 部位
     */
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,CheckDetialItemActivity.class);
        startActivity(intent);
    }

    private ArrayList<String> setStatusList(){
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("全部");
        statusList.add("待检验");
        statusList.add("待指派");
        statusList.add("待修复");
        statusList.add("待销项");
        return statusList;
    }
    private ArrayList<String> setBuweiList(){
        ArrayList<String> buweiList = new ArrayList<>();
        buweiList.add("全部");
        buweiList.add("主卧");
        buweiList.add("次卧室1");
        buweiList.add("次卧室2");
        buweiList.add("客厅");
        buweiList.add("厨房");
        buweiList.add("卫生间");
       return buweiList;
    }
    /**
     * 状态部位弹框
     * @param view
     * @param listItem
     */
    private void getPopWindow(View view,ArrayList<String> listItem) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_check_layout, null);

        /**
         * 如果pop是null就执行这个方法
         */
        if (mPopProWindow == null) {
            mPopProWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //        实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            mPopProWindow.setBackgroundDrawable(dw);
        }
        ListView listView = (ListView) contentView.findViewById(R.id.pop_list);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.txt_more_item_layout, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPopProWindow.dismiss();
                mBgTxt.setVisibility(View.GONE);
                mPopProWindow = null;
            }
        });

            /**
             * 显示就消失
             */
            if (mPopProWindow.isShowing()) {
                mPopProWindow.dismiss();
                mBgTxt.setVisibility(View.GONE);
                mPopProWindow = null;
            } else {
                mBgTxt.setVisibility(View.VISIBLE);
                mPopProWindow.showAsDropDown(view,0,0);  //设置layout在PopupWindow中显示的位置
            }
        }

}
