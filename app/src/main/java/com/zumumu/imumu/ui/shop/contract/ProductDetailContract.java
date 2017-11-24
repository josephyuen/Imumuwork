package com.zumumu.imumu.ui.shop.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.shop.model.AddToCartBean;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ProductDetailBean;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/4.
 */

public interface ProductDetailContract {

    /**
     * 商品详情
     */

    interface ProductDetailSuperModel extends BaseModel{
        Observable<String> loadProductDetailChannels(String s);    //加载商品详情

        Observable<String> loadAddToCartChannels(AddToCartBean addToCartBean);  //添加进购物车

        Observable<String> loadBuyNowChannels(BuyNowBean bean);  //立即购买
    }

    interface ProductDetailSuperView extends BaseView{
        void returnProductDetailChannels(ProductDetailBean bean);
        void returnProductDetailChannelsError(String msg);

        void returnAddToCartChannels();
        void returnAddToCartChannelsError(String msg);

        void returnBuyNowChannels(GenerateIndentBean bean);
        void returnBuyNowChannelsError(String msg);

    }

    abstract class ProductDetailSuperPresenter extends BasePresenter<ProductDetailSuperView,ProductDetailSuperModel>{
        public abstract void loadProductDetailChannelsReqeust(String s);

        public abstract void loadAddToCartChannelsReqeust(AddToCartBean addToCartBean);

        public abstract void loadBuyNowChannelsReqeust(BuyNowBean bean);
    }

    /**
     * 商品评论
     */

      interface ProductCommentSuperModel extends BaseModel{

           Observable<String> loadCommentsChannels(String s);

    }

    interface ProductCommentSuperView extends BaseView{

         void returnProductCommentsChannels();
         void returnProductCommentsChannelsError(String msg);
    }

    abstract class ProductCommentsSuperPresenter extends BasePresenter<ProductCommentSuperView,ProductCommentSuperModel>{

        public abstract void returnProductCommentsChannelsRequest(String s);
    }

}
