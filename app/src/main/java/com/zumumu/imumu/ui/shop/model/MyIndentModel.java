package com.zumumu.imumu.ui.shop.model;

import com.zumumu.imumu.ui.shop.contract.MyIndentContract;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/17.
 */

public class MyIndentModel implements MyIndentContract.MyIndentSuperModel{


    @Override
    public Observable<String> returnAllIndentChannels() {
        return null;


    }

    @Override
    public Observable<String> returnNotPayChannels() {
        return null;


    }

    @Override
    public Observable<String> returnNeedSendChannels() {
        return null;


    }

    @Override
    public Observable<String> returnNeedReceiptChannels() {
        return null;

    }

    @Override
    public Observable<String> returnNeedAssessChannels() {
        return null;
    }
}
