package com.fkq.skill.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fkq.common.activity.ComBaseActivity;
import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */

public class SmartRefreshActivity extends ComBaseActivity {
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
                        if (text.equals("1")) {
                            refreshLayout.setRefreshHeader(new MaterialHeader(activity).setShowBezierWave(true));
                            refreshLayout.setRefreshFooter(new BallPulseFooter(activity));
                        } else if (text.equals("2")) {
                            refreshLayout.setRefreshHeader(new PhoenixHeader(activity));
                            refreshLayout.setRefreshFooter(new ClassicsFooter(activity));
                        } else if (text.equals("3")) {
                            refreshLayout.setRefreshHeader(new StoreHouseHeader(activity));
                            refreshLayout.setRefreshFooter(new FalsifyFooter(activity));
                        }else if (text.equals("4")) {
                            refreshLayout.setRefreshHeader(new BezierCircleHeader(activity));
                            refreshLayout.setRefreshFooter(new BallPulseFooter(activity));
                        }else if (text.equals("5")) {
                            refreshLayout.setRefreshHeader(new DeliveryHeader(activity));
                            refreshLayout.setRefreshFooter(new BallPulseFooter(activity));
                        }

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
