package com.zumumu.imumu.ui.pay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.pay.adpter.AccountsAdpter;
import com.zumumu.imumu.ui.pay.contract.AccountsContract;
import com.zumumu.imumu.ui.pay.model.AccountsModel;
import com.zumumu.imumu.ui.pay.model.OrderBean;
import com.zumumu.imumu.ui.pay.presenter.AccountsPresenter;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public class AccountsActivity extends BaseActivity<AccountsPresenter,AccountsModel> implements AccountsContract.AccountsSuperView{
    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_accounts_list)
    RecyclerView rvAccountsList;
    @Bind(R.id.bt_accounts_submit)
    Button btAccountsSubmit;
    @Bind(R.id.tv_accounts_gross)
    TextView tvAccountsGross;
    @Bind(R.id.tv_accounts_name)
    TextView tvAccountsName;
    @Bind(R.id.tv_accounts_phone)
    TextView tvAccountsPhone;
    @Bind(R.id.tv_accounts_location)
    TextView tvAccountsLocation;
    @Bind(R.id.rl_accounts_location)
    RelativeLayout rlAccountsLocation;
    @Bind(R.id.tv_accounts_distribution_icon)
    TextView tvAccountsDistributionIcon;
    @Bind(R.id.iv_accounts_distribution)
    ImageView ivAccountsDistribution;
    @Bind(R.id.tv_accounts_distribution)
    TextView tvAccountsDistribution;
    @Bind(R.id.rl_accounts_distribution)
    RelativeLayout rlAccountsDistribution;
    @Bind(R.id.textView2)
    TextView textView2;

    private String userId;
    private String orderId;
    private String remark = "";
    private int addressId;
    private int expressId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_accounts;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        tvToolbar.setText("结算中心");
        userId = sp.getString("userid","");
        Bundle bundle = getIntent().getExtras();
        GenerateIndentBean generateIndentBean = (GenerateIndentBean) bundle.getSerializable("order");
        GenerateIndentBean.DataBean data = generateIndentBean.getData();

        orderId = generateIndentBean.getOrder_id() + "";
        tvAccountsGross.setText(data.getOrder_total() + "");
        getOrderGoods(generateIndentBean);
        getOrderLocation(generateIndentBean);
        getOrderExpress(generateIndentBean);
    }

    //初始化订单收货地址信息
    private void getOrderLocation(GenerateIndentBean generateIndentBean) {
        GenerateIndentBean.UserAddressBean userAddressBean = generateIndentBean.getUser_address();
        addressId = userAddressBean.getAddress_id();
        tvAccountsName.setText("收货人姓名" + userAddressBean.getAddress_name());
        tvAccountsPhone.setText("收货人电话" + userAddressBean.getAddress_mobile());
        String location = "收货地址：" + userAddressBean.getAddress_province() + userAddressBean.getAddress_city()
                + userAddressBean.getAddress_area() + userAddressBean.getAddress_detailed();
        tvAccountsLocation.setText(location);
    }
    //初始化订单商品信息
    private void getOrderGoods(GenerateIndentBean generateIndentBean) {
        List<GenerateIndentBean.OrderGoodsListBean> goodsList = generateIndentBean.getOrderGoodsList();
        LinearLayoutManager llm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvAccountsList.setLayoutManager(llm);
        AccountsAdpter adpter = new AccountsAdpter(mContext, R.layout.item_account_goods, goodsList);
        rvAccountsList.setAdapter(adpter);
    }
    //初始化配送方式信息
    private void getOrderExpress(GenerateIndentBean generateIndentBean){
        List<GenerateIndentBean.ExpressBean> expressBeans = generateIndentBean.getExpress();
        GenerateIndentBean.ExpressBean expressBean = null;
        for (int i = 0;i< expressBeans.size();i++){
            if (expressBeans.get(i).getIs_default() == 0) {
                expressBean = expressBeans.get(i);
                expressId = expressBean.getExpress_id();
                String distribution = expressBean.getExpress_name() +"：￥" + expressBean.getExpress_price();
                System.out.println(distribution);
                tvAccountsDistribution.setText(distribution);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode){
                case 0:
                    Bundle bundle = data.getExtras();
                    MyAddressBean.DataBean dataBean = (MyAddressBean.DataBean) bundle.getSerializable("address");
                    String location = dataBean.getAddress_province() + dataBean.getAddress_city()
                            + dataBean.getAddress_area() + dataBean.getAddress_detailed();
                    tvAccountsName.setText(dataBean.getAddress_name());
                    tvAccountsPhone.setText(dataBean.getAddress_mobile());
                    tvAccountsLocation.setText(location);
                    addressId = dataBean.getAddress_id();
                    break;
            }
        }
    }

    @OnClick({R.id.rl_accounts_location, R.id.rl_accounts_distribution,R.id.bt_accounts_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_accounts_location:
                startActivityForResult(OrderAddressActivity.class,0);
                break;
            case R.id.rl_accounts_distribution:

                break;
            case R.id.bt_accounts_submit:
                OrderBean orderBean  = new OrderBean();
                orderBean.setUserId(userId);
                orderBean.setAddressId(addressId);
                orderBean.setExpressId(expressId);
                orderBean.setOrderId(orderId);
                orderBean.setRemark(remark);
                System.out.println( userId + ":" + addressId+ ":" + expressId+ ":" + orderId + ":" + remark);
                mPresenter.returnAccountsChannelsRequst(orderBean);
                break;
        }
    }

    @Override
    public void returnAccountsChannels(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("orderid",msg);
        startActivity(PayActivity.class,bundle);
        System.out.println("正常");
        finish();
    }

    @Override
    public void returnAccountsChannelsError(String msg) {
        ToastUitl.show(msg, Toast.LENGTH_SHORT);
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
}
