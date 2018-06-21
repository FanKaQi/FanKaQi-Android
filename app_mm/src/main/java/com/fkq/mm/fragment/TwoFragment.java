package com.fkq.mm.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.mm.R;
import com.gyf.barlibrary.ImmersionBar;


public class TwoFragment extends BaseLazyFragment {

    private ImageView iv_title;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        iv_title = view.findViewById(R.id.iv_title);
        ImmersionBar.with(this).titleBar(iv_title).init();
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
