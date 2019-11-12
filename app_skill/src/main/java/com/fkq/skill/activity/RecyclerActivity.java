package com.fkq.skill.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fkq.common.activity.BaseActivity;
import com.fkq.skill.R;
import com.fkq.skill.adapter.MyRecyclerAdapter;
import com.fkq.skill.model.MyData;

import java.util.ArrayList;
import java.util.List;

/**
 * 不带粘性多布局RecycleView
 */

public class RecyclerActivity extends BaseActivity {
    private RecyclerView recycleView;
    private List<MyData> list;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        recycleView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        init();
        GridLayoutManager manager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this,list);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(list.get(position).getViewType() == 0){
                    return 3;
                }else {
                    return 1;
                }
            }
        });
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i == 0 || i == 6 || i == 10 || i == 16 || i == 25 || i == 32) {
                list.add(new MyData(0,i+""));
            } else {
                list.add(new MyData(1,i+""));
            }
        }
    }

}
