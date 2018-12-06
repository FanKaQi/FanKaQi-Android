package com.fkq.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fkq.common.R;
import com.fkq.common.util.ComApplication;
import com.gyf.barlibrary.ImmersionBar;

/**
 * 基准Activity 主干
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ComApplication.getInstance().addActivity(this);
        initView();
        initData();
        setStatusBar();
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


    protected void setStatusBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorTheme)
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .init();
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

