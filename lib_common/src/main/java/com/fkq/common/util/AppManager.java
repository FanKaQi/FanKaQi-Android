package com.fkq.common.util;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class AppManager extends Application {

    private List<Activity> activities = new ArrayList<>();
    private static AppManager instance;

    /**
     * 单例模式
     *
     * @return
     */
    public static AppManager getInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager();
            }
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 添加
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 退出
     */
    public void finish() {
        for (Activity act : activities) {
            act.finish();
        }
        System.exit(0);
    }


}
