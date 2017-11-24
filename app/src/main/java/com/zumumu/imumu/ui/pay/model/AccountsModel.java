package com.zumumu.imumu.ui.pay.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.pay.contract.AccountsContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class AccountsModel implements AccountsContract.AccountsSuperModel {
    @Override
    public Observable<String> loadAccountsChannels(final OrderBean orderBean) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map body = new HashMap();
                body.put("order_id",orderBean.getOrderId()+"");
                body.put("remark",orderBean.getRemark());
                body.put("user_id",orderBean.getUserId()+"");
                body.put("address_id",orderBean.getAddressId()+"");
                body.put("express_id",orderBean.getExpressId()+"");
                OkHttpClientUtils.OKHttpGet(ApiConstant.ACCOUNTS_URL, body, 3000, false, new Callback() {
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
