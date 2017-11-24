package com.zumumu.imumu.ui.personcenter.presenter;

import com.nzf.mvpframe.baserx.RxSubscriber;
import com.nzf.mvpframe.utils.JsonUtils;
import com.zumumu.imumu.ui.personcenter.contract.AddressContract;
import com.zumumu.imumu.ui.personcenter.model.UserAddressBean;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class AddAddressPresenter extends AddressContract.AddAddressSuperPresenter {
    @Override
    public void returnAddAddressChannelsRequst(final UserAddressBean userAddressBean) {
        mRxManage.add(mModel.loadAddAddressChannels(userAddressBean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                String status = JsonUtils.getValue(s,"status");
                if ("0".equals(status)) {
                    mView.returnAddAddressChannels(userAddressBean);
                }else {
                    mView.returnAddAddressChannelsError("服务器访问异常。");
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnAddAddressChannelsError(message);
            }
        }));
    }
}
