package com.zumumu.imumu.ui.shop.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.nzf.mvpframe.widget.LoadingTip;
import com.zumumu.imumu.R;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.pay.activity.AccountsActivity;
import com.zumumu.imumu.ui.shop.adapter.RVProDetailAdapter;
import com.zumumu.imumu.ui.shop.contract.ProductDetailContract;
import com.zumumu.imumu.ui.shop.model.AddToCartBean;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ProductDetailBean;
import com.zumumu.imumu.ui.shop.model.ProductDetailModel;
import com.zumumu.imumu.ui.shop.presenter.ProductDetailPresenter;
import com.zumumu.imumu.ui.view.MyDialog;
import com.zumumu.imumu.ui.view.XCFlowLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/4.
 */

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter, ProductDetailModel> implements ProductDetailContract.ProductDetailSuperView {


    @Bind(R.id.iv_prodetail_entercart)
    ImageView ivProdetailEntercart;
    @Bind(R.id.tb_prodetail)
    Toolbar tbProdetail;
    @Bind(R.id.rv_prodetail)
    RecyclerView rvProdetail;
    @Bind(R.id.iv_prodetail_collect)
    ImageView ivProdetailCollect;
    @Bind(R.id.bt_prodetail_addcart)
    Button btProdetailAddcart;
    @Bind(R.id.bt_prodetail_buynow)
    Button btProdetailBuynow;
    @Bind(R.id.productdetail_loadTip)
    LoadingTip loadedTip;
    @Bind(R.id.productdetail_ll)
    LinearLayout productdetailLl;
    private Bundle bundle;


    private XCFlowLayout mFlowLayout;

    private Button bt_showdialog;
    private MyDialog dialog;
    private View contentView;
    private TextView tv_shop_num;
    private XCFlowLayout xcf_shop_attr;
    private ImageView iv_close;
    private Button btn_shop_add;
    private Button btn_shop_cut;
    private TextView tv_shop_name;
    private TextView tv_shop_price;
    private Button input_message;
    private ProductDetailBean mBean;
    private RVProDetailAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_product_details;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        bundle = getIntent().getExtras();
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvProdetail.setLayoutManager(manager);

        loadedTip.setOnReloadListener(new LoadingTip.onReloadListener() {
            @Override
            public void reload() {
                productdetailLl.setVisibility(View.GONE);
                loadedTip.setVisibility(View.GONE);
                mPresenter.loadProductDetailChannelsReqeust(bundle.getString("goodsid"));
            }
        });
        mPresenter.loadProductDetailChannelsReqeust(bundle.getString("goodsid"));


    }

    @Override
    public void returnProductDetailChannels(ProductDetailBean bean) {
        mBean = bean;

        productdetailLl.setVisibility(View.VISIBLE);
        loadedTip.setVisibility(View.GONE);
        mAdapter = new RVProDetailAdapter(mContext, this, bean);
        rvProdetail.setAdapter(mAdapter);

    }

    @Override
    public void returnProductDetailChannelsError(String msg) {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
        productdetailLl.setVisibility(View.GONE);
        loadedTip.setTips(msg);
    }

    @Override
    public void returnAddToCartChannels() {
        ToastUitl.show("加入购物车成功!", Toast.LENGTH_SHORT);
    }

    @Override
    public void returnAddToCartChannelsError(String msg) {

    }

    @Override
    public void returnBuyNowChannels(GenerateIndentBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", bean);
        startActivity(AccountsActivity.class, bundle);
        System.out.println(bean.toString());

    }

    @Override
    public void returnBuyNowChannelsError(String msg) {


    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_prodetail_entercart, R.id.iv_prodetail_collect, R.id.bt_prodetail_addcart, R.id.bt_prodetail_buynow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_prodetail_entercart:
                startActivity(ShopCartActivity.class);
                break;
            case R.id.iv_prodetail_collect:

                break;
            case R.id.bt_prodetail_addcart:
                initDialog(1);

                break;
            case R.id.bt_prodetail_buynow:
                initDialog(2);

                break;
        }
    }

    private void initDialog(int type) {
        dialog = new MyDialog(mContext, R.style.GoodDialog);
        //设置退出速度
        dialog.outDuration(100);
        dialog.inDuration(100);
        //设置铺满
        dialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
        //解析视图
        contentView = LayoutInflater.from(mContext).inflate(R.layout.item_attr_dialog, null);
        //设置视图
        dialog.setContentView(contentView);
        tv_shop_name = (TextView) contentView.findViewById(R.id.tv_shop_name);
        tv_shop_price = (TextView) contentView.findViewById(R.id.tv_shop_price);
        SimpleDraweeView sdv_shop_photo = (SimpleDraweeView) contentView.findViewById(R.id.sdv_shop_photo);
        tv_shop_price.setText("单价:￥ " + mBean.getData().get(0).getGoods_price());
        tv_shop_name.setText(mBean.getData().get(0).getGoods_name());
        btn_shop_cut = (Button) contentView.findViewById(R.id.btn_shop_cut);
        sdv_shop_photo.setImageURI(Uri.parse(ApiConstant.HOME_URL + mBean.getData().get(0).getPlay_img_arr().get(0)));
        btn_shop_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String num1 = tv_shop_num.getText().toString().trim();
//                int num2 = Integer.parseInt(num1)-1;
                int num2 = mBean.getData().get(0).getGoodsNum() - 1;
                if (num2 <= 0) {
                    Toast.makeText(mContext, "亲,商品数量不能为0哦!", Toast.LENGTH_SHORT).show();
                    return;
                }
                tv_shop_num.setText(num2 + "");
                mBean.getData().get(0).setGoodsNum(mBean.getData().get(0).getGoodsNum() - 1);
            }
        });

        btn_shop_add = (Button) contentView.findViewById(R.id.btn_shop_add);
        btn_shop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num6 = mBean.getData().get(0).getGoodsNum() + 1;
