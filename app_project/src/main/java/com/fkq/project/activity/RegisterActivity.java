package com.fkq.project.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fkq.common.util.ToastUtil;
import com.fkq.project.R;
import com.fkq.project.constant.Constants;
import com.fkq.project.util.DevicesUtil;
import com.fkq.project.util.TitleUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

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
        OkGo.<String>post(Constants.url_register).params("name", username).params("password", DevicesUtil.toMD5(passwordOne))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject object = new JSONObject(body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
