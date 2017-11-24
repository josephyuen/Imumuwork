package com.zumumu.imumu.ui.home.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.home.contract.ShopCartFragContract;
import com.zumumu.imumu.ui.shop.model.BuyNowBean;
import com.zumumu.imumu.ui.shop.model.GenerateIndentBean;
import com.zumumu.imumu.ui.shop.model.ShopCartBean;
import com.zumumu.imumu.ui.user.resolve.JsonResolve;

import java.util.Map;

/**
 * Created by PC_p on 2017/1/13.
 */

public class ShopCartFragPresenter extends ShopCartFragContract.ShopCartFragSuperPresenter{


    @Override
    public void loadShopCartFragChannelsRequest(String s) {
        mRxManage.add(mModel.loadShopCartFragChannels(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                System.out.println("李佳胜:"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    ShopCartBean bean = gson.fromJson(s, ShopCartBean.class);
                    mView.returnShopCartFragChannels(bean);
                }else{
                    String msg = map.get("msg");
                    mView.returnShopCartFragChannelsError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnShopCartFragChannelsError(message);
            }
        }));

    }

    @Override
    public void loadShopCartFragDeleteRequest(String s) {
        mRxManage.add(mModel.loadShopCartFragDelete(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                System.out.println("李恒:"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    mView.returnShopCartFragDelete(s);
                }else{
                    String msg = map.get("msg");
                    mView.returnShopCartFragDeleteError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnShopCartFragDeleteError(message);
            }
        }));

    }

    @Override
    public void loadShopCartFragBuyNowRequest(BuyNowBean bean) {
        mRxManage.add(mModel.loadShopCartFragBuyNow(bean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                //System.out.println("李恒:"+s);
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    GenerateIndentBean bean = gson.fromJson(s, GenerateIndentBean.class);
                    mView.returnShopCartFragBuyNow(bean);
                }else{
                    String msg = map.get("msg");
                    mView.returnShopCartFragBuyNowError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnShopCartFragBuyNowError(message);
            }
        }));
    }
}
