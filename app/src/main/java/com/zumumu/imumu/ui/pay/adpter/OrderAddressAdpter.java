package com.zumumu.imumu.ui.pay.adpter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.zumumu.imumu.R;
import com.zumumu.imumu.base.BaseRecylerAdapter;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class OrderAddressAdpter extends BaseRecylerAdapter<MyAddressBean.DataBean, AddressViewHodler> {

    public OrderAddressAdpter(Context mContext, @LayoutRes int mLayoutRes, List mList) {
        super(mContext, mLayoutRes, mList);
    }

    @Override
    public AddressViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return AddressViewHodler.get(mContext,mLayoutRes,parent);
    }

    @Override
    protected void convert(AddressViewHodler holder, MyAddressBean.DataBean dataBean) {
        String location = "详细地址：" + dataBean.getAddress_province() + dataBean.getAddress_city() + dataBean.getAddress_area() + dataBean.getAddress_detailed();
        holder.setLocationText(R.id.tv_orderaddress_name,"收货人：" + dataBean.getAddress_name(),
                R.id.tv_orderaddress_phone,"收货人电话" + dataBean.getAddress_mobile(),
                R.id.tv_orderaddress_location,location);
    }

}
