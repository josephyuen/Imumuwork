package com.zumumu.imumu.ui.pay.adpter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zumumu.imumu.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class AddressViewHodler extends BaseViewHolder {

    public AddressViewHodler(View itemView) {
        super(itemView);
    }

    public AddressViewHodler(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    public static AddressViewHodler get(Context mContext, @LayoutRes int layoutRes, ViewGroup parent){
        View view = LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
        return new AddressViewHodler(view,mContext);
    }

    public AddressViewHodler setLocationText(@IdRes int nameViewId, String name,
                                                                @IdRes int phoneViewId, String phone,
                                                                @IdRes int locationViewId, String location) {
        TextView nameTv = getView(nameViewId);
        TextView phoneTv = getView(phoneViewId);
        TextView locationTv = getView(locationViewId);
        nameTv.setText(name);
        phoneTv.setText(phone);
        locationTv.setText(location);
        return this;
    }
}
