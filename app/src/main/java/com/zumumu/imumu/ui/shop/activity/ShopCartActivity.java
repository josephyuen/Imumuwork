package com.zumumu.imumu.ui.shop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.pay.activity.AccountsActivity;
import com.zumumu.imumu.ui.shop.adapter.ShopCartAdapter;
import com.zumumu.imumu.ui.shop.contract.ShopCartContract;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ShopCartBean;
import com.zumumu.imumu.ui.shop.model.ShopCartModel;
import com.zumumu.imumu.ui.shop.presenter.ShopCartPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/13.
 */

public class ShopCartActivity extends BaseActivity<ShopCartPresenter, ShopCartModel> implements ShopCartContract.ShopCartSuperView {


    @Bind(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @Bind(R.id.toolbar2)
    Toolbar toolbar2;
    @Bind(R.id.rv_fshopcart)
    RecyclerView rvFshopcart;
    @Bind(R.id.cb_shopcart_select1)
    CheckBox cbShopcartSelect1;
    @Bind(R.id.tv_shopcart_allprice)
    TextView tvShopcartAllprice;
    @Bind(R.id.bt_fshopcart_commit)
    Button btFshopcartCommit;
    @Bind(R.id.ll_shopcart_submit)
    LinearLayout llShopcartSubmit;
    @Bind(R.id.bt_shopcart_delete)
    Button btShopcartDelete;
    @Bind(R.id.ll_shopcart_delete)
    LinearLayout llShopcartDelete;

    private boolean deleteState = false;
    private boolean checkedAll = false;
    private ShopCartAdapter mAdapter;
    private ShopCartBean mBean;
    private int deletePosition;
    private double totlePrice;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopcart;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvFshopcart.setLayoutManager(manager);
        mPresenter.loadShopCartChannelsRequest(sp.getString("userid",""));
    }

    @Override
    public void returnShopCartChannels(ShopCartBean bean) {
        mBean = bean;
        mAdapter = new ShopCartAdapter(mContext,this,bean);
        rvFshopcart.setAdapter(mAdapter);
    }

    @Override
    public void returnShopCartChannelsError(String errorMsg) {

    }

    @Override
    public void returnShopCartDelete(String s) {
        mBean.getData().remove(deletePosition);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnShopCartDeleteError(String errorMsg) {

    }

    @Override
    public void returnShopCartBuyNow(GenerateIndentBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order",bean);
        startActivity(AccountsActivity.class,bundle);
    }

    @Override
    public void returnShopCartBuyNowError(String errorMsg) {

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

    @OnClick({R.id.tv_shopcart_edit, R.id.bt_fshopcart_commit, R.id.bt_shopcart_delete,R.id.cb_shopcart_select1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                deleteState = !deleteState;
                if(deleteState){
                    llShopcartDelete.setVisibility(View.VISIBLE);
                    llShopcartSubmit.setVisibility(View.GONE);
                    tvShopcartEdit.setTextColor(getResources().getColor(R.color.colorWhiteText));
                    tvShopcartEdit.setText("完成");
                }else {
                    llShopcartDelete.setVisibility(View.GONE);
                    llShopcartSubmit.setVisibility(View.VISIBLE);
                    tvShopcartEdit.setTextColor(Color.WHITE);
                    tvShopcartEdit.setText("编辑");
                }
                break;
            case R.id.bt_fshopcart_commit:
                BuyNowBean bean = new BuyNowBean();
                bean.setUserId(sp.getString("userid",""));
                String num = "";
                String goods_id = "";
                for(int i = 0;i<mBean.getData().size();i++){
                    if(mBean.getData().get(i).isItemChecked()){
                        num = num + mBean.getData().get(i).getGoods_quantity()+",";
                        goods_id = goods_id + mBean.getData().get(i).getGoods_id() +",";
                    }
                }
                num = num.substring(0,num.length()-1);
                goods_id = goods_id.substring(0,goods_id.length()-1);

                bean.setGoodsId(goods_id);
                bean.setGoodsNum(num);
                mPresenter.loadShopCartBuyNowRequest(bean);
                //System.out.println("商品ID"+goods_id+"数量:"+num);

                break;
            case R.id.bt_shopcart_delete:
//                String cart_id = "";
//
//                for (int i = 0;i<mBean.getData().size();i++){
//                    if(mBean.getData().get(i).isItemChecked()){
//                        cart_id = cart_id + mBean.getData().get(i).getCart_id()+",";
//
//                    }
//                }
//                cart_id = cart_id.substring(0,cart_id.length()-1);
//                mPresenter.loadShopCartDeleteRequest(cart_id);


                if(cbShopcartSelect1.isChecked()){
                    ToastUitl.show("全部删除", Toast.LENGTH_SHORT);
                    return;
                }

                for (int i = 0;i<mBean.getData().size();i++){
                    if(mBean.getData().get(i).isItemChecked()){
                        deletePosition = i;
                        mPresenter.loadShopCartDeleteRequest(mBean.getData().get(i).getCart_id()+"");
                    }
                }

                break;
            case R.id.cb_shopcart_select1:
                checkedAll = !checkedAll;
                mAdapter.notifyCheckState(checkedAll);
                break;

        }
    }

    public void changeAllPrice(){
            tvShopcartAllprice.setText(mBean.getTotlePrice()+"");
    }
}
