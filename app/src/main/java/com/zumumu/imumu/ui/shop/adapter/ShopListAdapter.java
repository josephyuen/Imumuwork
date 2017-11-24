package com.zumumu.imumu.ui.shop.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.zumumu.imumu.R;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.base.BaseRecylerAdapter;
import com.zumumu.imumu.ui.shop.model.ShopBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class ShopListAdapter extends BaseRecylerAdapter<ShopBean,ShopViewHolder> {

    public ShopListAdapter(Context mContext, int mLayoutRes, List<ShopBean> mList) {
        super(mContext, mLayoutRes, mList);
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShopViewHolder.get(mContext,mLayoutRes,parent);
    }

    @Override
    protected void convert(ShopViewHolder holder, ShopBean shopBean) {
        holder.setImage(R.id.sdv_icon, ApiConstant.HOME_URL + shopBean.getGoods_play_img());
        holder.setNameText(R.id.tv_shoplist_name,shopBean.getGoods_name());
        holder.setPrice(R.id.tv_shoplist_price,R.id.tv_shoplist_vipprice,"原价:￥" + shopBean.getGoods_priceY(),"现价:￥" + shopBean.getGoods_price());
        holder.setParticular(R.id.tv_shoplist_num,R.id.tv_shoplist_inventory,"销量：" + shopBean.getGoods_sales(),"库存" + shopBean.getGoods_inventory());
    }


}
