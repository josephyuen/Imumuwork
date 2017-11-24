package com.zumumu.imumu.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zumumu.imumu.lisener.RecyclerViewItemOnClickLisenter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public abstract class BaseRecylerAdapter<T,VH extends BaseViewHolder> extends RecyclerView.Adapter<VH>{

    protected Context mContext;
    protected List<T> mList;
    protected int mLayoutRes;
    private RecyclerViewItemOnClickLisenter mLisenter;

    public void setOnItemClickLisenter(RecyclerViewItemOnClickLisenter mLisenter){
        this.mLisenter = mLisenter;
    }

    public BaseRecylerAdapter(Context mContext, @LayoutRes int mLayoutRes, List<T> mList){
        this.mContext = mContext;
        this.mLayoutRes = mLayoutRes;
        this.mList = mList;
    }

    public void notifyDataListChanged(List<T> mList){
        this.mList = mList;
        this.notifyDataSetChanged();
    }

    public void notifyListChanged(List<T> mList){
        this.notifyDataListChanged(mList);
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return (VH)BaseViewHolder.get(mContext,mLayoutRes,parent);
    }



    @Override
    public void onBindViewHolder(VH holder, final int position) {
        convert(holder,mList.get(position));
        if (mLisenter != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLisenter.onItemClick(v,position);
                }
            });
        }
    }

    protected abstract void convert(VH holder, T t);

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }


}
