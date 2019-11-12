package com.fkq.common.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;


public class ProgressUtil {

    private static ProgressDialog progress;

    public static void showDialog(Context context, String info) {
        if (progress == null) {
            progress = new ProgressDialog(context);
            // 设置对话框参数
            if (TextUtils.isEmpty(info)) {
                progress.setMessage("正在提交,请稍等...");
            } else {
                progress.setMessage(info);
            }
            progress.setCancelable(false);
            // 设置进度条参数
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true); // 填false表示是明确显示进度的 填true表示不是明确显示进度的
        }
        if (!progress.isShowing()) {
            progress.show();
        }
    }

    public static void cancelDialog() {
        if (null != progress && progress.isShowing()) {
            progress.dismiss();
            progress.cancel();
            progress = null;
        }
    }
}
