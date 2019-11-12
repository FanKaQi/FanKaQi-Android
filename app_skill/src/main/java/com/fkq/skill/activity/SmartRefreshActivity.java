package com.fkq.skill.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fkq.common.activity.BaseActivity;
import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */

public class SmartRefreshActivity extends BaseActivity {
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> data;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_smart_refresh;
    }

    @Override
    protected void initView() {
        refreshLayout = findViewById(R.id.refreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        adapter = new RecyclerAdapter<String>(this, data, R.layout.item_text) {
            @Override
            public void convert(RecyclerHolder holder, final String text) {
                holder.setText(R.id.tv_name, text);
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SmartRefreshActivity activity = SmartRefreshActivity.this;


                    }
                });
            }

        };
        ViewUtil.getVRows(this, recyclerView, 1);
        recyclerView.setAdapter(adapter);
        //
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
            }
        });
    }
}
