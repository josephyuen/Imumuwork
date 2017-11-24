package com.zumumu.imumu.ui.shop.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.shop.contract.ProductDetailContract;
import com.zumumu.imumu.ui.shop.model.AddToCartBean;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ProductDetailBean;
import com.zumumu.imumu.ui.user.resolve.JsonResolve;

import java.util.Map;

/**
 * Created by PC_p on 2017/1/4.
 */

public class ProductDetailPresenter extends ProductDetailContract.ProductDetailSuperPresenter{

    @Override
    public void loadProductDetailChannelsReqeust(String goodsid) {
        mRxManage.add(mModel.loadProductDetailChannels(goodsid).subscribe(new RxSubscriber<String>(mContext, true) {
            @Override
            protected void _onNext(String json) {
                Map<String,String> map = JsonResolve.parseJson(json);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    ProductDetailBean productDetailBean =  gson.fromJson(json, ProductDetailBean.class);
                    mView.returnProductDetailChannels(productDetailBean);
                }else{
                    String msg = map.get("msg");
                    mView.returnProductDetailChannelsError(msg);
                }

            }

            @Override
            protected void _onError(String message) {
                mView.returnProductDetailChannelsError(message);
            }
        }));
    }

    @Override
    public void loadAddToCartChannelsReqeust(AddToCartBean addToCartBean) {
         mRxManage.add(mModel.loadAddToCartChannels(addToCartBean).subscribe(new RxSubscriber<String>(mContext,true) {
             @Override
             protected void _onNext(String s) {
                 System.out.println("李佳胜李佳胜李佳胜:"+s);

                 Map<String,String> map = JsonResolve.parseJson(s);
                 if("0".equals(map.get("status"))){
                     mView.returnAddToCartChannels();
                 }else{
                     String msg = map.get("msg");
                     mView.returnAddToCartChannelsError(msg);
                 }
             }

             @Override
             protected void _onError(String message) {
                 mView.returnAddToCartChannelsError(message);
             }
         }));
    }

    @Override
    public void loadBuyNowChannelsReqeust(BuyNowBean bean) {
        mRxManage.add(mModel.loadBuyNowChannels(bean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
              //  System.out.println("订单信息"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    GenerateIndentBean bean = gson.fromJson(s,GenerateIndentBean.class);
                    mView.returnBuyNowChannels(bean);
                }else{
                    String msg = map.get("msg");
                    mView.returnBuyNowChannelsError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnBuyNowChannelsError(message);
            }
        }));

    }
}
