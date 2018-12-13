package com.fkq.skill.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fkq.common.custom.AppImageView;
import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.skill.R;
import com.fkq.skill.model.UserInfo;
import com.fkq.skill.util.UserUtil;

import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.SharePlatform;


public class ThreeFragment extends BaseLazyFragment {

    private AppImageView iv_head;
    private TextView tv_name;
    private ImageView iv_share;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        iv_head = view.findViewById(R.id.iv_head);
        tv_name = view.findViewById(R.id.tv_name);
        iv_share = view.findViewById(R.id.iv_share);
        iv_share.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        UserInfo user = UserUtil.getInstance().getUser(context);
        Glide.with(context).load(user.getUserIcon()).centerCrop().into(iv_head);
        tv_name.setText(user.getUserName());
    }


    @Override
    protected void loadData() {
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_share:
                ShareUtil.shareText(context, SharePlatform.WX, "分享文字", null);
                break;
        }
    }
}
