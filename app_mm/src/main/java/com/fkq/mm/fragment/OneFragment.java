package com.fkq.mm.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.mm.R;
import com.gyf.barlibrary.ImmersionBar;


public class OneFragment extends BaseLazyFragment {

    private TextView tv_title;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_title = view.findViewById(R.id.tv_title);
        ImmersionBar.with(this).titleBar(tv_title).init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {
        getData();
    }

    private void getData() {

    }

}