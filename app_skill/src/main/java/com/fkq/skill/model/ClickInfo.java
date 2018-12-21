package com.fkq.skill.model;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 */
public class ClickInfo {

    public int mBottom;
    public int mGroupId = View.NO_ID;

    /**
     * 详细信息
     * 对应的子view坐标信息
     */
    public List<DetailInfo> mDetailInfoList;

    public ClickInfo(int bottom) {
        this.mBottom = bottom;
    }

    public ClickInfo(int bottom, List<DetailInfo> detailInfoList) {
        mBottom = bottom;
        mDetailInfoList = detailInfoList;
    }

    public static class DetailInfo {

        public int id;
        public int left;
        public int right;
        public int top;
        public int bottom;

        public DetailInfo(int id, int left, int right, int top, int bottom) {
            this.id = id;
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }
    }

}