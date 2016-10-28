package com.house.checkhouse.model.message;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/27.
 */
public class CheckHouseInfo {
    private String num;
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
}
