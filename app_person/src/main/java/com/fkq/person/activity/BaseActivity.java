package com.fkq.person.activity;

import android.os.Bundle;
import android.view.View;

import com.fkq.common.R;
import com.fkq.common.util.ComApplication;
import com.gyf.barlibrary.ImmersionBar;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 基准Activity 主干
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ComApplication.getInstance().addActivity(this);
        setStatusBar();
        initView();
        setBackEnable();
        initData();
    }

    /**
     * 初始化布局Id
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化data
     */
    protected abstract void initData();

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
        //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
        ImmersionBar.with(this).fitsSystemWindows(true, R.color.colorTheme).init();
    }

    /**
     * 设置是否可以返回侧滑
     */
    protected void setBackEnable() {
        setSwipeBackEnable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        ComApplication.getInstance().removeActivity(this);
    }

    @Override
    public void onClick(View v) {

    }

}

