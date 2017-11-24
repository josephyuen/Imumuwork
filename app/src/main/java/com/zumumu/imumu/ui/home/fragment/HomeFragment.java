package com.zumumu.imumu.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nzf.mvpframe.widget.LoadingTip;
import com.zumumu.imumu.R;
import com.zumumu.imumu.base.BaseLazyFragment;
import com.zumumu.imumu.ui.home.adapter.HomeFragRecycleAdapter;
import com.zumumu.imumu.ui.home.contract.HomeFragContract;
import com.zumumu.imumu.ui.home.model.HomeBean;
import com.zumumu.imumu.ui.home.model.HomeModel;
import com.zumumu.imumu.ui.home.presenter.HomePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2016/12/27.
 */

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements HomeFragContract.HomeFragSuperView {


    @Bind(R.id.rv_fhome)
    RecyclerView rvFhome;
    @Bind(R.id.fraghome_loadedTip)
    LoadingTip loadedTip;

    private HomeFragRecycleAdapter mAdapter;

    public HomeFragRecycleAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(HomeFragRecycleAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * fragment加载数据调用的方法
     */
    @Override
    protected void TriggleData() {
        mPresenter.loadHomeChannelsRequest();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {


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
    public void returnHomeChannels(HomeBean homeBean) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!" + homeBean.toString());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvFhome.setLayoutManager(manager);

        mAdapter = new HomeFragRecycleAdapter(getContext(), getActivity(), homeBean);
        rvFhome.setAdapter(mAdapter);
    }

    @Override
    public void returnHomeChannelsError(String errorMsg) {

    }

    @Override
    public void showLoading(String title) {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
        loadedTip.setTips(msg);
    }
}
