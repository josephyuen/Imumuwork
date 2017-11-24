package com.zumumu.imumu.ui.home.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.home.contract.HomeFragContract;
import com.zumumu.imumu.ui.home.model.HomeBean;
import com.zumumu.imumu.ui.user.resolve.JsonResolve;

import java.util.Map;

/**
 * Created by PC_p on 2016/12/28.
 */

public class HomePresenter extends HomeFragContract.HomeFragSuperPresenter{

    @Override
    public void loadHomeChannelsRequest() {
        mRxManage.add(mModel.loadHomeChannels().subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    Gson gson = new Gson();
                    HomeBean homeBean = gson.fromJson(s, HomeBean.class);
                    mView.returnHomeChannels(homeBean);
                }else{
                    String msg = map.get("msg");
                    mView.returnHomeChannelsError(msg);
                }
            }

            @Override
            protected void _onError(String message) {
                mView.returnHomeChannelsError(message);

            }
        }));
    }
}
