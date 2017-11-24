package com.zumumu.imumu.ui.home.contract;

import com.nzf.mvpframe.base.BaseModel;
import com.nzf.mvpframe.base.BasePresenter;
import com.nzf.mvpframe.base.BaseView;
import com.zumumu.imumu.ui.home.model.HomeBean;

import rx.Observable;

/**
 * Created by PC_p on 2016/12/28.
 */

public interface HomeFragContract {

    //首页接口
    interface HomeFragSuperModel extends BaseModel{
        Observable<String> loadHomeChannels();
    }

    interface HomeFragSuperView extends BaseView {
        void returnHomeChannels(HomeBean homeBean);
        void returnHomeChannelsError(String errorMsg);
    }

    abstract class HomeFragSuperPresenter extends BasePresenter<HomeFragSuperView,HomeFragSuperModel>{
        public abstract void loadHomeChannelsRequest();

    }




}
