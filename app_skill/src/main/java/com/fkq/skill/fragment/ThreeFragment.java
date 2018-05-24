package com.fkq.skill.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fkq.common.custom.AppImageView;
import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.skill.R;
import com.fkq.skill.model.UserInfo;
import com.fkq.skill.util.UserUtil;


public class ThreeFragment extends BaseLazyFragment {

    private AppImageView iv_head;
    private TextView tv_name;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        iv_head = view.findViewById(R.id.iv_head);
        tv_name = view.findViewById(R.id.tv_name);
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


}
