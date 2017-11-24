package com.zumumu.imumu.ui.shop.model;

/**
 * Created by PC_p on 2017/1/19.
 * 商品详情中立即购买的数据模型
 */

public class BuyNowBean {
    private String userId;
    private String goodsId;
    private String goodsNum;
    private String goodsAttr;

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

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }
}
