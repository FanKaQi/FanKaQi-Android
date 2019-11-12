package com.fkq.skill.manager;

import android.content.Context;

import com.fkq.skill.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;

/**
 * Created by Administrator on 2018/5/23.
 */

public class AppManager extends com.fkq.common.util.AppManager {

    /**
     * 代码段可以防止内存泄露
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.white, R.color.colorPrimary);
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                ClassicsFooter classicsFooter = new ClassicsFooter(context);
                classicsFooter.setTextSizeTitle(15);
                classicsFooter.setDrawableProgressSize(18);
                return classicsFooter;
            }
        });
    }


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
