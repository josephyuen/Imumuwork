package com.zumumu.imumu.ui.shop.model;

import java.util.List;

/**
 * Created by PC_p on 2017/1/4.
 * 商品详情数据bean
 */

public class ProductDetailBean {

    private int curAttr = 0;

    public int getCurAttr() {
        return curAttr;
    }

    public void setCurAttr(int curAttr) {
        this.curAttr = curAttr;
    }

    @Override
    public String toString() {
        return "ProductDetailBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", attrList=" + attrList +
                ", data=" + data +
                '}';
    }
    /**
     * attrList : [{"attr_Price":"211.62","attr_Value":"包装：三盒包装，每盒30枚，90枚起购","goods_Attr_id":1104,"goods_id":861},{"attr_Price":"282.62","attr_Value":"包装：四盒包装，每盒30枚，共120枚","goods_Attr_id":1105,"goods_id":861},{"attr_Price":"354.62","attr_Value":"包装：五盒包装，每盒30枚，150枚起购","goods_Attr_id":1106,"goods_id":861}]
     * data : [{"description_att":[""],"goods_BriefDescription":"土鸡蛋由农户散养，吃五谷杂粮且白天自由觅食，鸡不受外力因素自然成长，鸡放养过程中没有使用任何药物。","goods_Description":"","goods_IsHot":0,"goods_IsNew":0,"goods_IsRecommend":0,"goods_IsShippingFree":0,"goods_id":861,"goods_inventory":9694,"goods_name":"土鸡蛋","goods_play_img":"/mumu/mumu_data/images/goods_images/egg/t1.jpg,/mumu/mumu_data/images/goods_images/egg/t2.jpg,/mumu/mumu_data/images/goods_images/egg/t3.jpg,/mumu/mumu_data/images/goods_images/egg/t4.jpg","goods_price":2.38,"goods_priceY":3,"goods_sales":307,"highPrice":"2.33","play_img_arr":["/mumu/mumu_data/images/goods_images/egg/t1.jpg","/mumu/mumu_data/images/goods_images/egg/t2.jpg","/mumu/mumu_data/images/goods_images/egg/t3.jpg","/mumu/mumu_data/images/goods_images/egg/t4.jpg"],"vipPrice":"2.09"}]
     * msg : 获取商品详情成功！
     * status : 0
     */

    private String msg;
    private int status;
    /**
     * attr_Price : 211.62
     * attr_Value : 包装：三盒包装，每盒30枚，90枚起购
     * goods_Attr_id : 1104
     * goods_id : 861
     */

