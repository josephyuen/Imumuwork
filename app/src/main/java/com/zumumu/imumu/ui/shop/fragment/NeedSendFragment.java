package com.zumumu.imumu.ui.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zumumu.imumu.R;
import com.zumumu.imumu.base.BaseLazyFragment;
import com.zumumu.imumu.ui.shop.adapter.IndentAdapter;
import com.zumumu.imumu.ui.shop.contract.MyIndentContract;
import com.zumumu.imumu.ui.shop.model.MyIndentModel;
import com.zumumu.imumu.ui.shop.presenter.MyIndentPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/10.
 */

public class NeedSendFragment extends BaseLazyFragment<MyIndentPresenter, MyIndentModel> implements MyIndentContract.MyIndentSuperView{


    @Bind(R.id.lv_myindent)
    ListView lvMyindent;

    @Override
    protected void TriggleData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_myindent;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        IndentAdapter mAdapter = new IndentAdapter(getContext(),10,"待发货");
        lvMyindent.setAdapter(mAdapter);
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

    @Override
    public void returnAllIndentChannels() {

    }

    @Override
    public void returnAllIndentChannelsError(String msg) {

    }

    @Override
    public void returnNotPayChannels() {

    }

    @Override
    public void returnNotPayChannelsError(String msg) {

    }

    @Override
    public void returnNeedSendChannels() {

    }

    @Override
    public void returnNeedSendChannelsError(String msg) {

    }

    @Override
    public void returnNeedReceiptChannels() {

    }

    @Override
    public void returnNeedReceiptChannelsError(String msg) {

    }

    @Override
    public void returnNeedAssessChannels() {

    }

    @Override
    public void returnNeedAssessChannelsError(String msg) {

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
