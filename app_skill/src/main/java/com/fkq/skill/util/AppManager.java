package com.fkq.skill.util;

import com.fkq.common.util.ComApplication;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;

/**
 * Created by Administrator on 2018/5/23.
 */

public class AppManager extends ComApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareConfig config = ShareConfig.instance()
                .qqId("1106930326")
                .wxId("wx4868b35061f87885")
                .weiboId("711574484")
                // 下面两个，如果不需要登录功能，可不填写
//                .weiboRedirectUrl(REDIRECT_URL)
                .wxSecret("64020361b8ec4c99936c0e3999a9f249");
        ShareManager.init(config);
    }

}
