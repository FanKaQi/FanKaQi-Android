package com.fkq.skill.util;

import com.fkq.common.util.CommonApplication;
import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/5/23.
 */

public class AppManager extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }

}
