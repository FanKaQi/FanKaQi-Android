package com.fkq.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import java.util.List;

public class FragmentStateAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> list;

    public FragmentStateAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public void setData(List<Fragment> fragments) {
        this.list = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    /**
     * 可用于筛选某个fragment不被重新加载
     *
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        //防止应用列表刷新后首页数据刷新造成卡顿
        if (object instanceof Fragment) {
            return POSITION_UNCHANGED;
        } else {
            return POSITION_NONE;
        }
    }

}