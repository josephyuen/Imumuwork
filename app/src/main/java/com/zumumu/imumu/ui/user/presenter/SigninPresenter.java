package com.zumumu.imumu.ui.user.presenter;

import com.nzf.mvpframe.baserx.RxSubscriber;
import com.zumumu.imumu.ui.user.contract.UserContract;
import com.zumumu.imumu.ui.user.model.UserBean;
import com.zumumu.imumu.ui.user.resolve.JsonResolve;

import java.util.Map;

/**
 * Created by PC_p on 2016/12/26.
 */

public class SigninPresenter extends UserContract.SigninSuperPresenter{

    @Override
    public void loadSigninChannelsRequst(UserBean userBean) {
        mRxManage.add(mModel.loadSigninChannels(userBean).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    String data = map.get("data");
                    mView.returnSigninChannels(data);

                }else{
                    String msg = map.get("msg");
                    mView.returnSigninChannelsError(msg);
                }


//                String status = JsonUtils.getValue(s,"status");
//                if("0".equals(status)){
//                    String data = JsonUtils.getValue(s,"data");
//                    mView.returnSigninChannels(data);
//                }else{
//                    String msg = JsonUtils.getValue(s,"msg");
//                    mView.returnSigninChannelsError(msg);
//                }

            }

            @Override
            protected void _onError(String message) {
                  mView.returnSigninChannelsError(message);
            }
        }));


    }

    @Override
    public void loadVerifyCodeChannelsRequest(String phone) {
        mRxManage.add(mModel.loadVerfyCodeChannels(phone).subscribe(new RxSubscriber<String>(mContext,true) {
            @Override
            protected void _onNext(String s) {
                Map<String,String> map = JsonResolve.parseJson(s);
                if("0".equals(map.get("status"))){
                    String data = map.get("data");
                    mView.returnVerifyCodeChannels(data);

                }else{
                    String msg = map.get("msg");
                    mView.returnVerifyCodeChannelsError(msg);
                }


//                String status = getValue(s,"status");
//                if("0".equals(status)){
//                    String data = getValue(s,"data");
//                    mView.returnVerifyCodeChannels(data);
//                }else{
//                    String msg = getValue(s,"msg");
//                    mView.returnVerifyCodeChannelsError(msg);
//                }

            }

            @Override
            protected void _onError(String message) {
                mView.returnVerifyCodeChannelsError(message);
            }
        }));
    }
}
