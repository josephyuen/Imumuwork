package com.zumumu.imumu.ui.pay.presenter;

import com.nzf.mvpframe.baserx.RxSubscriber;
import com.nzf.mvpframe.utils.JsonUtils;
import com.zumumu.imumu.ui.pay.contract.PayContract;

import java.util.Map;

/**
 * Created by PC_p on 2017/1/22.
 */

public class PayPresenter extends PayContract.PaySuperPresenter{
    @Override
    public void returnPayChannelsRequst(String s) {
        mRxManage.add(mModel.loadPayChannels(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                String status = JsonUtils.getValue(s,"status");
                if ("0".equals(status)) {
                    String data = JsonUtils.getValue(s,"data");
                    mView.returnPayChannels(data);
                }else {
                    mView.returnPayChannelsError(JsonUtils.getValue(s,"msg"));
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnPayChannelsError(message);
            }
        }));


    }

    @Override
    public void returnBalancePayChannelsRequst(Map params) {
        mRxManage.add(mModel.loadBalancePayChannels(params).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                System.out.println("余额支付"+s);
                String status = JsonUtils.getValue(s,"status");
                if ("0".equals(status)) {
                    String msg = JsonUtils.getValue(s,"msg");
                    mView.returnBalancePayChannels(msg);
                }else {
                    mView.returnBalancePayChannelsError(JsonUtils.getValue(s,"msg"));
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnBalancePayChannelsError(message);
            }
        }));

    }
}
