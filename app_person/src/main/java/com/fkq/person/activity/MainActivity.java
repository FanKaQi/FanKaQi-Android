package com.fkq.person.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fkq.common.adapter.FragmentAdapter;
import com.fkq.common.custom.AppViewPager;
import com.fkq.common.util.BottomNavigationViewUtil;
import com.fkq.common.util.DrawerLayoutUtil;
import com.fkq.person.R;
import com.fkq.person.fragment.OneFragment;
import com.fkq.person.fragment.ThreeFragment;
import com.fkq.person.fragment.TwoFragment;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {

    private BottomNavigationView navigation;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FragmentAdapter fragmentAdapter;
    private AppViewPager viewPager;

    private DrawerLayout drawerLayout;
    private ImageView iv_menu;
    private TextView tv_account_name;

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        ImmersionBar.with(this).reset().titleBar(findViewById(R.id.ll_view)).init();
    }

    @Override
    protected void setBackEnable() {
        super.setBackEnable();
        setSwipeBackEnable(false);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        tv_account_name = (TextView) findViewById(R.id.tv_account_name);
        viewPager = (AppViewPager) findViewById(R.id.viewPager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewUtil.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        //
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        DrawerLayoutUtil.addDrawerListener(drawerLayout);
        iv_menu.setOnClickListener(this);
        tv_account_name.setOnClickListener(this);
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.tv_account_name:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
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
