package com.fkq.skill.activity;

import android.view.View;
import android.widget.ImageView;

import com.fkq.common.activity.ComBaseActivity;
import com.fkq.skill.R;
import com.fkq.skill.util.ShareSdkUtil;

import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by Administrator on 2018/5/23.
 */

public class LoginActivity extends ComBaseActivity {

    private ImageView iv_shared;
    private ImageView iv_qq_login;
    private ImageView iv_wachat_login;
    private ImageView iv_xinlang_login;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        iv_shared = findViewById(R.id.iv_shared);
        iv_qq_login = findViewById(R.id.iv_qq_login);
        iv_wachat_login = findViewById(R.id.iv_wachat_login);
        iv_xinlang_login = findViewById(R.id.iv_xinlang_login);
        iv_shared.setOnClickListener(this);
        iv_qq_login.setOnClickListener(this);
        iv_wachat_login.setOnClickListener(this);
        iv_xinlang_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_qq_login:
                ShareSdkUtil.getInstance().showLogin(this, QQ.NAME);
                break;
            case R.id.iv_wachat_login:
                ShareSdkUtil.getInstance().showLogin(this, Wechat.NAME);
                break;
            case R.id.iv_xinlang_login:
                ShareSdkUtil.getInstance().showLogin(this, SinaWeibo.NAME);
                break;
        }
    }
}
