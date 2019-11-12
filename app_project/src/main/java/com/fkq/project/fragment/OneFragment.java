package com.fkq.project.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.project.R;


public class OneFragment extends BaseLazyFragment {

    private TextView tv_title;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

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