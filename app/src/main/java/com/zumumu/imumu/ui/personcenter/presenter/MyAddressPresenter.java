package com.zumumu.imumu.ui.personcenter.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.personcenter.contract.MyAddressContract;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;
import com.zumumu.imumu.ui.user.resolve.JsonResolve;

import java.util.Map;

/**
 * Created by PC_p on 2017/1/17.
 */

public class MyAddressPresenter extends MyAddressContract.MyAddressSuperPresenter{
    @Override
    public void loadMyAddressChannelsRequst(String s) {
        mRxManage.add(mModel.loadMyAddressChannels(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    MyAddressBean myAddressBean = gson.fromJson(s, MyAddressBean.class);
                    mView.returnMyAddressChannels(myAddressBean);
                }else{
                    String msg = map.get("msg");
                    mView.returnMyAddressChannelsError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnMyAddressChannelsError(message);

            }
        }));

    }

    @Override
    public void loadDeleteChannelsRequst(String s) {
        mRxManage.add(mModel.loadDeleteChannels(s).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                 mView.returnDeleteChannels(s);
            }

            @Override
            protected void _onError(String message) {
                mView.returnDeleteChannelsError(message);
            }
        }));
    }
}
