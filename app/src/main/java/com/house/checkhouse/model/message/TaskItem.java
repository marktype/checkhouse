package com.house.checkhouse.model.message;

/**
 * Created by 欢大哥 on 2016/10/26.
 */
public class TaskItem {

    private String title;
    private String content;
//    private int itemType;  // 0 未完成   1已完成
//
//    public int getItemType() {
//        return itemType;
//    }
//
//    public void setItemType(int itemType) {
//        this.itemType = itemType;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
