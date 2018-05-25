package com.fkq.skill.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/25.
 */

public class PermissionUtil {

    private static int REQUEST_PERMISSIONS = 1;

    /**
     * 请求多个权限
     * @param activity
     * @param PERMISSIONS_REQUEST
     */
    public static void requesPermissions(Activity activity, String[] PERMISSIONS_REQUEST) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        ArrayList<String> neededPems = new ArrayList<>();
        for (int i = 0; i < PERMISSIONS_REQUEST.length; i++) {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, PERMISSIONS_REQUEST[i]))
                neededPems.add(PERMISSIONS_REQUEST[i]);
        }
        int size = neededPems.size();
        if (size > 0) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_REQUEST, REQUEST_PERMISSIONS);
            ActivityCompat.requestPermissions(activity, neededPems.toArray(new String[size]), REQUEST_PERMISSIONS);
        }
    }
}
