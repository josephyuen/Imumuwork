package com.zumumu.imumu.ui.shop.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/10.
 * 我的订单
 */

public interface MyIndentContract {

    interface MyIndentSuperModel extends BaseModel {
        Observable<String> returnAllIndentChannels();  //全部订单

        Observable<String> returnNotPayChannels();  //未付款

        Observable<String> returnNeedSendChannels();//待发货

        Observable<String> returnNeedReceiptChannels();//待收货

        Observable<String> returnNeedAssessChannels();//待评价
    }

    interface MyIndentSuperView extends BaseView {
        void returnAllIndentChannels();
        void returnAllIndentChannelsError(String msg);//全部订单

        void returnNotPayChannels();
        void returnNotPayChannelsError(String msg);//未付款

        void returnNeedSendChannels();
        void returnNeedSendChannelsError(String msg);//待发货

        void returnNeedReceiptChannels();
        void returnNeedReceiptChannelsError(String msg);//待收货

        void returnNeedAssessChannels();
        void returnNeedAssessChannelsError(String msg);//待评价

    }

    abstract static class MyIndentSuperPresenter extends BasePresenter<MyIndentContract.MyIndentSuperView,MyIndentContract.MyIndentSuperModel> {
        public abstract void returnAllIndentChannelsRequst(); //全部订单

        public abstract void returnNotPayChannelsRequst(); //未付款

        public abstract void returnNeedSendChannelsRequst();//待发货

        public abstract void returnNeedReceiptChannelsRequst();//待收货

        public abstract void returnNeedAssessChannelsRequst();//待评价

    }

}
