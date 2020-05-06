package com.fkq.common.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fkq.common.receive.NetWorkChangReceiver;
import com.fkq.common.util.AppManager;

/**
 * 基准Activity 主干
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,
        NetWorkChangReceiver.OnNetWorkChangeListener {

    private NetWorkChangReceiver netWorkChangReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        //注册网络状态监听广播
        netWorkChangReceiver = new NetWorkChangReceiver();
        netWorkChangReceiver.setNetWorkChangeListener(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, intentFilter);
        //
        AppManager.getInstance().addActivity(this);
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
        AppManager.getInstance().removeActivity(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onNetChange(boolean isConnectNet) {

    }
}

