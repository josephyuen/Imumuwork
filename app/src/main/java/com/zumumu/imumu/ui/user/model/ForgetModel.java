package com.zumumu.imumu.ui.user.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.user.contract.UserContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class ForgetModel implements UserContract.ForgetSuperModel {
    @Override
    public Observable<String> loadForgetVerifyChannels(final String phone) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map body = new HashMap();
                body.put("mobile",phone);
                OkHttpClientUtils.OKHttpGet(ApiConstant.FORGET_CODE_URL, body, 3000,false, new Callback() {
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
    public Observable<String> loadForgetChannels(final UserBean userBean) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map body = new HashMap();
                body.put("mobile",userBean.getPhone());
                body.put("password",userBean.getPassWord());
                body.put("code",userBean.getVerifycode());
                OkHttpClientUtils.OKHttpGet(ApiConstant.FORGET_URL, body, 3000,false, new Callback() {
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
