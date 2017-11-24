package com.zumumu.imumu.ui.user.presenter;

import android.text.TextUtils;

import com.nzf.mvpframe.baserx.RxSubscriber;
import com.nzf.mvpframe.utils.JsonUtils;
import com.zumumu.imumu.ui.user.activity.ForgetActivity;
import com.zumumu.imumu.ui.user.contract.UserContract;
import com.zumumu.imumu.ui.user.model.UserBean;
import com.zumumu.imumu.utils.CheckPhoneUtil;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class ForgetPresenter extends UserContract.ForgetSuperPresenter {
    @Override
    public void loadForgetVerifyChannelsRequst(String phone) {
        if (TextUtils.isEmpty(phone)) {
            mView.returnForgetChannelsError(ForgetActivity.EMPTY_PHONE);
        }else if (!CheckPhoneUtil.check(phone)){
            mView.returnForgetChannelsError(ForgetActivity.PHONE_ERROR);
        } else {
            mRxManage.add(mModel.loadForgetVerifyChannels(phone).subscribe(new RxSubscriber<String>(mContext,false) {
                @Override
                protected void _onNext(String s) {
                    String status = JsonUtils.getValue(s,"status");
                    if("0".equals(status)){
                        String data = JsonUtils.getValue(s,"data");
                        mView.returnForgetVerifyCodeChannels(data);
                    }else if ("1".equals(status)){
                        mView.returnForgetChannelsError(ForgetActivity.PHONE_ERROR_MIS);
                    }else{
                        String msg = JsonUtils.getValue(s,"msg");
                        mView.returnForgetChannelsError(msg);
                    }
                }

                @Override
                protected void _onError(String message) {
                    mView.returnForgetChannelsError(message);
                }
            }));
        }
    }

    /**
     * 忘记密码确认修改
     * @param phone 电话号码
     * @param verify 验证码
     * @param pwd 密码
     * @param pwdAgin 确认密码
     */
    @Override
    public void loadForgetChannelsRequst(String phone,String verify,String pwd,String pwdAgin) {
        UserBean userBean = null;
        if (TextUtils.isEmpty(phone)) {
            mView.returnForgetChannelsError(ForgetActivity.EMPTY_PHONE);
        }else if (TextUtils.isEmpty(verify)){
            mView.returnForgetChannelsError(ForgetActivity.EMPTY_VERIFYCODE);
        }else if (TextUtils.isEmpty(pwd)){
            mView.returnForgetChannelsError(ForgetActivity.EMPTY_PWD);
        }else if (!pwd.equals(pwdAgin)){
            mView.returnForgetChannelsError(ForgetActivity.PWD_VERIFYERROR);
        }else if (!CheckPhoneUtil.check(phone)){
            mView.returnForgetChannelsError(ForgetActivity.PHONE_ERROR);
        } else{
            userBean = new UserBean();
            userBean.setPhone(phone);
            userBean.setVerifycode(verify);
            userBean.setPassWord(pwd);
            mRxManage.add(mModel.loadForgetChannels(userBean).subscribe(new RxSubscriber<String>(mContext,true) {
                @Override
                protected void _onNext(String s) {
                    String status = JsonUtils.getValue(s,"status");
                    if("0".equals(status)){
                        String data = JsonUtils.getValue(s,"msg");
                        mView.returnForgetChannels(data);
                    }else if ("1".equals(status)){
                        mView.returnForgetChannelsError(ForgetActivity.PHONE_ERROR_MIS);
                    }else if("2".equals(status)){
                        mView.returnForgetChannelsError(ForgetActivity.VERIFYCODE_ERROR);
                    }else {
                        String msg = JsonUtils.getValue(s,"msg");
                        mView.returnForgetChannelsError(msg);
                    }

                }

                @Override
                protected void _onError(String message) {
                    mView.returnForgetChannelsError(message);
                }
            }));
        }
    }
}
