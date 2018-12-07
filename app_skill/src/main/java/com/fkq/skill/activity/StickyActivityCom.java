package com.fkq.skill.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.fkq.common.activity.ComBaseActivity;
import com.fkq.common.adapter.FragmentAdapter;
import com.fkq.skill.R;
import com.fkq.skill.fragment.StickyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */

public class StickyActivityCom extends ComBaseActivity {
    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_sticky;
    }

    @Override
    protected void initView() {
        mTabLayout =  findViewById(R.id.tabLayout);
        mViewPager =  findViewById(R.id.viewPager);
    }

    @Override
    protected void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("未处理");
        titles.add("已处理");
        titles.add("已完成");
        List<Fragment> fragments = new ArrayList<>();
        StickyFragment stickyFragment1 = new StickyFragment();
        StickyFragment stickyFragment2 = new StickyFragment();
        StickyFragment stickyFragment3 = new StickyFragment();
        fragments.add(stickyFragment1);
        fragments.add(stickyFragment2);
        fragments.add(stickyFragment3);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        fragmentAdapter.setTitles(titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(fragmentAdapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
