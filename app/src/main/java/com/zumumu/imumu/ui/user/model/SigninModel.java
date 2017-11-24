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
 * Created by PC_p on 2016/12/26.
 */

public class SigninModel implements UserContract.SigninSuperModel{
    @Override
    public Observable<String> loadSigninChannels(final UserBean userBean) {
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map params = new HashMap();
                params.put("mobile",userBean.getPhone());
                params.put("nickName",userBean.getUserName());
                params.put("password",userBean.getPassWord());
                params.put("code",userBean.getVerifycode());
                OkHttpClientUtils.OKHttpGet(ApiConstant.SIGNIN_URL, params, 5000,false, new Callback() {
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
    public Observable<String> loadVerfyCodeChannels(final String phone) {


        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map<String,String> param = new HashMap();
                param.put("mobile",phone);
                OkHttpClientUtils.OKHttpGet(ApiConstant.SIGN_CODE_URL, param, 5000,false, new Callback() {
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
