package com.zumumu.imumu.ui.shop.model;

import com.zumumu.imumu.ui.shop.contract.ProductDetailContract;

import rx.Observable;

/**
 * Created by PC_p on 2017/1/5.
 */

public class ProductCommentModel implements ProductDetailContract.ProductCommentSuperModel{

    @Override
    public Observable<String> loadCommentsChannels(String s) {


        return null;
    }

}
