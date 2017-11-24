package com.zumumu.imumu.ui.shop.contract;

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

public interface ShopCartContract {
    //购物车activity界面接口

    interface ShopCartSuperModel extends BaseModel {
        Observable<String> loadShopCartChannels(String s);

        Observable<String> loadShopCartDelete(String s);

        Observable<String> loadShopCartBuyNow(BuyNowBean bean);

    }

    interface ShopCartSuperView extends BaseView {
        void returnShopCartChannels(ShopCartBean bean);
        void returnShopCartChannelsError(String errorMsg);

        void returnShopCartDelete(String s );
        void returnShopCartDeleteError(String errorMsg);

        void returnShopCartBuyNow(GenerateIndentBean bean);
        void returnShopCartBuyNowError(String errorMsg);

    }

    abstract class ShopCartSuperPresenter extends BasePresenter<ShopCartContract.ShopCartSuperView,ShopCartContract.ShopCartSuperModel> {
        public abstract void loadShopCartChannelsRequest(String s);

        public abstract void loadShopCartDeleteRequest(String s);

        public abstract void loadShopCartBuyNowRequest(BuyNowBean bean);
    }

}
