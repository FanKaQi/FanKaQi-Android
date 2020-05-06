package com.fkq.skill.fragment;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.fkq.common.adapter.RecyclerAdapter;
import com.fkq.common.custom.DividerDecoration;
import com.fkq.common.fragment.BaseLazyFragment;
import com.fkq.common.model.RecyclerHolder;
import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.fkq.skill.interfaces.OnRecyclerItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.touch.OnItemStateChangedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RecyclerView拖拽移动
 */
public class SwipeListFragment extends BaseLazyFragment {

    private SwipeRecyclerView recyclerView;
    private List<String> data;
    private RecyclerAdapter adapter;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("哈哈哈哈哈" + i);
        }
        adapter = new RecyclerAdapter<String>(context, data, R.layout.item_text) {
            @Override
            public void convert(RecyclerHolder holder, String str) {
                holder.setText(R.id.tv_name, str);
            }

        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerDecoration(context, com.fkq.common.R.color.gray81, 1));
        recyclerView.setLongPressDragEnabled(true); // 拖拽排序，默认关闭。
        recyclerView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。
        recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemStateChangedListener(new OnItemStateChangedListener() {
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder holder, int actionState) {
                if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
                    // 状态：正在拖拽。
                    Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);
                    holder.itemView.setBackgroundResource(R.color.translucent);
                } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                    // 状态：滑动删除。
                } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                    // 状态：手指松开。
                    holder.itemView.setBackground(null);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        getData();
    }

    private void getData() {

    }

    OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            int fromPosition = srcHolder.getAdapterPosition();
            int toPosition = targetHolder.getAdapterPosition();
            Collections.swap(data, fromPosition, toPosition);
            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            int position = srcHolder.getAdapterPosition();
            data.remove(position);
            adapter.notifyItemRemoved(position);
        }
    };
}
