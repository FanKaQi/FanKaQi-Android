package com.fkq.common.util;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fkq.common.R;
import com.fkq.common.custom.AppImageView;
import com.fkq.common.custom.DividerDecoration;

import java.util.Random;


/**
 * Created by Administrator on 2017/7/7.
 */

public class ViewUtil {

    /**
     * 多行垂直滑动RecyclerView
     *
     * @param context
     * @param recyclerView
     * @param row
     * @return
     */
    public static void getVRows(Context context, RecyclerView recyclerView, int row) {
        //设置layoutManager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setSpanCount(row);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerDecoration(context, R.color.gray81, 1));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static void getVRowsNoLine(Context context, RecyclerView recyclerView, int row) {
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.VERTICAL));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 多行水平滑动RecyclerView
     *
     * @param context
     * @param recyclerView
     * @param row
     * @return
     */
    public static void getHRows(Context context, RecyclerView recyclerView, int row) {
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(row, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerDecoration(context, R.color.white, 1));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置头像样式
     *
     * @param context
     * @param url
     * @param content
     * @param avatarImageView
     */
    public static void setHeadStyle(Context context, String url, String content, AppImageView avatarImageView) {
        if (TextUtils.isEmpty(url)) {
            if (!TextUtils.isEmpty(content) && content.length() > 2) {
                int length = content.length();
                avatarImageView.setTextAndColorSeed(content.substring(length - 2, length), content.substring(length - 2, length));
            } else {
                avatarImageView.setTextAndColor(content, AppImageView.COLORS[new Random().nextInt(5)]);
            }
        } else {
            setImageUrl(context, url, avatarImageView);
        }
    }

    /**
     * 设置网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */

    public static void setImageUrl(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

}
