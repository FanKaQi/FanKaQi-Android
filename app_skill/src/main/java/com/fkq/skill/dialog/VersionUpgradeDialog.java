package com.fkq.skill.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fkq.skill.R;
import com.fkq.skill.constant.Constant;
import com.fkq.skill.model.VersionInfo;
import com.fkq.skill.util.FileUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;


public class VersionUpgradeDialog implements View.OnClickListener {
    private Context mContext;
    //
    private Dialog dialog;
    private TextView tv_update;
    private TextView tv_cancel;
    private TextView tv_content;
    private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private VersionInfo serverVersion;
    private String fileName = "信通业务助手.apk";

    public VersionUpgradeDialog(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        dialog = BaseDialog.getIntance(mContext).getDialog(R.layout.dialog_version_upgrade,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        tv_update = dialog.findViewById(R.id.tv_update);
        tv_cancel = dialog.findViewById(R.id.tv_cancel);
        tv_content = dialog.findViewById(R.id.tv_content);
        tv_update.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        tv_content.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void show(VersionInfo serverVersion) {
        this.serverVersion = serverVersion;
        tv_content.setText("发现新版本：V" + serverVersion.getVersionname()
                + "\n更新以下内容\n" + serverVersion.getDescription());
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
            case R.id.tv_update:
                downLoadApk();
                dialog.dismiss();
                break;
        }
    }

    /**
     * 从服务器中下载APK
     */
    private void downLoadApk() {
        final File file = new File(filePath + "/" + fileName);
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载新版本");
        progressDialog.setCancelable(false);
        OkGo.<File>get(Constant.DOWNLOAD_VERSION)
                .params("versionName", serverVersion.getVersionname()).execute(new FileCallback(filePath, fileName) {

            @Override
            public void onStart(Request<File, ? extends Request> request) {
                super.onStart(request);
                progressDialog.show();
            }

            @Override
            public void onSuccess(Response<File> response) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(mContext, "com.hylink.police.fileprovider", file);
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                mContext.startActivity(intent);
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                String totalSize = FileUtil.getIntance(mContext).formatFileSize(progress.totalSize);
                String currentSize = FileUtil.getIntance(mContext).formatFileSize(progress.currentSize);
                progressDialog.setProgressNumberFormat(currentSize + "/" + totalSize);
                int percent = (int) (100 * progress.currentSize / progress.totalSize);
                progressDialog.setProgress(percent);
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                progressDialog.dismiss();
            }
        });
    }

}
