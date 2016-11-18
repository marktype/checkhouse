package com.house.checkhouse.model.message;

/**
 * Created by 欢大哥 on 2016/11/15.
 */
public class SignHistoryInfo {
    private String data;
    private String week;
    private String status;
    private String startTime;
    private String endTime;
    private String startSign;
    private String endSign;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartSign() {
        return startSign;
    }

    public void setStartSign(String startSign) {
        this.startSign = startSign;
    }

    public String getEndSign() {
        return endSign;
    }

    public void setEndSign(String endSign) {
        this.endSign = endSign;
    }
}
