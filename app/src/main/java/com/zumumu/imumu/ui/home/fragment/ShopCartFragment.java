package com.zumumu.imumu.ui.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.base.BaseLazyFragment;
import com.zumumu.imumu.ui.home.adapter.ShopCartFragAdapter;
import com.zumumu.imumu.ui.home.contract.ShopCartFragContract;
import com.zumumu.imumu.ui.home.model.ShopCartFragModel;
import com.zumumu.imumu.ui.home.presenter.ShopCartFragPresenter;
import com.zumumu.imumu.ui.pay.activity.AccountsActivity;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ShopCartBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/6.
 */

public class ShopCartFragment extends BaseLazyFragment<ShopCartFragPresenter, ShopCartFragModel> implements ShopCartFragContract.ShopCartFragSuperView {

    @Bind(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @Bind(R.id.toolbar2)
    Toolbar toolbar2;
    @Bind(R.id.rv_fshopcart)
    RecyclerView rvFshopcart;
    @Bind(R.id.bt_shopcart_delete)
    Button btShopcartDelete;
    @Bind(R.id.ll_shopcart_delete)
    LinearLayout llShopcartDelete;
    @Bind(R.id.cb_shopcart_select1)
    CheckBox cbShopcartSelect1;
    @Bind(R.id.tv_shopcart_allprice)
    TextView tvShopcartAllprice;
    @Bind(R.id.bt_fshopcart_commit)
    Button btFshopcartCommit;
    @Bind(R.id.ll_shopcart_submit)
    LinearLayout llShopcartSubmit;
    private boolean deleteState = false;
    private ShopCartFragAdapter mAdapter;
    private ShopCartBean mBean;
    private boolean checkedAll = false;
    private int deletePosition;
    private double totlePrice;


    @Override
    protected void TriggleData() {
        mPresenter.loadShopCartFragChannelsRequest(sp.getString("userid",""));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_shopcart;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvFshopcart.setLayoutManager(manager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.bt_fshopcart_commit, R.id.bt_shopcart_delete,R.id.cb_shopcart_select1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                deleteState = !deleteState;
                if (deleteState) {
                    llShopcartDelete.setVisibility(View.VISIBLE);
                    llShopcartSubmit.setVisibility(View.GONE);
                    tvShopcartEdit.setTextColor(getResources().getColor(R.color.colorWhiteText));
                    tvShopcartEdit.setText("完成");
                } else {
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
                if(num.length() == 0){
                    return;
                }
                num = num.substring(0,num.length()-1);
                goods_id = goods_id.substring(0,goods_id.length()-1);

                bean.setGoodsId(goods_id);
                bean.setGoodsNum(num);
                mPresenter.loadShopCartFragBuyNowRequest(bean);
                break;
            case R.id.bt_shopcart_delete:
                if(cbShopcartSelect1.isChecked()){
                    ToastUitl.show("全部删除", Toast.LENGTH_SHORT);
                    return;
                }

                for (int i = 0;i<mBean.getData().size();i++){
                    if(mBean.getData().get(i).isItemChecked()){
                        deletePosition = i;
                        mPresenter.loadShopCartFragDeleteRequest(mBean.getData().get(i).getCart_id()+"");
                    }
                }
                break;

            case R.id.cb_shopcart_select1:
                checkedAll = !checkedAll;
                mAdapter.notifyCheckState(checkedAll);

                break;
        }
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
    public void returnShopCartFragChannels(ShopCartBean bean) {
        mBean = bean;
        if(mAdapter == null){
            mAdapter = new ShopCartFragAdapter(mContext,mActivity,bean,this);
        }
        rvFshopcart.setAdapter(mAdapter);


    }

    @Override
    public void returnShopCartFragChannelsError(String errorMsg) {

    }

    @Override
    public void returnShopCartFragDelete(String s) {
        mBean.getData().remove(deletePosition);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnShopCartFragDeleteError(String errorMsg) {

    }

    @Override
    public void returnShopCartFragBuyNow(GenerateIndentBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order",bean);
        startActivity(AccountsActivity.class,bundle);
    }

    @Override
    public void returnShopCartFragBuyNowError(String errorMsg) {

    }

    public void changeAllPrice(){
        tvShopcartAllprice.setText(mBean.getTotlePrice()+"");
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
            if(isVisibleToUser){
                TriggleData();
                System.out.println("onResume!!!!!!!!!!!");
        }


    }
}



