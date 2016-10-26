package com.house.checkhouse.model.message;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class TaskInfo {
    private String time;
    private ArrayList<TaskItem> listTask;

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