    private List<AttrListBean> attrList;
    /**
     * description_att : [""]
     * goods_BriefDescription : 土鸡蛋由农户散养，吃五谷杂粮且白天自由觅食，鸡不受外力因素自然成长，鸡放养过程中没有使用任何药物。
     * goods_Description :
     * goods_IsHot : 0
     * goods_IsNew : 0
     * goods_IsRecommend : 0
     * goods_IsShippingFree : 0
     * goods_id : 861
     * goods_inventory : 9694
     * goods_name : 土鸡蛋
     * goods_play_img : /mumu/mumu_data/images/goods_images/egg/t1.jpg,/mumu/mumu_data/images/goods_images/egg/t2.jpg,/mumu/mumu_data/images/goods_images/egg/t3.jpg,/mumu/mumu_data/images/goods_images/egg/t4.jpg
     * goods_price : 2.38
     * goods_priceY : 3.0
     * goods_sales : 307
     * highPrice : 2.33
     * play_img_arr : ["/mumu/mumu_data/images/goods_images/egg/t1.jpg","/mumu/mumu_data/images/goods_images/egg/t2.jpg","/mumu/mumu_data/images/goods_images/egg/t3.jpg","/mumu/mumu_data/images/goods_images/egg/t4.jpg"]
     * vipPrice : 2.09
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

    public List<AttrListBean> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<AttrListBean> attrList) {
        this.attrList = attrList;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class AttrListBean {
        @Override
        public String toString() {
            return "AttrListBean{" +
                    "attr_Price='" + attr_Price + '\'' +
                    ", attr_Value='" + attr_Value + '\'' +
                    ", goods_Attr_id=" + goods_Attr_id +
                    ", goods_id=" + goods_id +
                    '}';
        }

        private String attr_Price;
        private String attr_Value;
        private int goods_Attr_id;
        private int goods_id;

        public String getAttr_Price() {
            return attr_Price;
        }

        public void setAttr_Price(String attr_Price) {
            this.attr_Price = attr_Price;
        }

        public String getAttr_Value() {
            return attr_Value;
        }

        public void setAttr_Value(String attr_Value) {
            this.attr_Value = attr_Value;
        }

        public int getGoods_Attr_id() {
            return goods_Attr_id;
        }

        public void setGoods_Attr_id(int goods_Attr_id) {
            this.goods_Attr_id = goods_Attr_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "goods_BriefDescription='" + goods_BriefDescription + '\'' +
                    ", goods_Description='" + goods_Description + '\'' +
                    ", goods_IsHot=" + goods_IsHot +
                    ", goods_IsNew=" + goods_IsNew +
                    ", goods_IsRecommend=" + goods_IsRecommend +
                    ", goods_IsShippingFree=" + goods_IsShippingFree +
                    ", goods_id=" + goods_id +
                    ", goods_inventory=" + goods_inventory +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_play_img='" + goods_play_img + '\'' +
                    ", goods_price=" + goods_price +
                    ", goods_priceY=" + goods_priceY +
                    ", goods_sales=" + goods_sales +
                    ", highPrice='" + highPrice + '\'' +
                    ", vipPrice='" + vipPrice + '\'' +
                    ", description_att=" + description_att +
                    ", play_img_arr=" + play_img_arr +
                    '}';
        }

        private String goods_BriefDescription;
        private String goods_Description;
        private int goods_IsHot;
        private int goods_IsNew;
        private int goods_IsRecommend;
        private int goods_IsShippingFree;
        private int goods_id;
        private int goods_inventory;
        private String goods_name;
        private String goods_play_img;
        private double goods_price;
        private double goods_priceY;
        private int goods_sales;
        private String highPrice;
        private String vipPrice;
        private List<String> description_att;
        private List<String> play_img_arr;
        private int goodsNum = 1;

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoods_BriefDescription() {
            return goods_BriefDescription;
        }

        public void setGoods_BriefDescription(String goods_BriefDescription) {
            this.goods_BriefDescription = goods_BriefDescription;
        }

        public String getGoods_Description() {
            return goods_Description;
        }

        public void setGoods_Description(String goods_Description) {
            this.goods_Description = goods_Description;
        }

        public int getGoods_IsHot() {
            return goods_IsHot;
        }

        public void setGoods_IsHot(int goods_IsHot) {
            this.goods_IsHot = goods_IsHot;
        }

        public int getGoods_IsNew() {
            return goods_IsNew;
        }

        public void setGoods_IsNew(int goods_IsNew) {
            this.goods_IsNew = goods_IsNew;
        }

        public int getGoods_IsRecommend() {
            return goods_IsRecommend;
        }

        public void setGoods_IsRecommend(int goods_IsRecommend) {
            this.goods_IsRecommend = goods_IsRecommend;
        }

        public int getGoods_IsShippingFree() {
            return goods_IsShippingFree;
        }

        public void setGoods_IsShippingFree(int goods_IsShippingFree) {
            this.goods_IsShippingFree = goods_IsShippingFree;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_inventory() {
            return goods_inventory;
        }

        public void setGoods_inventory(int goods_inventory) {
            this.goods_inventory = goods_inventory;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_play_img() {
            return goods_play_img;
        }

        public void setGoods_play_img(String goods_play_img) {
            this.goods_play_img = goods_play_img;
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

        public int getGoods_sales() {
            return goods_sales;
        }

        public void setGoods_sales(int goods_sales) {
            this.goods_sales = goods_sales;
        }

        public String getHighPrice() {
            return highPrice;
        }

        public void setHighPrice(String highPrice) {
            this.highPrice = highPrice;
        }

        public String getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(String vipPrice) {
            this.vipPrice = vipPrice;
        }

        public List<String> getDescription_att() {
            return description_att;
        }

        public void setDescription_att(List<String> description_att) {
            this.description_att = description_att;
        }

        public List<String> getPlay_img_arr() {
            return play_img_arr;
        }

        public void setPlay_img_arr(List<String> play_img_arr) {
            this.play_img_arr = play_img_arr;
        }
    }
}
