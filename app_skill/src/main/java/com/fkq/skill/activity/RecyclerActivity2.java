package com.fkq.skill.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fkq.common.activity.ComBaseActivity;
import com.fkq.skill.R;
import com.fkq.skill.adapter.StickySectionRecyclerAdapter;
import com.fkq.skill.custom.StickyDecoration;
import com.fkq.skill.interfaces.GroupListener;
import com.fkq.skill.model.MyData2;

import java.util.ArrayList;
import java.util.List;

/**
 * 带粘性多布局RecycleView
 */

public class RecyclerActivity2 extends ComBaseActivity {
    private RecyclerView recycleView;
    private List<MyData2> list;


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
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        StickySectionRecyclerAdapter adapter = new StickySectionRecyclerAdapter(this, list);
        recycleView.setAdapter(adapter);
        //
        GroupListener groupListener = new GroupListener() {
            @Override
            public String getGroupName(int position) {
                //获取分组名
                return list.get(position).getTitle();
            }
        };
        StickyDecoration decoration = StickyDecoration.Builder
                .init(groupListener)
                //重置span（使用GridLayoutManager时必须调用）
                .resetSpan(recycleView, manager)
                .setGroupTextColor(getResources().getColor(R.color.colorFont))
                .setGroupBackground(getResources().getColor(R.color.gray))
                .build();
        recycleView.setLayoutManager(manager);
        //需要在setLayoutManager()之后调用addItemDecoration()
        recycleView.addItemDecoration(decoration);
    }

    void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i < 5) {
                list.add(new MyData2("第一组2018--12--21", i + ""));
            } else if (i >= 5 && i < 12) {
                list.add(new MyData2("第二组2018--12--21", i + ""));
            } else {
                list.add(new MyData2("第三组2018--12--21", i + ""));

            }
        }
    }

}
