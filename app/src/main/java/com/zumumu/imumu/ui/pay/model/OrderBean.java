package com.zumumu.imumu.ui.pay.model;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class OrderBean {
    private String userId;
    private int addressId;
    private int expressId;
    private String orderId;
    private String remark;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {

        return userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getExpressId() {
        return expressId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRemark() {
        return remark;
    }
}
