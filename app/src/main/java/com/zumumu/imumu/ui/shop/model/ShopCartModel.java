package com.zumumu.imumu.ui.shop.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.shop.contract.ShopCartContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by PC_p on 2017/1/13.
 */

public class ShopCartModel implements ShopCartContract.ShopCartSuperModel{
    @Override
    public Observable<String> loadShopCartChannels(final String s) {
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                String url = ApiConstant.SHOW_SHOPCART+"?user_id="+s;
                OkHttpClientUtils.OKHttpGet(url,null,5000,false, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        subscriber.onError(e);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        subscriber.onNext(json);
                        subscriber.onCompleted();
                    }
                });

            }
        }).compose(RxSchedulers.<String>io_main());
    }




    @Override
    public Observable<String> loadShopCartDelete(final String s) {
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                String url = ApiConstant.DELETE_CART+"?cart_id="+s;
                OkHttpClientUtils.OKHttpGet(url,null,5000,false,new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        subscriber.onNext(json);
                        subscriber.onCompleted();
                    }
                });

            }
        }).compose(RxSchedulers.<String>io_main());
    }

    @Override
    public Observable<String> loadShopCartBuyNow(final BuyNowBean bean) {
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id",bean.getUserId());
                params.put("num",bean.getGoodsNum());
                params.put("goods_id",bean.getGoodsId());
                OkHttpClientUtils.OKHttpGet(ApiConstant.DETAIL_BUY_NOW,params,5000,false,new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        subscriber.onNext(json);
                        subscriber.onCompleted();
                    }
                });

            }
        }).compose(RxSchedulers.<String>io_main());
    }

}
