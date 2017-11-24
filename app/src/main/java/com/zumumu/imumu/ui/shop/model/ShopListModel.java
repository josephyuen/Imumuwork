package com.zumumu.imumu.ui.shop.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.shop.contract.ShopListContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class ShopListModel implements ShopListContract.ShopListSuperModel {
    @Override
    public Observable<String> loadShopListChannels(final int packNum, final String ShopType) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map body = new HashMap();
                body.put("num",ShopType);
//                body.put("packNum",packNum+"");
                OkHttpClientUtils.OKHttpGet(ApiConstant.SHOPLIST_URL, body, 3000,true, new Callback() {
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
