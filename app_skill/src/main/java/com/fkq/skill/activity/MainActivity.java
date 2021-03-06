package com.fkq.skill.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;

import com.fkq.common.activity.BaseActivity;
import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.fkq.skill.util.DataUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> data;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        data = DataUtil.getSkillString();
        adapter = new RecyclerAdapter<String>(this, data, R.layout.item_text) {
            @Override
            public void convert(RecyclerHolder holder, final String text) {
                holder.setText(R.id.tv_name, text);
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, SkillActivity.class);
                        intent.putExtra("skill", text);
                        startActivity(intent);
                    }
                });
            }

        };
        ViewUtil.getVRows(this, recyclerView, 2);
        recyclerView.setAdapter(adapter);
    }

}
