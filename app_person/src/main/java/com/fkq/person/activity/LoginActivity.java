package com.fkq.person.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fkq.common.util.ToastUtil;
import com.fkq.common.util.ViewUtil;
import com.fkq.person.R;
import com.fkq.person.constant.Constants;
import com.fkq.person.custom.JsonCallBack;
import com.fkq.person.model.JsonData;
import com.fkq.person.model.User;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/5/23.
 */

public class LoginActivity extends BaseActivity {
    private ImageView iv_bg;
    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private TextView tv_register;

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        ImmersionBar.with(this).reset().transparentStatusBar().init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        tv_register = (TextView) findViewById(R.id.tv_register);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ViewUtil.setImageUrl(this, "http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170816/c561779915194b3b8d60445311d578ce_th.jpg", iv_bg);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        OkGo.<JsonData>get(Constants.url_login).params("name", username).params("password", password)
                .execute(new JsonCallBack<JsonData>(JsonData.class) {
                    @Override
                    public void onSuccess(Response<JsonData> response) {
                        JsonData jsonData = response.body();
                        if (jsonData.getCode() == 0) {
                            User user = (User) jsonData.getData();
                        } else {
                            ToastUtil.show(LoginActivity.this, jsonData.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<JsonData> response) {
                        super.onError(response);
                    }
                });
    }
}
