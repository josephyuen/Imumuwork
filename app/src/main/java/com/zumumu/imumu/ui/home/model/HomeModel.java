package com.zumumu.imumu.ui.home.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.home.contract.HomeFragContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by PC_p on 2016/12/28.
 */

public class HomeModel implements HomeFragContract.HomeFragSuperModel {
    @Override
    public Observable<String> loadHomeChannels() {
       return Observable.create(new Observable.OnSubscribe<String>(){
           @Override
           public void call(final Subscriber<? super String> subscriber) {
               OkHttpClientUtils.OKHttpGet(ApiConstant.HOME_PAGE_URL, null, 5000,true, new Callback() {
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
