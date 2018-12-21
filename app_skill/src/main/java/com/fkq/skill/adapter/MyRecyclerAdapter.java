package com.fkq.skill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fkq.common.util.ViewUtil;
import com.fkq.skill.R;
import com.fkq.skill.model.MyData;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.BaseViewHolder> {
    private List<MyData> data;
    private Context context;

    public MyRecyclerAdapter(Context context, List<MyData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.rv_item_title, parent, false);
            OneViewHolder oneViewHolder = new OneViewHolder(view1);
            return oneViewHolder;
        } else {
            View view2 = LayoutInflater.from(context).inflate(R.layout.rv_item_text, parent, false);
            TwoViewHolder twoViewHolder = new TwoViewHolder(view2);
            return twoViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (data.get(position).getViewType() == 0) {
            ((OneViewHolder) holder).name.setText("标题" + position);
        } else {
            ViewUtil.setImageUrl(context, "http://img.mp.itc.cn/q_70,c_zoom,w_640/upload/20170816/c561779915194b3b8d60445311d578ce_th.jpg", ((TwoViewHolder) holder).name);
        }


    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class OneViewHolder extends BaseViewHolder {
        TextView name;

        OneViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
        }
    }

    public class TwoViewHolder extends BaseViewHolder {
        ImageView name;

        TwoViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.iv_img);
        }
    }
}
