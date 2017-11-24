package com.zumumu.imumu.ui.shop.model;

/**
 * Created by PC_p on 2017/1/13.
 */

public class AddToCartBean {
    private String userId;
    private String goodsId;
    private String num;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
