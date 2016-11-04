package com.house.checkhouse.model.message;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class TaskInfo {
    private String time;
    private ArrayList<TaskItem> listTask;
    private int type;    //0未完成   1 已完成

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

    public ArrayList<TaskItem> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<TaskItem> listTask) {
        this.listTask = listTask;
    }
}
