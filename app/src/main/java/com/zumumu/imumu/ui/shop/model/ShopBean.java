package com.zumumu.imumu.ui.shop.model;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class ShopBean {

    private String goods_id;
    private String goods_name;
    private String goods_play_img;
    private String goods_priceY;
    private String goods_price;
    private String goods_showType;
    private String goods_inventory;
    private String goods_sales;

    public String getGoods_play_img() {
        return goods_play_img;
    }

    public void setGoods_play_img(String goods_play_img) {
        this.goods_play_img = goods_play_img;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getGoods_priceY() {
        return goods_priceY;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public String getGoods_showType() {
        return goods_showType;
    }

    public String getGoods_inventory() {
        return goods_inventory;
    }

    public String getGoods_sales() {
        return goods_sales;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setGoods_priceY(String goods_priceY) {
        this.goods_priceY = goods_priceY;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public void setGoods_showType(String goods_showType) {
        this.goods_showType = goods_showType;
    }

    public void setGoods_inventory(String goods_inventory) {
        this.goods_inventory = goods_inventory;
    }

    public void setGoods_sales(String goods_sales) {
        this.goods_sales = goods_sales;
    }
}
