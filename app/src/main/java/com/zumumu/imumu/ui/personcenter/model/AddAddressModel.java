package com.zumumu.imumu.ui.personcenter.model;

import com.nzf.mvpframe.baserx.RxSchedulers;
import com.nzf.mvpframe.utils.OkHttpClientUtils;
import com.zumumu.imumu.api.ApiConstant;
import com.zumumu.imumu.ui.personcenter.contract.AddressContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class AddAddressModel implements AddressContract.AddAddressSuperModel {
    @Override
    public Observable<String> loadAddAddressChannels(final UserAddressBean userAddressBean) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Map body = new HashMap();
                body.put("user_id",userAddressBean.getUser_id());
                body.put("address_name",userAddressBean.getAddress_name());
                body.put("address_mobile",userAddressBean.getAddress_mobile());
                body.put("addres_province",userAddressBean.getAddress_province());
                body.put("city",userAddressBean.getAddress_city());
                body.put("address_area",userAddressBean.getAddress_area());
                body.put("addres_detailed",userAddressBean.getAddress_detailed());
                body.put("address_default",userAddressBean.getAddress_isDefault());
                OkHttpClientUtils.OKHttpGet(ApiConstant.ADD_ADDRES_URL, body, 3000,true, new Callback() {
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
