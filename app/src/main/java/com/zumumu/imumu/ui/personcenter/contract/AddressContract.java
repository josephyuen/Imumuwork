package com.zumumu.imumu.ui.personcenter.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.personcenter.model.UserAddressBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public interface AddressContract {
    //添加收货地址
    interface AddAddressSuperModel extends BaseModel{
        Observable<String> loadAddAddressChannels(UserAddressBean userAddress);
    }

    interface AddAddressSuperView extends BaseView{
        void returnAddAddressChannels(UserAddressBean userAddressBean);
        void returnAddAddressChannelsError(String msg);
    }

    abstract static class AddAddressSuperPresenter extends BasePresenter<AddAddressSuperView,AddAddressSuperModel>{
        public abstract void returnAddAddressChannelsRequst(UserAddressBean userAddressBean);
    }
}
