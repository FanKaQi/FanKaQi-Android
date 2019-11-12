package com.fkq.project.util;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.fkq.project.R;


/**
 * Created by Administrator on 2018/5/3.
 */

public class TitleUtil {

    /**
     * @param activity
     * @param title    标题
     */
    public static void setTitle(final Activity activity, String title) {
        ImageView iv_back = (ImageView) activity.findViewById(R.id.iv_back);
        TextView tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_title.setText(title);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    /**
     * @param activity
     * @param title    标题
     */
    public static void setTitleCustomBack(final Activity activity, String title, View.OnClickListener listener) {
        ImageView iv_back = (ImageView) activity.findViewById(R.id.iv_back);
        TextView tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_title.setText(title);
        iv_back.setOnClickListener(listener);
    }

    /**
     * @param activity
     * @param title    标题
     */
    public static void setTitleFragment(final DialogFragment activity, View mRootView, String title) {
        ImageView iv_back = (ImageView) mRootView.findViewById(R.id.iv_back);
        TextView tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismiss();
            }
        });
    }
    /**
     * @param activity
     * @param title    标题
     * @param toolText 功能文字内容
     * @param listener 功能文字点击事件
     */
    public static void setTitleTextTool(final Activity activity, String title, String toolText, View.OnClickListener listener) {
        ImageView iv_back = (ImageView) activity.findViewById(R.id.iv_back);
        TextView tv_title = (TextView) activity.findViewById(R.id.tv_title);
        TextView tv_tool = (TextView) activity.findViewById(R.id.tv_tool);
        tv_title.setText(title);
        tv_tool.setText(toolText);
        tv_tool.setVisibility(View.VISIBLE);
        tv_tool.setOnClickListener(listener);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    /**
     * @param activity
     * @param title        标题
     * @param toolResource 图片资源id
     * @param listener     图片资源点击事件
     */
    public static void setTitleImageTool(final Activity activity, String title, int toolResource, View.OnClickListener listener) {
        ImageView iv_back = (ImageView) activity.findViewById(R.id.iv_back);
        ImageView iv_tool = (ImageView) activity.findViewById(R.id.iv_tool);
        TextView tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_title.setText(title);
        iv_tool.setVisibility(View.VISIBLE);
        iv_tool.setImageResource(toolResource);
        iv_tool.setOnClickListener(listener);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }
}
