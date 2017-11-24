package com.zumumu.imumu.ui.pay.presenter;

import com.nzf.mvpframe.baserx.RxSubscriber;
import com.nzf.mvpframe.utils.JsonUtils;
import com.zumumu.imumu.ui.pay.contract.AccountsContract;
import com.zumumu.imumu.ui.pay.model.OrderBean;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class AccountsPresenter extends AccountsContract.AccountsSuperPresenter {
    @Override
    public void returnAccountsChannelsRequst(OrderBean orderBean) {
        mRxManage.add(mModel.loadAccountsChannels(orderBean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                String status = JsonUtils.getValue(s,"status");
                if ("0".equals(status)) {
                    String data = JsonUtils.getValue(s,"data");
                    String orderId = JsonUtils.getValue(data,"order_id");
                    mView.returnAccountsChannels(orderId);
                }else {
                    mView.returnAccountsChannelsError(JsonUtils.getValue(s,"msg"));
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnAccountsChannelsError(message);
            }
        }));
    }
}
