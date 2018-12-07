package com.fkq.person.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fkq.common.util.ToastUtil;
import com.fkq.person.R;
import com.fkq.person.constant.Constants;
import com.fkq.person.custom.JsonCallBack;
import com.fkq.person.model.JsonData;
import com.fkq.person.model.User;
import com.fkq.person.util.TitleUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/5/23.
 */

public class RegisterActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_password_one, et_password_two;
    private Button bt_register;



    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        TitleUtil.setTitle(this, "注册");
        et_username = (EditText) findViewById(R.id.et_username);
        et_password_one = (EditText) findViewById(R.id.et_password_one);
        et_password_two = (EditText) findViewById(R.id.et_password_two);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_register:
                register();
                break;
        }
    }

    private void register() {
        String username = et_username.getText().toString().trim();
        String passwordOne = et_password_one.getText().toString().trim();
        String passwordTwo = et_password_two.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show(RegisterActivity.this, "请输入账户");
            return;
        } else if (TextUtils.isEmpty(passwordOne)) {
            ToastUtil.show(RegisterActivity.this, "请输入密码");
            return;
        } else if (TextUtils.isEmpty(passwordTwo)) {
            ToastUtil.show(RegisterActivity.this, "请确认密码");
            return;
        } else if (!passwordTwo.equals(passwordOne)) {
            ToastUtil.show(RegisterActivity.this, "两次输入密码不一致");
            return;
        }
        OkGo.<JsonData>get(Constants.url_login).params("name", username).params("password", passwordOne)
                .execute(new JsonCallBack<JsonData>(JsonData.class) {
                    @Override
                    public void onSuccess(Response<JsonData> response) {
                        JsonData jsonData = response.body();
                        if (jsonData.getCode() == 0) {
                            User user = (User) jsonData.getData();
                        } else {
                            ToastUtil.show(RegisterActivity.this, jsonData.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<JsonData> response) {
                        super.onError(response);
                    }
                });
    }
}
