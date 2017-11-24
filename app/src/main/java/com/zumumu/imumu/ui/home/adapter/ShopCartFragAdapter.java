package com.zumumu.imumu.ui.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.home.fragment.ShopCartFragment;
import com.zumumu.imumu.ui.shop.activity.ProductDetailActivity;
import com.zumumu.imumu.ui.shop.model.ShopCartBean;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/13.
 */

public class ShopCartFragAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private Activity mActivity;
    private ShopCartBean bean;
    private Fragment mFragment;
    public ShopCartFragAdapter(Context context, Activity activity, ShopCartBean bean, Fragment fragment) {
        mContext = context;
        mActivity = activity;
        this.bean = bean;
        mFragment = fragment;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shopcart, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder holder1 = (ViewHolder) holder;

        holder1.tvShopcartPreprice.setText("¥"+bean.getData().get(position).getGoods_priceY());
        holder1.tvShopcartPreprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
        holder1.tvShopcartCurprice.setText("现价:¥"+bean.getData().get(position).getGoods_price());
        holder1.tvShopcartTitle.setText(bean.getData().get(position).getGoods_name());
        holder1.sdvShopcartIcon.setImageURI(Uri.parse(ApiConstant.HOME_URL + bean.getData().get(position).getGoods_img()));
        holder1.tvShopcartNum.setText(bean.getData().get(position).getGoods_quantity() + "");
        holder1.ivShopcartArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", bean.getData().get(position).getGoods_id() + "");
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });

        holder1.btnShopcartAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.getData().get(position).setGoods_quantity(bean.getData().get(position).getGoods_quantity()+1);
                holder1.tvShopcartNum.setText(bean.getData().get(position).getGoods_quantity()+"");
                ShopCartFragment shopCartFragment = (ShopCartFragment) mFragment;
                if(bean.getData().get(position).isItemChecked()){
                    BigDecimal a1 = new BigDecimal(Double.toString(bean.getTotlePrice()));
                    BigDecimal b1 = new BigDecimal(Double.toString(bean.getData().get(position).getGoods_price()));
                    double y = a1.add(b1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    bean.setTotlePrice(y);
                    shopCartFragment.changeAllPrice();
                }
            }
        });

        holder1.btnShopcartCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean.getData().get(position).getGoods_quantity()-1==0){
                    ToastUitl.show("亲,商品数量不能为0!",Toast.LENGTH_SHORT);
                    return;
                }
                bean.getData().get(position).setGoods_quantity(bean.getData().get(position).getGoods_quantity()-1);
                holder1.tvShopcartNum.setText(bean.getData().get(position).getGoods_quantity()+"");
                ShopCartFragment shopCartFragment = (ShopCartFragment) mFragment;
                if(bean.getData().get(position).isItemChecked()){
                    BigDecimal a1 = new BigDecimal(Double.toString(bean.getTotlePrice()));
                    BigDecimal b1 = new BigDecimal(Double.toString(bean.getData().get(position).getGoods_price()));
                    double y = a1.subtract(b1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    bean.setTotlePrice(y);
                    shopCartFragment.changeAllPrice();
                }
            }
        });

        holder1.cbShopcartSelect.setChecked(bean.getData().get(position).isItemChecked());
        holder1.cbShopcartSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.getData().get(position).setItemChecked(!(bean.getData().get(position).isItemChecked()));
                ShopCartFragment shopCartFragment = (ShopCartFragment) mFragment;
                BigDecimal a1 = new BigDecimal(Double.toString(bean.getTotlePrice()));
                BigDecimal b1 = new BigDecimal(Double.toString(bean.getData().get(position).getGoods_price()));
                BigDecimal c1 = new BigDecimal(Double.toString(bean.getData().get(position).getGoods_quantity()));
                if(bean.getData().get(position).isItemChecked()){
                    double x = b1.multiply(c1).setScale(2,BigDecimal.ROUND_HALF_UP).add(a1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    bean.setTotlePrice(x);
                }else{
                    double x = a1.subtract(b1.multiply(c1).setScale(2,BigDecimal.ROUND_HALF_UP)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    bean.setTotlePrice(x);
                }
                shopCartFragment.changeAllPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cb_shopcart_select)
        CheckBox cbShopcartSelect;
        @Bind(R.id.sdv_shopcart_icon)
        SimpleDraweeView sdvShopcartIcon;
        @Bind(R.id.tv_shopcart_title)
        TextView tvShopcartTitle;
        @Bind(R.id.tv_shopcart_curprice)
        TextView tvShopcartCurprice;
        @Bind(R.id.tv_shopcart_preprice)
        TextView tvShopcartPreprice;
        @Bind(R.id.btn_shopcart_cut)
        Button btnShopcartCut;
        @Bind(R.id.tv_shopcart_num)
        TextView tvShopcartNum;
        @Bind(R.id.btn_shopcart_add)
        Button btnShopcartAdd;
        @Bind(R.id.tv_shopcart_norm)
        TextView tvShopcartNorm;
        @Bind(R.id.iv_shopcart_arrow)
        ImageView ivShopcartArrow;
        @Bind(R.id.ll_shopcart)
        LinearLayout llShopcart;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void notifyCheckState(boolean isChecked) {

            for (int i = 0; i < bean.getData().size(); i++) {
                bean.getData().get(i).setItemChecked(isChecked);
                if(isChecked){
                    BigDecimal a1 = new BigDecimal(Double.toString(bean.getTotlePrice()));
                    BigDecimal b1 = new BigDecimal(Double.toString(bean.getData().get(i).getGoods_price()));
                    BigDecimal c1 = new BigDecimal(Double.toString(bean.getData().get(i).getGoods_quantity()));
                    BigDecimal x = b1.multiply(c1);
                    double y =a1.add(x).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    bean.setTotlePrice(y);
                    // bean.setTotlePrice(bean.getTotlePrice()+bean.getData().get(i).getGoods_price()*bean.getData().get(i).getGoods_quantity());
                }else{
                    bean.setTotlePrice(0);
            }
        }
        ShopCartFragment shopCartFragment = (ShopCartFragment) mFragment;
        shopCartFragment.changeAllPrice();
        notifyDataSetChanged();
    }
}
