package com.fkq.skill.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.fkq.common.util.JsonUtil;
import com.fkq.skill.constant.Constant;
import com.fkq.skill.dialog.VersionUpgradeDialog;
import com.fkq.skill.model.VersionInfo;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class VersionManager {

    private Context mContext;

    public VersionManager(Context context) {
        mContext = context;
    }

    /**
     * 获取本地软件版本信息
     */
    public VersionInfo getVersionFromLocal() {
        VersionInfo versionInfo = new VersionInfo();
        try {
            PackageInfo packageInfo = mContext.getApplicationContext().getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            versionInfo.setVersioncode(packageInfo.versionCode);
            versionInfo.setVersionname(packageInfo.versionName);
            Log.d("TAG", "本软件的版本号" + versionInfo.getVersioncode() + "/" + versionInfo.getVersionname());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionInfo;
    }


    /**
     * 检测是否需要更新软件
     */
    public void checkVersion() {
        OkGo.<String>get(Constant.GET_VERSION).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jo = new JSONObject(response.body());
                    if (jo.getInt(Constant.JSON_RESULT) == Constant.JSON_SUCCESS) {
                        String data = jo.getString(Constant.JSON_DATA);
                        VersionInfo serverVersion = JsonUtil.parserTFromJson(data, VersionInfo.class);
                        VersionInfo localVersion = getVersionFromLocal();
                        if (serverVersion != null) {
                            int localCode = localVersion.getVersioncode();
                            int serverCode = serverVersion.getVersioncode();
                            if (localCode == serverCode){
                                EventBus.getDefault().post("已是最新版本");
                            }
                            if (serverCode > localCode) {
                                VersionUpgradeDialog dialog = new VersionUpgradeDialog(mContext);
                                dialog.show(serverVersion);
                            }
                        }else {
                            EventBus.getDefault().post("获取版本失败");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.e("hyy", "onError: ",response.getException() );
            }
        });
    }


}
