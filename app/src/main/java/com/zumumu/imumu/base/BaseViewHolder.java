package com.zumumu.imumu.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/1/3 0003.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> mView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mView = new SparseArray<>();
    }

    public BaseViewHolder(View itemView,Context mContext){
        this(itemView);
        this.mContext = mContext;
    }

    public static BaseViewHolder get(Context mContext, @LayoutRes int layoutRes, ViewGroup parent){
        View view = LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
        return new BaseViewHolder(view,mContext);
    }

    public <T extends View>T getView(int viewId){
        View view = mView.get(viewId);
        if (view == null){
            view = itemView.findViewById(viewId);
            mView.put(viewId,view);
        }

        return (T) view;
    }

    public View getItemView(){
        return itemView;
    }
}
