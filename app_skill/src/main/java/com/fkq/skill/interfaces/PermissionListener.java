package com.fkq.skill.interfaces;

import java.util.List;

public interface PermissionListener {

    /**
     * 所有权限已经授权
     */
    void onGranted();

    /**
     * 被拒绝的权限
     *
     * @param deniedPermission
     */
    void onDenied(List<String> deniedPermission);

    /**
     * 勾选的不在提示的权限
     *
     * @param deniedPermission
     */
    void onShouldShowRationale(List<String> deniedPermission);
}
