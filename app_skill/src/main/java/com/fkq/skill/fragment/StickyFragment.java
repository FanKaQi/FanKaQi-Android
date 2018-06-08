package com.fkq.skill.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */

public class StickyFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> data;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_sticky;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("name" + i);
        }
        adapter = new RecyclerAdapter<String>(context, data, R.layout.item_text) {
            @Override
            public void convert(RecyclerHolder holder, final String text) {
                holder.setText(R.id.tv_name, text);
            }

        };
        ViewUtil.getVRows(context, recyclerView, 1);
        recyclerView.setAdapter(adapter);
    }
}
