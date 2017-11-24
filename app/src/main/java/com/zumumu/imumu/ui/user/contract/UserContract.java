package com.zumumu.imumu.ui.user.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.user.model.UserBean;

import rx.Observable;

/**
 * Created by PC_p on 2016/12/26.
 */

public interface UserContract {

    //登陆接口
    interface LoginSuperModel extends BaseModel {
        Observable<String> loadLoginChannels(UserBean userBean);
    }

    interface LoginSuperView extends BaseView {
        void returnUserChannels(String loginMsg);

        void returnUserChannelsError(String msg);
    }

    abstract static class LoginSuperPresenter extends BasePresenter<LoginSuperView,LoginSuperModel> {
        public abstract void loadUserChannelsRequst(UserBean userBean);
    }

    //注册接口
    interface SigninSuperModel extends BaseModel{
        Observable<String> loadSigninChannels(UserBean userBean);
        Observable<String> loadVerfyCodeChannels(String phone);
    }

    interface SigninSuperView extends BaseView{
        void returnSigninChannels(String SigninMsg);
        void returnVerifyCodeChannels(String msg);
        void returnVerifyCodeChannelsError(String erroMsg);
        void returnSigninChannelsError(String msg);
    }

    abstract static class SigninSuperPresenter extends BasePresenter<SigninSuperView,SigninSuperModel>{
        public abstract void loadSigninChannelsRequst(UserBean userBean);
        public abstract void loadVerifyCodeChannelsRequest(String phone);

    }

    //找回密码接口
    interface ForgetSuperModel extends BaseModel{
        Observable<String> loadForgetVerifyChannels(String phone);
        Observable<String> loadForgetChannels(UserBean userBean);
    }

    interface ForgetSuperView extends BaseView{
        void returnForgetChannels(String msg);
        void returnForgetVerifyCodeChannels(String msg);

        void returnForgetChannelsError(String msg);
    }

    abstract static class ForgetSuperPresenter extends BasePresenter<ForgetSuperView,ForgetSuperModel>{
        public abstract void loadForgetVerifyChannelsRequst(String phone);
        public abstract void loadForgetChannelsRequst(String phone,String verify,String pwd,String pwdAgin);
    }
}
