package com.zumumu.imumu.ui.pay.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.pay.contract.AccountsContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class OrderAddressModel implements AccountsContract.OrderAddressSuperModel {
    @Override
    public Observable<String> loadMyAddressChannels(final String s) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                String url = ApiConstant.SHOW_ADDRESS + "?user_id=" + s;
                OkHttpClientUtils.OKHttpGet(url, null, 5000, true, new Callback() {
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
