package com.zumumu.imumu.ui.home.contract;

import com.nzf.mvpframe.base.BasePresenter;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/5.
 */

public interface PcenterFragContract {
    interface PcenterFragSuperModel{
        Observable<String> loadPcenterChannels();
    }

    interface PcenterFragSuperView{
        void returnPcenterChannels(String msg);
        void returnPcenterChannelsError(String errorMsg);
    }

    abstract class PcenterFragSuperPresenter extends BasePresenter{
        public abstract void loadPcenterChannelsRequest();

    }
}
