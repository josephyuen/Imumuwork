package com.zumumu.imumu.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zumumu.imumu.lisener.RecyclerViewItemOnClickLisenter;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public abstract class BaseHeaderFootAdpter<T> extends RecyclerView.Adapter{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOT = 2;

    public View mHaederView;
    private View mFootView;

    int size;
    protected Context mContext;
    protected T mData;
    protected int mLayoutRes;
    private RecyclerViewItemOnClickLisenter mLisenter;

    public void setOnItemClickLisenter(RecyclerViewItemOnClickLisenter mLisenter){
        this.mLisenter = mLisenter;
    }

    public void setmHaederView(View mHaederView) {
        this.mHaederView = mHaederView;
        notifyItemChanged(0);
    }

    public void setmFootView(View mFootView) {
        this.mFootView = mFootView;
        notifyItemChanged(size + 2);
    }

    public View getmHaederView() {
        return mHaederView;
    }

    public View getmFootView() {
        return mFootView;
    }

    public BaseHeaderFootAdpter(Context mContext, @LayoutRes int mLayoutRes, T mData){
        this.mContext = mContext;
        this.mLayoutRes = mLayoutRes;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        if (position == size + 2) return TYPE_FOOT;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHaederView != null && viewType == TYPE_HEADER) return onCreateHeader(parent,mHaederView);
        if (mFootView != null&& viewType == TYPE_FOOT) return onCreateFoot(parent,mFootView);
        return onCreate(parent,viewType);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            headerConvert(holder,mData,position);
        }else if (getItemViewType(position) == size) {
            footConvert(holder,mData,position);
        }else {
            convert(holder, getRealPosition(holder),mData);
        }

        if (mLisenter != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLisenter.onItemClick(v,position);
                }
            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHaederView == null ? position : position - 1;
    }

    public abstract RecyclerView.ViewHolder  onCreate(ViewGroup parent, final int viewType);

    protected abstract RecyclerView.ViewHolder onCreateHeader(ViewGroup parent,View mHaederView);
    protected abstract RecyclerView.ViewHolder onCreateFoot(ViewGroup parent,View mFootView);

    protected abstract void headerConvert(RecyclerView.ViewHolder holder, T t, int position);
    protected abstract void convert(RecyclerView.ViewHolder holder,int postion, T t);
    protected abstract void footConvert(RecyclerView.ViewHolder holder, T t, int position);

    protected abstract int setItemCount();

    @Override
    public int getItemCount() {
        if (mData != null) setItemCount();
        if (mHaederView != null) size += 1;
        if (mFootView != null) size += 1;
        return 2;
    }


}
