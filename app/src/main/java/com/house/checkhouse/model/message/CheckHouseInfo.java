package com.house.checkhouse.model.message;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/27.
 */
public class CheckHouseInfo {
    private String num;
    private String type;  //选择使用title（1、楼盘，2、预验房，3、交房陪验）
    private ArrayList<HousesInfo> list;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public ArrayList<HousesInfo> getList() {
        return list;
    }

    public void setList(ArrayList<HousesInfo> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
