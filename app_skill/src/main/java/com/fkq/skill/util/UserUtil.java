package com.fkq.skill.util;

import android.content.Context;

import com.fkq.skill.model.UserInfo;

/**
 * Created by Administrator on 2018/5/24.
 */

public class UserUtil {

    private static UserUtil instance;

    // 单例模式
    public static UserUtil getInstance() {
        if (null == instance) {
            instance = new UserUtil();
        }
        return instance;
    }


    public UserInfo getUser(Context context) {
        UserInfo user = (UserInfo) ACache.get(context).getAsObject("user");
        if (user == null) {
            user = new UserInfo();
        }
        return user;
    }

    public void setUser(Context context, UserInfo user) {
        ACache.get(context).put("user", user);
    }

    public void outLogin(Context context) {

    }
}
