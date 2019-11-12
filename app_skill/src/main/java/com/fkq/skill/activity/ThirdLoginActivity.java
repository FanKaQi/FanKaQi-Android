package com.fkq.skill.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.fkq.common.activity.BaseActivity;
import com.fkq.common.util.ToastUtil;
import com.fkq.skill.R;

import me.shaohui.shareutil.LoginUtil;
import me.shaohui.shareutil.login.LoginListener;
import me.shaohui.shareutil.login.LoginPlatform;
import me.shaohui.shareutil.login.LoginResult;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ThirdLoginActivity extends BaseActivity {

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
                login(LoginPlatform.QQ);
//                ShareSdkUtil.getInstance().showLogin(this, QQ.NAME);
                break;
            case R.id.iv_wachat_login:
                login(LoginPlatform.WX);
//                ShareSdkUtil.getInstance().showLogin(this, Wechat.NAME);
                break;
            case R.id.iv_xinlang_login:
                login(LoginPlatform.WEIBO);
//                ShareSdkUtil.getInstance().showLogin(this, SinaWeibo.NAME);
                break;
        }
    }

    private void login(int platform) {
        // LoginPlatform.WEIBO  微博登录
        // LoginPlatform.WX     微信登录
        // LoginPlatform.QQ     QQ登录
        LoginListener listener = new LoginListener() {
            @Override
            public void loginSuccess(LoginResult result) {
                //登录成功， 如果你选择了获取用户信息，可以通过
                Intent intent = new Intent(ThirdLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void loginFailure(Exception e) {
                Log.i("TAG", "登录失败");
                ToastUtil.show(ThirdLoginActivity.this, "登录失败");
            }

            @Override
            public void loginCancel() {
                Log.i("TAG", "登录取消");
                ToastUtil.show(ThirdLoginActivity.this, "登录取消");
            }
        };
        LoginUtil.login(this, platform, listener, true);
    }
}
