package com.fkq.skill.util;

import com.fkq.common.util.ComApplication;
import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/5/23.
 */

public class AppManager extends ComApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }

}
