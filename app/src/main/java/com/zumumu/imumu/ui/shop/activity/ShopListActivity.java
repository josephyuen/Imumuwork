package com.zumumu.imumu.ui.shop.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.lisener.RecyclerViewItemOnClickLisenter;
import com.zumumu.imumu.ui.shop.adapter.ShopListAdapter;
import com.zumumu.imumu.ui.shop.contract.ShopListContract;
import com.zumumu.imumu.ui.shop.model.ShopBean;
import com.zumumu.imumu.ui.shop.model.ShopListModel;
import com.zumumu.imumu.ui.shop.presenter.ShopListPresenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class ShopListActivity extends BaseActivity<ShopListPresenter,ShopListModel> implements ShopListContract.ShopListSuperView{
    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_shoplist)
    RecyclerView rvShoplist;
    @Bind(R.id.srl_shoplist_refresh)
    SwipeRefreshLayout srlShoplistRefresh;

    private String title;//标题
    private int packNum;//分页
    private String shoptype;//商城分类

    private ShopListAdapter shopAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shoplist;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        SetStatusBarColor(getResources().getColor(R.color.colorPrimary));
        packNum = 1;
        Bundle bundle = this.getIntent().getExtras();
        shoptype = bundle.getString("num");
        title = bundle.getString("title");
        tvToolbar.setText(title);
        shopListRefresh();
        mPresenter.returnShopListChannelsRequst(packNum,shoptype);
    }

    //页面刷新
    private void shopListRefresh(){
        srlShoplistRefresh.setColorSchemeResources(R.color.colorAccent);
        srlShoplistRefresh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        srlShoplistRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.returnShopListChannelsRequst(packNum,shoptype);
                        packNum++;
                    }
                });
            }
        });
    }

    @Override
    public void returnShopListChannels(final List<ShopBean> shopList) {
        if (shopAdapter == null) {
            LinearLayoutManager llm = new LinearLayoutManager(mContext);
            llm.setOrientation(LinearLayout.VERTICAL);
            rvShoplist.setLayoutManager(llm);
            shopAdapter = new ShopListAdapter(mContext,R.layout.item_shoplist,shopList);
            shopAdapter.setOnItemClickLisenter(new RecyclerViewItemOnClickLisenter() {
                @Override
                public void onItemClick(View view, int postion) {
                    ShopBean shopBean = shopList.get(postion);
                    String goodsId = shopBean.getGoods_id();
                    Bundle bundle = new Bundle();
                    bundle.putString("goodsid",goodsId);
                    startActivity(ProductDetailActivity.class,bundle);
                }
            });
            rvShoplist.setAdapter(shopAdapter);
        }else {
            srlShoplistRefresh.setRefreshing(false);
            shopAdapter.notifyDataListChanged(shopList);
        }
    }

    @Override
    public void returnShopListChannelsError(String msg) {
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
