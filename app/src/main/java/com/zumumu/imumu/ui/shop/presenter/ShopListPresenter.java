package com.zumumu.imumu.ui.shop.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.nzf.mvpframe.utils.JsonUtils;
import com.zumumu.imumu.ui.shop.contract.ShopListContract;
import com.zumumu.imumu.ui.shop.model.ShopBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3 0003.
 */

public class ShopListPresenter extends ShopListContract.ShopListSuperPresenter {
    @Override
    public void returnShopListChannelsRequst(int packNum,String ShopType) {
        mRxManage.add(mModel.loadShopListChannels(packNum,ShopType).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                String status = JsonUtils.getValue(s,"status");
                if ("0".equals(status)) {
                    String listJson = JsonUtils.getValue(s,"data");
                    Gson gson = new Gson();
                    List<ShopBean> shopBeanList= gson.fromJson(listJson,new TypeToken<List<ShopBean>>(){}.getType());
                    if (shopBeanList != null) {
                        mView.returnShopListChannels(shopBeanList);
                    }else {
                        mView.returnShopListChannelsError("连接错误，请稍后刷新重试。");
                    }
                }else {
                    String msg = JsonUtils.getValue(s,"msg");
                    mView.returnShopListChannelsError(msg);
                }

            }

            @Override
            protected void _onError(String message) {
                mView.returnShopListChannelsError(message);
            }
        }));
    }
}
