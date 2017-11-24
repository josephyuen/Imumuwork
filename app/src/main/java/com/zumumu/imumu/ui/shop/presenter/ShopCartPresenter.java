package com.zumumu.imumu.ui.shop.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.shop.contract.ShopCartContract;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ShopCartBean;
import com.zumumu.imumu.ui.user.resolve.JsonResolve;

import java.util.Map;

/**
 * Created by PC_p on 2017/1/13.
 */

public class ShopCartPresenter extends ShopCartContract.ShopCartSuperPresenter{
    @Override
    public void loadShopCartChannelsRequest(String s) {
        mRxManage.add(mModel.loadShopCartChannels(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                System.out.println("李佳胜:"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    ShopCartBean bean = gson.fromJson(s, ShopCartBean.class);
                    mView.returnShopCartChannels(bean);
                }else{
                    String msg = map.get("msg");
                    mView.returnShopCartChannelsError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnShopCartChannelsError(message);
            }
        }));

    }

    @Override
    public void loadShopCartDeleteRequest(String s) {
        mRxManage.add(mModel.loadShopCartDelete(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
               // System.out.println("李恒:"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    mView.returnShopCartDelete(s);
                }else{
                    String msg = map.get("msg");
                    mView.returnShopCartDeleteError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnShopCartDeleteError(message);
            }
        }));


    }

    @Override
    public void loadShopCartBuyNowRequest(BuyNowBean bean) {
        mRxManage.add(mModel.loadShopCartBuyNow(bean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
              //  System.out.println("李恒:"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    GenerateIndentBean bean =  gson.fromJson(s, GenerateIndentBean.class);
                    mView.returnShopCartBuyNow(bean);
                }else{
                    String msg = map.get("msg");
                    mView.returnShopCartBuyNowError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnShopCartBuyNowError(message);
            }
        }));



    }

}
