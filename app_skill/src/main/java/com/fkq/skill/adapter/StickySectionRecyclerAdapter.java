package com.fkq.skill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.fkq.skill.model.MyData2;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class StickySectionRecyclerAdapter extends RecyclerView.Adapter<StickySectionRecyclerAdapter.BaseViewHolder> {
    private List<MyData2> data;
    private Context context;

    public StickySectionRecyclerAdapter(Context context, List<MyData2> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.rv_item_text, parent, false);
        BaseViewHolder oneViewHolder = new BaseViewHolder(view1);
        return oneViewHolder;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ViewUtil.setImageUrl(context, "http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170816/c561779915194b3b8d60445311d578ce_th.jpg",  holder.name);
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView name;

        BaseViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.iv_img);
        }
    }
}
