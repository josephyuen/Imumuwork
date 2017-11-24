package com.zumumu.imumu.ui.user.presenter;

import com.google.gson.Gson;
import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.user.contract.UserContract;
import com.zumumu.imumu.ui.user.model.LoginBean;
import com.zumumu.imumu.ui.user.model.UserBean;

/**
 * Created by PC_p on 2016/12/26.
 */

public class LoginPresenter extends UserContract.LoginSuperPresenter{

    @Override
    public void loadUserChannelsRequst(UserBean userBean) {
        mRxManage.add(mModel.loadLoginChannels(userBean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String loginMsg) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(loginMsg, LoginBean.class);
                String status = loginBean.getStatus()+"";
                if("0".equals(status)){
                  //String data = map.get("msg");
                    String userId = loginBean.getData().getUser_ID()+"";
                    mView.returnUserChannels(userId);

                }else{
                    String msg = loginBean.getMsg();
                    mView.returnUserChannelsError(msg);

                }

            }

            @Override
            protected void _onError(String message) {
                  mView.returnUserChannelsError(message);

            }
        }));
    }
}
