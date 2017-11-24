package com.zumumu.imumu.ui.user.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.user.contract.UserContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by PC_p on 2016/12/26.
 */

public class LoginModel implements UserContract.LoginSuperModel {
    @Override
    public Observable<String> loadLoginChannels(final UserBean userBean) {
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
//               FormBody body = new FormBody.Builder().add("username",userBean.getPhone())
//                        .add("password",userBean.getPassWord()).build();
//
//                OkHttpClientUtils.OkHttpPost(ApiConstant.HOME_URL, body, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        subscriber.onError(e);
//                        subscriber.onCompleted();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String json = response.body().string();
//                        subscriber.onNext(json);
//                        subscriber.onCompleted();
//                    }
//                });
                String url = ApiConstant.LOGIN_URL+"?mobile="+userBean.getPhone()+"&password="+userBean.getPassWord();
                OkHttpClientUtils.OKHttpGet(url, null,5000,false, new Callback() {
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


//15574819278
//123456