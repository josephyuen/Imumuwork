package com.zumumu.imumu.ui.personcenter.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/17.
 */

public interface MyAddressContract {
    //收货地址管理
    interface MyAddressSuperModel extends BaseModel {
        Observable<String> loadMyAddressChannels(String s);  //请求收货地址列表

        Observable<String> loadDeleteChannels(String s);   //删除收货地址

    }

    interface MyAddressSuperView extends BaseView {
        void returnMyAddressChannels(MyAddressBean myAddressBean);
        void returnMyAddressChannelsError(String msg);

        void returnDeleteChannels(String s);
        void returnDeleteChannelsError(String msg);


    }

    abstract static class MyAddressSuperPresenter extends BasePresenter<MyAddressContract.MyAddressSuperView,MyAddressContract.MyAddressSuperModel> {
        public abstract void loadMyAddressChannelsRequst(String s);

        public abstract void loadDeleteChannelsRequst(String s);
    }

}
