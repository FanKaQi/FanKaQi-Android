package com.fkq.skill.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.fkq.skill.activity.RecyclerActivity2;
import com.fkq.skill.activity.SmartRefreshActivity;
import com.fkq.skill.activity.StickyActivity;
import com.fkq.skill.arcgis.ArcgisListActivity;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends BaseLazyFragment {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> data;


    @Override
    protected int setLayoutView() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new RecyclerAdapter<String>(context, data, R.layout.item_text) {
            @Override
            public void convert(RecyclerHolder holder, final String text) {
                holder.setText(R.id.tv_name, text);
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = null;
                        if (text.equals("RecyclerView")) {
                            intent = new Intent(context, RecyclerActivity2.class);
                        } else if (text.equals("StickyLayout")) {
                            intent = new Intent(context, StickyActivity.class);
                        } else if (text.equals("SmartRefreshLayout")) {
                            intent = new Intent(context, SmartRefreshActivity.class);
                        } else if (text.equals("Arcgis")) {
                            intent = new Intent(context, ArcgisListActivity.class);
                        } else {
                            return;
                        }
                        startActivity(intent);
                    }
                });
            }

        };
        ViewUtil.getVRows(context, recyclerView, 1);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        data.add("RecyclerView");
        data.add("StickyLayout");
        data.add("SmartRefreshLayout");
        data.add("Arcgis");
    }

}