package com.fkq.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fkq.common.util.ComApplication;

/**
 * 基准Activity 主干
 */
public abstract class ComBaseActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComApplication.getInstance().addActivity(this);
        setContentView(setLayoutId());
        initView();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ComApplication.getInstance().removeActivity(this);
    }

    @Override
    public void onClick(View v) {

    }

}

