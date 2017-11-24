package com.zumumu.imumu.ui.pay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.lisener.RecyclerViewItemOnClickLisenter;
import com.zumumu.imumu.ui.pay.adpter.OrderAddressAdpter;
import com.zumumu.imumu.ui.pay.contract.AccountsContract;
import com.zumumu.imumu.ui.pay.model.OrderAddressModel;
import com.zumumu.imumu.ui.pay.presenter.OrderAddressPresenter;
import com.zumumu.imumu.ui.personcenter.activity.AddAddressActivity;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class OrderAddressActivity extends BaseActivity<OrderAddressPresenter, OrderAddressModel> implements AccountsContract.OrderAddressSuperView {
    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_oderaddress_address)
    RecyclerView rvOderaddressAddress;
    @Bind(R.id.bt_myaddress_add)
    Button btMyaddressAdd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_oderaddress;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvToolbar.setText("选择收货地址");
        String user_Id = sp.getString("userid","");
        mPresenter.loadMyAddressChannelsRequst(user_Id);
    }

    @Override
    public void returnMyAddressChannels(final List<MyAddressBean.DataBean> myAddressBean) {
        System.out.println("集合长度："+ myAddressBean.size());
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvOderaddressAddress.setLayoutManager(llm);
        OrderAddressAdpter adpter = new OrderAddressAdpter(mContext,R.layout.item_orderaddress_location,myAddressBean);
        adpter.setOnItemClickLisenter(new RecyclerViewItemOnClickLisenter() {
            @Override
            public void onItemClick(View view, int postion) {
                MyAddressBean.DataBean dataBean= myAddressBean.get(postion);
                Bundle bundle = new Bundle();
                bundle.putSerializable("addreess",dataBean);
                Intent intent = new Intent();
                intent.putExtra("address",dataBean);
                setResult(0,intent);
                finish();
            }
        });
        rvOderaddressAddress.setAdapter(adpter);
    }

    @Override
    public void returnMyAddressChannelsError(String msg) {
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


    @OnClick(R.id.bt_myaddress_add)
    public void onClick() {
        startActivityForResult(AddAddressActivity.class,1);
    }
}
