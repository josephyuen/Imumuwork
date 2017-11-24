package com.zumumu.imumu.ui.home.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ShopCartBean;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/13.
 */

public interface ShopCartFragContract{
    //购物车fragment界面接口

    interface ShopCartFragSuperModel extends BaseModel{
        Observable<String> loadShopCartFragChannels(String s);

        Observable<String> loadShopCartFragDelete(String s);

        Observable<String> loadShopCartFragBuyNow(BuyNowBean bean);
    }

    interface ShopCartFragSuperView extends BaseView{
        void returnShopCartFragChannels(ShopCartBean shopCartBean);
        void returnShopCartFragChannelsError(String errorMsg);

        void returnShopCartFragDelete(String s );
        void returnShopCartFragDeleteError(String errorMsg);

        void returnShopCartFragBuyNow(GenerateIndentBean bean);
        void returnShopCartFragBuyNowError(String errorMsg);
    }

    abstract class ShopCartFragSuperPresenter extends BasePresenter<ShopCartFragContract.ShopCartFragSuperView,ShopCartFragContract.ShopCartFragSuperModel>{
        public abstract void loadShopCartFragChannelsRequest(String s);

        public abstract void loadShopCartFragDeleteRequest(String s);

        public abstract void loadShopCartFragBuyNowRequest(BuyNowBean bean);
    }

}
