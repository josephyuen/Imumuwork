package com.zumumu.imumu.ui.pay.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.pay.contract.PayContract;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by PC_p on 2017/1/22.
 */

public class PayModel implements PayContract.PaySuperModel{
    @Override
    public Observable<String> loadPayChannels(final String s) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                OkHttpClientUtils.OKHttpGet(ApiConstant.ALIPAY+"?order_id="+s+"&pay_name=支付宝&status=2", null, 3000, false, new Callback() {
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
    public Observable<String> loadBalancePayChannels(final Map params) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {


                OkHttpClientUtils.OKHttpGet(ApiConstant.BALANCE_PAY, params,3000, false, new Callback() {
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
