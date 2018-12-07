package com.fkq.skill.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fkq.common.util.ToastUtil;
import com.fkq.skill.activity.MainActivityCom;
import com.fkq.skill.model.UserInfo;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Created by Administrator on 2018/5/24.
 */

public class ShareSdkUtil {

    private static ShareSdkUtil instance;

    public static ShareSdkUtil getInstance() {
        if (instance == null) {
            synchronized (ShareSdkUtil.class) {
                instance = new ShareSdkUtil();
            }
        }
        return instance;
    }

    /**
     * 分享 文本
     *
     * @param context
     */
    public void showShareText(Context context) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("文本分享");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        oks.addHiddenPlatform(WechatFavorite.NAME);
        oks.addHiddenPlatform(WechatMoments.NAME);
        // 启动分享GUI
        oks.show(context);
    }

    public void showShareImage(final Context context) {
        //所有权限都已经授权
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("图片分享");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        oks.setImagePath("/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1526456646606.jpg");
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(context);
    }

    /**
     * 第三方登录
     *
     * @param context
     * @param platName
     */
    public void showLogin(final Context context, String platName) {
        final Platform plat = ShareSDK.getPlatform(platName);
        //授权回调监听
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
                //输出所有授权信息
                if (Platform.ACTION_USER_INFOR == action) {
                    PlatformDb platDB = platform.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    UserInfo info = new UserInfo();
                    info.setUserId(platDB.getUserId());
                    info.setUserName(platDB.getUserName());
                    info.setUserIcon(platDB.getUserIcon());
                    UserUtil.getInstance().setUser(context, info);
                    Intent intent = new Intent(context, MainActivityCom.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } else {
                    ToastUtil.show(AppManager.getInstance(), "获取信息失败，请重新登陆");
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastUtil.show(AppManager.getInstance(), "登录" + platform.getName() + "出现异常");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ToastUtil.show(AppManager.getInstance(), "取消" + platform.getName() + "授权登录");
            }
        });
        plat.showUser(null);
    }

}
