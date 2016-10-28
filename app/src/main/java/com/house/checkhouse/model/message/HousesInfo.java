package com.house.checkhouse.model.message;

/**
 * Created by 欢大哥 on 2016/10/27.
 */
public class HousesInfo {

    private String name;
    private String id;
    private int type;   //检验状态  0 未检测   1待指派  2 待修复  3 待销项

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
