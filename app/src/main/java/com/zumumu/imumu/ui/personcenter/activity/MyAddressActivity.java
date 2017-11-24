package com.zumumu.imumu.ui.personcenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.nzf.mvpframe.base.BaseActivity;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.personcenter.adapter.MyAddressAdapter;
import com.zumumu.imumu.ui.personcenter.contract.MyAddressContract;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;
import com.zumumu.imumu.ui.personcenter.model.MyAddressModel;
import com.zumumu.imumu.ui.personcenter.model.UserAddressBean;
import com.zumumu.imumu.ui.personcenter.presenter.MyAddressPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by PC_p on 2017/1/11.
 * 收货地址界面
 */

public class MyAddressActivity extends BaseActivity<MyAddressPresenter,MyAddressModel> implements MyAddressContract.MyAddressSuperView{

    @Bind(R.id.bt_myaddress_edit)
    Button btMyaddressEdit;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_myaddress)
    RecyclerView rvMyaddress;
    @Bind(R.id.bt_myaddress_add)
    Button btMyaddressAdd;
    private MyAddressAdapter mAdapter;
    private boolean editing = false;
    private int deletePosition;
    private MyAddressBean mBean;
    private final int RESULT_OK = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_myaddress;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvMyaddress.setLayoutManager(manager);
        mPresenter.loadMyAddressChannelsRequst(sp.getString("userid",""));

    }

    @OnClick({R.id.bt_myaddress_edit, R.id.bt_myaddress_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_myaddress_edit:
                editing = !editing;
                if (editing == true) {
                    btMyaddressEdit.setText("完成编辑");
                } else {
                    btMyaddressEdit.setText("编辑");
                }
                if(mAdapter != null){
                    mAdapter.refreshDisplay();
                }

                break;
            case R.id.bt_myaddress_add:
                startActivityForResult(AddAddressActivity.class,0);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data !=null){
            switch (resultCode){
                case RESULT_OK:
                    Bundle bundle = data.getExtras();
                    UserAddressBean bean = (UserAddressBean)bundle.getSerializable("userAddress");
                    MyAddressBean.DataBean dataBean = new MyAddressBean.DataBean();
                    dataBean.setAddress_default(Integer.parseInt(bean.getAddress_isDefault()));
                    dataBean.setAddress_area(bean.getAddress_area());
                    dataBean.setAddress_city(bean.getAddress_city());
                    dataBean.setAddress_mobile(bean.getAddress_mobile());
                    dataBean.setAddress_detailed(bean.getAddress_detailed());
                    dataBean.setAddress_province(bean.getAddress_province());
                    dataBean.setAddress_name(bean.getAddress_name());
                    dataBean.setUser_id(bean.getUser_id());
                    mBean.getData().add(0,dataBean);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }

    }

    @Override
    public void returnMyAddressChannels(MyAddressBean myAddressBean) {
        mBean = myAddressBean;
        mAdapter = new MyAddressAdapter(mContext,myAddressBean,this);
        rvMyaddress.setAdapter(mAdapter);

    }

    @Override
    public void returnMyAddressChannelsError(String msg) {

    }

    @Override
    public void returnDeleteChannels(String s) {
         System.out.println("地址删除成功了吗?????"+s);
         mBean.getData().remove(deletePosition);
         mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnDeleteChannelsError(String msg) {

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

    public void loadDeleteLogic(int position,int addressId){
        mPresenter.loadDeleteChannelsRequst(addressId+"");
        deletePosition = position;
    }

    public void loadModifyScreen(int position){
        Intent intent = new Intent(this,ModifyMyAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",mBean.getData().get(position).getAddress_name());
        bundle.putString("num",mBean.getData().get(position).getAddress_mobile());
        bundle.putString("province",mBean.getData().get(position).getAddress_province());
        bundle.putString("city",mBean.getData().get(position).getAddress_city());
        bundle.putString("area",mBean.getData().get(position).getAddress_area());
        bundle.putString("detail",mBean.getData().get(position).getAddress_detailed());
        bundle.putString("default",mBean.getData().get(position).getAddress_default()+"");
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
    }

}
