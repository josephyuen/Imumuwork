package com.zumumu.imumu.ui.shop.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.shop.model.ShopBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public interface ShopListContract {

    interface ShopListSuperModel extends BaseModel{
        Observable<String> loadShopListChannels(int packNum,String ShopType);
    }

    interface ShopListSuperView extends BaseView{
        void returnShopListChannels(List<ShopBean> shopList);
        void returnShopListChannelsError(String msg);
    }

    abstract static class ShopListSuperPresenter extends BasePresenter<ShopListSuperView,ShopListSuperModel>{
        public abstract void returnShopListChannelsRequst(int packNum,String ShopType);
    }
}
