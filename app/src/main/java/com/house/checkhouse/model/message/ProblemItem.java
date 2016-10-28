package com.house.checkhouse.model.message;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class ProblemItem extends TaskItem{
    private int type;
    private String time;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
