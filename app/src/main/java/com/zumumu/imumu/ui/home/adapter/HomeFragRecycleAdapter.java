package com.zumumu.imumu.ui.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zumumu.imumu.R;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.home.model.HomeBean;
import com.zumumu.imumu.ui.shop.activity.ProductDetailActivity;
import com.zumumu.imumu.ui.shop.activity.ShopListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/3.
 */

public class HomeFragRecycleAdapter extends RecyclerView.Adapter {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;



    private Context context;
    private Activity mActivity;
    private HomeBean mHomeBean;

    private boolean isStop = false;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    private int previousPosition = 0;

    private boolean isFirstTime = true;
    public HomeFragRecycleAdapter(Context context, Activity activity, HomeBean homeBean) {
        this.context = context;
        mActivity = activity;
        mHomeBean = homeBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FIRST;
        } else if (position <= 2) {
            return SECOND;
        } else if (position == 3) {
            return THIRD;
        } else {
            return FOURTH;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == FIRST) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_item1, parent, false);
            ViewHolder1 holder1 = new ViewHolder1(view);
            return holder1;
        } else if (viewType == SECOND) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fhome_item2, parent, false);
            ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        } else if (viewType == THIRD) {
            TextView tv = new TextView(context);
            tv.setText("猜你喜欢:");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(16);
            tv.setPadding(20, 5, 0, 5);
            view = tv;
            ViewHolder3 holder3 = new ViewHolder3(view);
            return holder3;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_shoplist, parent, false);
            ViewHolder4 holder4 = new ViewHolder4(view);
            return holder4;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {
            final ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.vpHomefrag.setAdapter(new HomeFragBigPics(context, mHomeBean));  //轮播图

            if(isFirstTime) {
                for (int i = 0; i < mHomeBean.getData().get(0).getCarousel_img_arr().size(); i++) {

                    View v = new View(context);
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
                        int newPosition = position % mHomeBean.getData().get(0).getCarousel_img_arr().size();

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
                holder1.vpHomefrag.setOnPageChangeListener(listener);
                listener.onPageSelected(0);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!isStop) {
                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    holder1.vpHomefrag.setCurrentItem(holder1.vpHomefrag.getCurrentItem() + 1);
                                }
                            });
                            SystemClock.sleep(3000);
                        }
                        System.out.println("我(1)已经完成了我的任务,可以休息了!");
                    }

                }).start();

                isFirstTime = false;
            }










            holder1.ivFhomeFarm.setImageResource(R.mipmap.search1);
            holder1.ivFhomeMeat.setImageResource(R.mipmap.search1);
            holder1.ivFhomeVip.setImageResource(R.mipmap.search1);
            holder1.ivFhomeMore.setImageResource(R.mipmap.search1);


        } else if (position <= 2) {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            if (position == 1) {
                holder2.ivItemFhomeHotimg1.setImageURI(Uri.parse(ApiConstant.HOME_URL + mHomeBean.getData().get(position - 1).getGoods_play_img()));
                holder2.ivItemFhomeHotimg2.setImageURI(Uri.parse(ApiConstant.HOME_URL + mHomeBean.getData().get(position).getGoods_play_img()));


                holder2.tvItemFhomeHottitle1.setText(mHomeBean.getData().get(position - 1).getGoods_name() + "");
                holder2.tvItemFhomeHottitle2.setText(mHomeBean.getData().get(position).getGoods_name() + "");

                holder2.tvItemPrice1.setText("¥" + mHomeBean.getData().get(position - 1).getGoods_price());
                holder2.tvItemPrice2.setText("¥" + mHomeBean.getData().get(position).getGoods_price());

                holder2.rlFhomeItem21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("goodsid", mHomeBean.getData().get(position - 1).getGoods_id() + "");
                        intent.putExtras(bundle);
                        mActivity.startActivity(intent);
                    }
                });

                holder2.rlFhomeItem22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("goodsid", mHomeBean.getData().get(position).getGoods_id() + "");
                        intent.putExtras(bundle);
                        mActivity.startActivity(intent);
                    }
                });

            } else {
                holder2.ivItemFhomeHotimg1.setImageURI(Uri.parse(ApiConstant.HOME_URL + mHomeBean.getData().get(position).getGoods_play_img()));
                holder2.ivItemFhomeHotimg2.setImageURI(Uri.parse(ApiConstant.HOME_URL + mHomeBean.getData().get(position + 1).getGoods_play_img()));


                holder2.tvItemFhomeHottitle1.setText(mHomeBean.getData().get(position).getGoods_name() + "");
                holder2.tvItemFhomeHottitle2.setText(mHomeBean.getData().get(position + 1).getGoods_name() + "");

                holder2.tvItemPrice1.setText("¥" + mHomeBean.getData().get(position).getGoods_price());
                holder2.tvItemPrice2.setText("¥" + mHomeBean.getData().get(position + 1).getGoods_price());

                holder2.rlFhomeItem21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("goodsid", mHomeBean.getData().get(position).getGoods_id() + "");
                        intent.putExtras(bundle);
                        mActivity.startActivity(intent);
                    }
                });

                holder2.rlFhomeItem22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("goodsid", mHomeBean.getData().get(position + 1).getGoods_id() + "");
                        intent.putExtras(bundle);
                        mActivity.startActivity(intent);
                    }
                });


            }

        } else if (position == 3) {
            ViewHolder3 holder3 = (ViewHolder3) holder;
        } else {
            ViewHolder4 holder4 = (ViewHolder4) holder;
            holder4.tvShoplistName.setText(mHomeBean.getData().get(position).getGoods_name());
            holder4.tvShoplistPrice.setText("当前价格:¥" + mHomeBean.getData().get(position).getGoods_price());
            holder4.tvShoplistVipprice.setVisibility(View.INVISIBLE);
            holder4.tvShoplistInventory.setText("库存:" + mHomeBean.getData().get(position).getGoods_inventory());
            holder4.tvShoplistNum.setText("销量:" + mHomeBean.getData().get(position).getGoods_sales());
            holder4.sdvIcon.setImageURI(ApiConstant.HOME_URL + mHomeBean.getData().get(position).getGoods_play_img());

            holder4.rlGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("goodsid", mHomeBean.getData().get(position).getGoods_id() + "");
                    intent.putExtras(bundle);
                    mActivity.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {
        @Bind(R.id.vp_homefrag)
        ViewPager vpHomefrag;
        @Bind(R.id.ll_point_group)
        LinearLayout llPointGroup;
        @Bind(R.id.activity_main)
        RelativeLayout activityMain;
        @Bind(R.id.et_home_search)
        EditText etHomeSearch;
        @Bind(R.id.bt_home_search)
        ImageView btHomeSearch;
        @Bind(R.id.iv_fhome_cub)
        ImageView ivFhomeCub;
        @Bind(R.id.ll_fhome_cub)
        LinearLayout llFhomeCub;
        @Bind(R.id.iv_fhome_video)
        ImageView ivFhomeVideo;
        @Bind(R.id.ll_fhome_video)
        LinearLayout llFhomeVideo;
        @Bind(R.id.iv_fhome_meat)
        ImageView ivFhomeMeat;
        @Bind(R.id.ll_fhome_meat)
        LinearLayout llFhomeMeat;
        @Bind(R.id.iv_fhome_farm)
        ImageView ivFhomeFarm;
        @Bind(R.id.ll_fhome_farm)
        LinearLayout llFhomeFarm;
        @Bind(R.id.iv_fhome_vip)
        ImageView ivFhomeVip;
        @Bind(R.id.textView8)
        TextView textView8;
        @Bind(R.id.ll_fhome_vip)
        LinearLayout llFhomeVip;
        @Bind(R.id.iv_fhome_more)
        ImageView ivFhomeMore;
        @Bind(R.id.ll_fhome_activity)
        LinearLayout llFhomeActivity;

        @OnClick({R.id.ll_fhome_cub, R.id.ll_fhome_video, R.id.ll_fhome_meat, R.id.ll_fhome_farm, R.id.ll_fhome_vip, R.id.ll_fhome_activity})
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            switch (view.getId()) {
                case R.id.ll_fhome_cub:
                    bundle.putString("title", "幼崽交易中心");
                    bundle.putString("num", "2");
                    Intent intent = new Intent(mActivity, ShopListActivity.class);
                    intent.putExtras(bundle);
                    mActivity.startActivity(intent);
                    break;
                case R.id.ll_fhome_video:

                    break;
                case R.id.ll_fhome_meat:
                    bundle.putString("title", "肉类交易中心");
                    bundle.putString("num", "5");
                    Intent intent1 = new Intent(mActivity, ShopListActivity.class);
                    intent1.putExtras(bundle);
                    mActivity.startActivity(intent1);
                    break;
                case R.id.ll_fhome_farm:
                    bundle.putString("title", "农产品交易中心");
                    bundle.putString("num", "3");
                    Intent intent2 = new Intent(mActivity, ShopListActivity.class);
                    intent2.putExtras(bundle);
                    mActivity.startActivity(intent2);
                    break;
                case R.id.ll_fhome_vip:
                    bundle.putString("title", "VIP贵宾卡");
                    bundle.putString("num", "4");
                    Intent intent3 = new Intent(mActivity, ShopListActivity.class);
                    intent3.putExtras(bundle);
                    mActivity.startActivity(intent3);
                    break;
                case R.id.ll_fhome_activity:
                    bundle.putString("title", "优惠活动中心");
                    bundle.putString("num", "6");
                    Intent intent4 = new Intent(mActivity, ShopListActivity.class);
                    intent4.putExtras(bundle);
                    mActivity.startActivity(intent4);
                    break;
            }
        }

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_item_fhome_hotimg1)
        SimpleDraweeView ivItemFhomeHotimg1;
        @Bind(R.id.tv_item_fhome_hottitle1)
        TextView tvItemFhomeHottitle1;
        @Bind(R.id.tv_item_price1)
        TextView tvItemPrice1;
        @Bind(R.id.rl_fhome_item2_1)
        RelativeLayout rlFhomeItem21;
        @Bind(R.id.iv_item_fhome_hotimg2)
        SimpleDraweeView ivItemFhomeHotimg2;
        @Bind(R.id.tv_item_fhome_hottitle2)
        TextView tvItemFhomeHottitle2;
        @Bind(R.id.tv_item_price2)
        TextView tvItemPrice2;
        @Bind(R.id.rl_fhome_item2_2)
        RelativeLayout rlFhomeItem22;

        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


class ViewHolder3 extends RecyclerView.ViewHolder {

    public ViewHolder3(View itemView) {
        super(itemView);

    }
}


class ViewHolder4 extends RecyclerView.ViewHolder {
    @Bind(R.id.sdv_icon)
    SimpleDraweeView sdvIcon;
    @Bind(R.id.tv_shoplist_name)
    TextView tvShoplistName;
    @Bind(R.id.tv_shoplist_price)
    TextView tvShoplistPrice;
    @Bind(R.id.tv_shoplist_vipprice)
    TextView tvShoplistVipprice;
    @Bind(R.id.tv_shoplist_inventory)
    TextView tvShoplistInventory;
    @Bind(R.id.tv_shoplist_num)
    TextView tvShoplistNum;
    @Bind(R.id.rl_goods)
    RelativeLayout rlGoods;

    public ViewHolder4(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}




