package com.fkq.skill.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.fkq.common.activity.ComBaseActivity;
import com.fkq.common.adapter.FragmentAdapter;
import com.fkq.common.custom.AppViewPager;
import com.fkq.common.util.BottomNavigationViewUtil;
import com.fkq.skill.R;
import com.fkq.skill.fragment.OneFragment;
import com.fkq.skill.fragment.ThreeFragment;
import com.fkq.skill.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivityCom extends ComBaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {

    private BottomNavigationView navigation;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FragmentAdapter fragmentAdapter;
    private AppViewPager viewPager;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        navigation = findViewById(R.id.navigation);
        BottomNavigationViewUtil.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        //初始化Fragment
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fragments.add(oneFragment);
        fragments.add(twoFragment);
        fragments.add(threeFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        //设置不可滑动
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0, false);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                viewPager.setCurrentItem(0, false);
                return true;
            case R.id.navigation_message:
                viewPager.setCurrentItem(1, false);
                return true;
            case R.id.navigation_my:
                viewPager.setCurrentItem(2, false);
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navigation.getMenu().getItem(position).setChecked(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
