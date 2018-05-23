package com.fkq.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.fkq.common.model.RecyclerHolder;
import java.util.List;

/**
 * 通用型RecyclerView.Adapter
 * Created by Administrator on 2017/6/19.
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public RecyclerAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        RecyclerHolder reViewHolder = RecyclerHolder.get(mContext, parent, mLayoutId);
        return reViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(RecyclerHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }
}
