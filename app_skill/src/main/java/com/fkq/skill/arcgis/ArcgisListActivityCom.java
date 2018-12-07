package com.fkq.skill.arcgis;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fkq.common.activity.ComBaseActivity;
import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */

public class ArcgisListActivityCom extends ComBaseActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> data;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        data.add("ArcgisFragment1");
        data.add("ArcgisFragment2");
        data.add("ArcgisFragment3");
        data.add("ArcgisFragment4");
        data.add("ArcgisFragment5");
        data.add("ArcgisFragment6");
        data.add("ArcgisFragment7");
        adapter = new RecyclerAdapter<String>(this, data, R.layout.item_text) {
            @Override
            public void convert(RecyclerHolder holder, final String text) {
                holder.setText(R.id.tv_name, text);
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ArcgisListActivityCom.this, ArcgisActivityCom.class);
                        intent.putExtra("name", text);
                        startActivity(intent);
                    }
                });
            }

        };
        ViewUtil.getVRows(this, recyclerView, 1);
        recyclerView.setAdapter(adapter);
    }
}
