package com.zumumu.imumu.ui.personcenter.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.personcenter.model.UserAddressBean;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/19.
 * 修改收货地址
 */

public interface ModifyAddressContract {

    interface ModifyAddressSuperModel extends BaseModel {
        Observable<String> loadModifyAddressChannels(UserAddressBean userAddressBean);
    }

    interface ModifyAddressSuperView extends BaseView {
        void returnModifyAddress(UserAddressBean userAddressBean);
        void returnModifyAddressError(String msg);
    }

    abstract class ModifyAddressSuperPresenter extends BasePresenter<ModifyAddressContract.ModifyAddressSuperView,ModifyAddressContract.ModifyAddressSuperModel> {
        public abstract void loadModifyAddressRequst(UserAddressBean userAddressBean);
    }

}
