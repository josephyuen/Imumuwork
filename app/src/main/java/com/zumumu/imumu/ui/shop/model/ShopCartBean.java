package com.zumumu.imumu.ui.shop.model;

import java.util.List;

/**
 * Created by PC_p on 2017/1/14.
 */

public class ShopCartBean {



    /**
     * data : [{"cart_id":18,"goods_IsShipping":1,"goods_id":862,"goods_img":"/mumu/mumu_data/images/goods_images/NxPig/t1.jpg","goods_name":"宁乡花猪","goods_price":26940,"goods_priceY":29040,"goods_quantity":30,"user_id":4678},{"cart_id":13,"goods_IsShipping":0,"goods_id":879,"goods_img":"/mumu/mumu_data/images/goods_images/rice/li/t1.jpg","goods_name":"五常稻花香（礼盒装）","goods_price":414,"goods_priceY":450,"goods_quantity":3,"user_id":4678},{"cart_id":12,"goods_IsShipping":0,"goods_id":879,"goods_img":"/mumu/mumu_data/images/goods_images/rice/li/t1.jpg","goods_name":"五常稻花香（礼盒装）","goods_price":276,"goods_priceY":300,"goods_quantity":2,"user_id":4678},{"cart_id":11,"goods_IsShipping":0,"goods_id":879,"goods_img":"/mumu/mumu_data/images/goods_images/rice/li/t1.jpg","goods_name":"五常稻花香（礼盒装）","goods_price":276,"goods_priceY":300,"goods_quantity":2,"user_id":4678},{"cart_id":10,"goods_IsShipping":0,"goods_id":879,"goods_img":"/mumu/mumu_data/images/goods_images/rice/li/t1.jpg","goods_name":"五常稻花香（礼盒装）","goods_price":276,"goods_priceY":300,"goods_quantity":2,"user_id":4678},{"cart_id":9,"goods_IsShipping":0,"goods_id":879,"goods_img":"/mumu/mumu_data/images/goods_images/rice/li/t1.jpg","goods_name":"五常稻花香（礼盒装）","goods_price":276,"goods_priceY":276,"goods_quantity":2,"user_id":4678},{"cart_id":8,"goods_IsShipping":0,"goods_id":879,"goods_img":"/mumu/mumu_data/images/goods_images/rice/li/t1.jpg","goods_name":"五常稻花香（礼盒装）","goods_price":276,"goods_priceY":276,"goods_quantity":2,"user_id":4678},{"cart_id":7,"goods_IsShipping":0,"goods_id":828,"goods_img":"","goods_name":"大豆粕","goods_price":0,"goods_priceY":0,"goods_quantity":2,"user_id":4678},{"cart_id":6,"goods_IsShipping":1,"goods_id":887,"goods_img":"/mumu/mumu_data/images/goods_images/Nxpork/longjirou/t1.jpg","goods_name":"龙脊骨","goods_price":59.6,"goods_priceY":0,"goods_quantity":2,"user_id":4678}]
     * msg : 显示列表成功！
     * status : 0
     */
    private double totlePrice;

    public double getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(double totlePrice) {
        this.totlePrice = totlePrice;
    }

    private String msg;
    private int status;
    /**
     * cart_id : 18
     * goods_IsShipping : 1
     * goods_id : 862
     * goods_img : /mumu/mumu_data/images/goods_images/NxPig/t1.jpg
     * goods_name : 宁乡花猪
     * goods_price : 26940.0
     * goods_priceY : 29040.0
     * goods_quantity : 30
     * user_id : 4678
     */

    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int cart_id;
        private int goods_IsShipping;
        private int goods_id;
        private String goods_img;
        private String goods_name;
        private double goods_price;
        private double goods_priceY;
        private int goods_quantity;
        private int user_id;

        public boolean isItemChecked() {
            return itemChecked;
        }

        public void setItemChecked(boolean itemChecked) {
            this.itemChecked = itemChecked;
        }

        private boolean itemChecked;

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

        public int getGoods_IsShipping() {
            return goods_IsShipping;
        }

        public void setGoods_IsShipping(int goods_IsShipping) {
            this.goods_IsShipping = goods_IsShipping;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public double getGoods_priceY() {
            return goods_priceY;
        }

        public void setGoods_priceY(double goods_priceY) {
            this.goods_priceY = goods_priceY;
        }

        public int getGoods_quantity() {
            return goods_quantity;
        }

        public void setGoods_quantity(int goods_quantity) {
            this.goods_quantity = goods_quantity;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
