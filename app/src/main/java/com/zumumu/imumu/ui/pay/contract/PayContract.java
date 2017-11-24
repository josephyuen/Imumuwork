package com.zumumu.imumu.ui.pay.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/22.
 */

public interface  PayContract {
    interface PaySuperModel extends BaseModel {
        Observable<String> loadPayChannels(String s);

        Observable<String> loadBalancePayChannels(Map params);

    }

    interface PaySuperView extends BaseView {
        void returnPayChannels(String msg);
        void returnPayChannelsError(String msg);

        void returnBalancePayChannels(String msg);
        void returnBalancePayChannelsError(String msg);
    }

    abstract static class PaySuperPresenter extends BasePresenter<PayContract.PaySuperView,PayContract.PaySuperModel> {
        public abstract void returnPayChannelsRequst(String s);

        public abstract void returnBalancePayChannelsRequst(Map params);
    }
}
