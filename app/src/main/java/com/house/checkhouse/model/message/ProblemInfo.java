package com.house.checkhouse.model.message;

import java.util.ArrayList;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class ProblemInfo {
    private String time;
    private ArrayList<ProblemItem> listTask;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<ProblemItem> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<ProblemItem> listTask) {
        this.listTask = listTask;
    }
}
