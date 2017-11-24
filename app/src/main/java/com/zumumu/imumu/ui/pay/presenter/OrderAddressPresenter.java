package com.zumumu.imumu.ui.pay.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.pay.contract.AccountsContract;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class OrderAddressPresenter extends AccountsContract.OrderAddressSuperPresenter {
    @Override
    public void loadMyAddressChannelsRequst(String s) {
        mRxManage.add(mModel.loadMyAddressChannels(s).subscribe(new RxSubscriber<String>(mContext, true) {
            @Override
            protected void _onNext(String s) {
                Gson gson = new Gson();
                MyAddressBean myAddressBean = gson.fromJson(s, MyAddressBean.class);
                if (myAddressBean.getStatus() == 0) {
                    List<MyAddressBean.DataBean> dataBeen = myAddressBean.getData();
                    mView.returnMyAddressChannels(dataBeen);
                }else {
                    mView.returnMyAddressChannelsError(myAddressBean.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnMyAddressChannelsError(message);

            }
        }));
    }
}
