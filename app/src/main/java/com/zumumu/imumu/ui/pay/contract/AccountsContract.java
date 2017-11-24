package com.zumumu.imumu.ui.pay.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.pay.model.OrderBean;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/14 0014.
 */

public interface AccountsContract {
    //结算接口
    interface AccountsSuperModel extends BaseModel{
        Observable<String> loadAccountsChannels(OrderBean orderBean);
    }

    interface AccountsSuperView extends BaseView{
        void returnAccountsChannels(String msg);
        void returnAccountsChannelsError(String msg);
    }

    abstract static class AccountsSuperPresenter extends BasePresenter<AccountsSuperView,AccountsSuperModel>{
        public abstract void returnAccountsChannelsRequst(OrderBean orderBean);
    }

    //收货地址管理
    interface OrderAddressSuperModel extends BaseModel {
        Observable<String> loadMyAddressChannels(String s);  //请求收货地址列表
    }

    interface OrderAddressSuperView extends BaseView {
        void returnMyAddressChannels(List<MyAddressBean.DataBean> myAddressBean);
        void returnMyAddressChannelsError(String msg);
    }

    abstract static class OrderAddressSuperPresenter extends BasePresenter<OrderAddressSuperView,OrderAddressSuperModel> {
        public abstract void loadMyAddressChannelsRequst(String s);
    }
}
