package com.zumumu.imumu.ui.user.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/10.
 * 个人中心用户个人资料
 */

public interface PersonInfoContract {

    interface PInfoSuperModel extends BaseModel {
        Observable<String> loadPInfoChannels();
    }

    interface PInfoSuperView extends BaseView {
        void returnPInfoChannels(String pInfoMsg);

        void returnPInfoChannelsError(String msg);
    }

    abstract static class PInfoSuperPresenter extends BasePresenter<PersonInfoContract.PInfoSuperView,PersonInfoContract.PInfoSuperModel> {
        public abstract void loadPInfoChannelsRequst();
    }

}
