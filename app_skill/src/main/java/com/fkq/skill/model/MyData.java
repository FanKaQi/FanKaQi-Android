package com.fkq.skill.model;

/**
 * Created by Administrator on 2018/12/20.
 */

public class MyData {

    /**
     * 0----标题  否则----内容
     */
    private int viewType;
    private String data;

    public MyData(int viewType, String data) {
        this.viewType = viewType;
        this.data = data;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }
}
