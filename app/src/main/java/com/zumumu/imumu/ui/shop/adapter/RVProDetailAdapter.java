package com.zumumu.imumu.ui.shop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zumumu.imumu.R;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.shop.activity.ProductCommentActivity;
import com.zumumu.imumu.ui.shop.model.ProductDetailBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/13.
 * 商品详情的RecycleView 的Adapter
 */

public class RVProDetailAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private Activity mActivity;
    private ProductDetailBean mBean;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private boolean isStop = false;
    private int previousPosition;
    private boolean isFirstTime = true;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public RVProDetailAdapter(Context context, Activity activity, ProductDetailBean bean) {
        mContext = context;
        mActivity = activity;
        mBean = bean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FIRST;
        } else if (position == 1) {
            return SECOND;
        } else {
            return THIRD;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == FIRST) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_prodetail_1, parent, false);
            ViewHolder1 holder1 = new ViewHolder1(view);
            return holder1;
        } else if (viewType == SECOND) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_prodetail_2, parent, false);
            ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_prodetail_3, parent, false);
            ViewHolder3 holder3 = new ViewHolder3(view);
            return holder3;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            final ViewHolder1 holder1 = (ViewHolder1) holder;
            ProDetailBigPicAdapter adapter1 = new ProDetailBigPicAdapter(mContext, mActivity, mBean); //商品详情轮播图
            holder1.vpProdetailBigpics.setAdapter(adapter1);

            if(isFirstTime) {
                for (int i = 0; i < mBean.getData().get(0).getPlay_img_arr().size(); i++) {

                    View v = new View(mContext);
                    v.setBackgroundResource(R.drawable.point_bg);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
                    if (i != 0) {
                        // 当前不是第一个点, 需要设置左边距
                        params.leftMargin = 30;
                    }
                    v.setLayoutParams(params);
                    v.setEnabled(false);
                    holder1.llPointGroup.addView(v);
                }
                class MyListener implements ViewPager.OnPageChangeListener {

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        int newPosition = position % mBean.getData().get(0).getPlay_img_arr().size();

                        // 把当前选中的点给切换了, 还有描述信息也切换
                        holder1.llPointGroup.getChildAt(previousPosition).setEnabled(false);
                        holder1.llPointGroup.getChildAt(newPosition).setEnabled(true);

                        // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                        previousPosition = newPosition;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
                MyListener listener = new MyListener();
                holder1.vpProdetailBigpics.setOnPageChangeListener(listener);
                listener.onPageSelected(0);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!isStop) {
                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    holder1.vpProdetailBigpics.setCurrentItem(holder1.vpProdetailBigpics.getCurrentItem() + 1);
                                }
                            });
                            SystemClock.sleep(3000);
                        }
                         System.out.println("我(2)已经完成了我的任务,可以休息了!");
                    }

                }).start();

                isFirstTime = false;
            }




            holder1.tvProdetailTitle.setText(mBean.getData().get(0).getGoods_name());  //商品名称
            holder1.tvProdetailDes.setText("        " + mBean.getData().get(0).getGoods_BriefDescription()); //商品描述
            holder1.tvProdetailPreprice.setText("原价:¥" + mBean.getData().get(0).getGoods_priceY());  //原价
            holder1.tvProdetailPreprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线
            holder1.tvProdetailCurprice.setText("当前价格:¥"+mBean.getData().get(0).getGoods_price());
            holder1.tvProdetailVip1.setText("高级会员价:¥" + mBean.getData().get(0).getHighPrice()); //高级会员价
            holder1.tvProdetailVip2.setText("VIP会员价:¥" + mBean.getData().get(0).getVipPrice());   //VIP会员价
            holder1.tvProdetailSales.setText("销量:" + mBean.getData().get(0).getGoods_sales());  //销量
            holder1.tvProdetailStock.setText("库存" + mBean.getData().get(0).getGoods_inventory()); //库存

            holder1.ivProdetailArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ProductCommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("goodsid", mBean.getData().get(0).getGoods_id() + "");
                    mActivity.startActivity(intent);
                }
            });

        } else if (position == 1) {

        } else {
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.sdvProdetailItem3.setImageURI(Uri.parse(ApiConstant.HOME_URL + mBean.getData().get(0).getDescription_att().get(position - 2)));
        }


    }

    @Override
    public int getItemCount() {

        return 2 + mBean.getData().get(0).getDescription_att().size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @Bind(R.id.vp_prodetail_bigpics)
        ViewPager vpProdetailBigpics;
        @Bind(R.id.ll_point_group)
        LinearLayout llPointGroup;
        @Bind(R.id.tv_prodetail_title)
        TextView tvProdetailTitle;
        @Bind(R.id.tv_prodetail_des)
        TextView tvProdetailDes;
        @Bind(R.id.tv_prodetail_curprice)
        TextView tvProdetailCurprice;
        @Bind(R.id.tv_prodetail_preprice)
        TextView tvProdetailPreprice;
        @Bind(R.id.tv_prodetail_vip1)
        TextView tvProdetailVip1;
        @Bind(R.id.tv_prodetail_vip2)
        TextView tvProdetailVip2;
        @Bind(R.id.tv_prodetail_sales)
        TextView tvProdetailSales;
        @Bind(R.id.tv_prodetail_stock)
        TextView tvProdetailStock;
        @Bind(R.id.tv_prodetail_assess)
        TextView tvProdetailAssess;
        @Bind(R.id.tv_prodetail_mark)
        TextView tvProdetailMark;
        @Bind(R.id.iv_prodetail_arrow)
        ImageView ivProdetailArrow;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {

        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        @Bind(R.id.sdv_prodetail_item3)
        SimpleDraweeView sdvProdetailItem3;

        public ViewHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
