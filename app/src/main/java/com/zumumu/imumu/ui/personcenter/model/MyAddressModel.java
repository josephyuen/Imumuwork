package com.zumumu.imumu.ui.personcenter.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.personcenter.contract.MyAddressContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by PC_p on 2017/1/17.
 */

public class MyAddressModel implements MyAddressContract.MyAddressSuperModel {

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

    @Override
    public Observable<String> loadDeleteChannels(final String s) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                String url = ApiConstant.DELETE_ADDRESS + "?address_id=" + s;
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
