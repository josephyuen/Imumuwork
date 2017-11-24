package com.zumumu.imumu.ui.user.model;

import com.zumumu.imumu.ui.user.contract.PersonInfoContract;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/10.
 */

public class PInfoModel implements PersonInfoContract.PInfoSuperModel{
    @Override
    public Observable<String> loadPInfoChannels() {


        return null;
    }
}