//              String num5 = tv_shop_num.getText().toString().trim();
//              int num6 = Integer.parseInt(num5)+1;
                tv_shop_num.setText(num6 + "");
                mBean.getData().get(0).setGoodsNum(mBean.getData().get(0).getGoodsNum() + 1);

            }
        });

        tv_shop_num = (TextView) contentView.findViewById(R.id.tv_shop_num);

        xcf_shop_attr = (XCFlowLayout) contentView.findViewById(R.id.xcf_shop_attr);

        input_message = (Button) contentView.findViewById(R.id.btn_buy_input_message);  //确认按钮

        if (type == 1) {
            input_message.setText("加入购物车");
            input_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddToCartBean addToCartBean = new AddToCartBean();
                    addToCartBean.setUserId(sp.getString("userid", ""));            //需要用到MD5加密解密

                    addToCartBean.setGoodsId(mBean.getData().get(0).getGoods_id() + "");
                    addToCartBean.setNum(tv_shop_num.getText().toString().trim());
                    mPresenter.loadAddToCartChannelsReqeust(addToCartBean);
                    dialog.dismiss();
                }
            });

        } else {
            input_message.setText("立即购买");
            input_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuyNowBean bean = new BuyNowBean();
                    bean.setUserId(sp.getString("userid", ""));
                    bean.setGoodsNum(mBean.getData().get(0).getGoodsNum() + "");
                    bean.setGoodsId(mBean.getData().get(0).getGoods_id() + "");
                    //bean.setGoodsAttr(mBean.getAttrList().get(mBean.getCurAttr()).getGoods_Attr_id()+"");
                    dialog.dismiss();
                    mPresenter.loadBuyNowChannelsReqeust(bean);
                }
            });
        }

        iv_close = (ImageView) contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (mBean.getAttrList().size() != 0) {
            initChildViews();
        }

        dialog.show();

    }

    private void initChildViews() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mBean.getAttrList().size(); i++) {
            View view = View.inflate(mContext, R.layout.tag_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_tag);
            tv.setText(mBean.getAttrList().get(i).getAttr_Value());
            if (i == 0) {
                tv.setBackgroundResource(R.color.colorPrimary);
                tv.setTextColor(Color.WHITE);

                System.out.println("属性价格" + mBean.getAttrList().get(0).getAttr_Price());
                tv_shop_price.setText("单价:￥ " + mBean.getAttrList().get(0).getAttr_Price());

            } else {
                tv.setBackgroundResource(R.drawable.round_rectangle_bg);
                tv.setTextColor(Color.BLUE);
            }
            xcf_shop_attr.addView(view, lp);
            tv.setOnClickListener(new MyOnclickListener(i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.setStop(true);
        }
    }

    class MyOnclickListener implements View.OnClickListener {
        private int position;

        public MyOnclickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            TextView tv1 = (TextView) v;
            for (int i = 0; i < xcf_shop_attr.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) xcf_shop_attr.getChildAt(i);
                TextView tv = (TextView) ll.getChildAt(0);
                tv.setBackgroundResource(R.drawable.round_rectangle_bg);
                tv.setTextColor(Color.BLUE);
            }
            tv1.setBackgroundResource(R.color.colorPrimary);
            tv1.setTextColor(Color.WHITE);
            tv_shop_price.setText("单价:¥" + mBean.getAttrList().get(position).getAttr_Price());
            mBean.setCurAttr(position);
        }
    }

}
