package com.zumumu.imumu.ui.shop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC_p on 2017/1/19.
 * 生成的订单bean
 */

public class GenerateIndentBean implements Serializable{
    @Override
    public String toString() {
        return "GenerateIndentBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", order_id=" + order_id +
                ", user_address=" + user_address +
                ", orderGoodsList=" + orderGoodsList +
                ", express=" + express +
                '}';
    }

    private int size;

    private int orderSize(){
        return size;
    }

    public int getSize() {
        return size;
    }

    /**
     * status : 0
     * msg : 订单生成成功
     * data : {"order_total":134.97,"order_total_z":96.97,"order_status":1,"order_creation_time":1484829885705,"order_type":1,"user_id":4678,"user_name":"从余","order_id":null,"goods_list":"[{\"goods_id\":\"887\",\"goods_name\":\"龙脊骨\",\"goods_num\":1,\"goods_play_img\":\"/mumu/mumu_data/images/goods_images/Nxpork/longjirou/t1.jpg\",\"goods_price\":29.8,\"goods_total\":26.22,\"goods_weight\":2500},{\"goods_id\":\"888\",\"goods_name\":\"带皮五花肉\",\"goods_num\":3,\"goods_play_img\":\"/mumu/mumu_data/images/goods_images/Nxpork/wuhuarou/t1.jpg\",\"goods_price\":26.8,\"goods_total\":70.75,\"goods_weight\":7500}]"}
     * orderGoodsList : [{"goods_id":"887","goods_name":"龙脊骨","goods_play_img":"/mumu/mumu_data/images/goods_images/Nxpork/longjirou/t1.jpg","goods_price":29.8,"goods_weight":2500,"goods_num":1,"goods_total":26.22},{"goods_id":"888","goods_name":"带皮五花肉","goods_play_img":"/mumu/mumu_data/images/goods_images/Nxpork/wuhuarou/t1.jpg","goods_price":26.8,"goods_weight":7500,"goods_num":3,"goods_total":70.75}]
     * order_id : 40
     * user_address : {"user_id":"4678","address_name":"'sss'","address_mobile":"15574819278","address_province":"'ssss'","address_city":"'www'","address_area":"'eee'","address_detailed":"'rrrr'","address_default":0,"address_id":1635}
     * express : [{"express_id":43,"express_name":"顺丰","is_default":1,"express_price":11,"default_price":3},{"express_id":44,"express_name":"自提","is_default":0,"express_price":0,"default_price":0}]
     */

    private int status;
    private String msg;
    /**
     * order_total : 134.97
     * order_total_z : 96.97
     * order_status : 1
     * order_creation_time : 1484829885705
     * order_type : 1
     * user_id : 4678
     * user_name : 从余
     * order_id : null
     * goods_list : [{"goods_id":"887","goods_name":"龙脊骨","goods_num":1,"goods_play_img":"/mumu/mumu_data/images/goods_images/Nxpork/longjirou/t1.jpg","goods_price":29.8,"goods_total":26.22,"goods_weight":2500},{"goods_id":"888","goods_name":"带皮五花肉","goods_num":3,"goods_play_img":"/mumu/mumu_data/images/goods_images/Nxpork/wuhuarou/t1.jpg","goods_price":26.8,"goods_total":70.75,"goods_weight":7500}]
     */

    private DataBean data;
    private int order_id;
    /**
     * user_id : 4678
     * address_name : 'sss'
     * address_mobile : 15574819278
     * address_province : 'ssss'
     * address_city : 'www'
     * address_area : 'eee'
     * address_detailed : 'rrrr'
     * address_default : 0
     * address_id : 1635
     */

    private UserAddressBean user_address;
    /**
     * goods_id : 887
     * goods_name : 龙脊骨
     * goods_play_img : /mumu/mumu_data/images/goods_images/Nxpork/longjirou/t1.jpg
     * goods_price : 29.8
     * goods_weight : 2500
     * goods_num : 1
     * goods_total : 26.22
     */

    private List<OrderGoodsListBean> orderGoodsList;
    /**
     * express_id : 43
     * express_name : 顺丰
     * is_default : 1
     * express_price : 11.0
     * default_price : 3.0
     */

    private List<ExpressBean> express;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public UserAddressBean getUser_address() {
        return user_address;
    }

    public void setUser_address(UserAddressBean user_address) {
        this.user_address = user_address;
    }

    public List<OrderGoodsListBean> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
        size = orderGoodsList.size();
        this.orderGoodsList = orderGoodsList;
    }

    public List<ExpressBean> getExpress() {
        return express;
    }

    public void setExpress(List<ExpressBean> express) {
        this.express = express;
    }

    public static class DataBean implements Serializable{
        @Override
        public String toString() {
            return "DataBean{" +
                    "order_total=" + order_total +
                    ", order_total_z=" + order_total_z +
                    ", order_status=" + order_status +
                    ", order_creation_time=" + order_creation_time +
                    ", order_type=" + order_type +
                    ", user_id=" + user_id +
                    ", user_name='" + user_name + '\'' +
                    ", order_id=" + order_id +
                    ", goods_list='" + goods_list + '\'' +
                    '}';
        }

        private double order_total;
        private double order_total_z;
        private int order_status;
        private long order_creation_time;
        private int order_type;
        private int user_id;
        private String user_name;
        private String order_id;
        private String goods_list;

        public double getOrder_total() {
            return order_total;
        }

        public void setOrder_total(double order_total) {
            this.order_total = order_total;
        }

        public double getOrder_total_z() {
            return order_total_z;
        }

        public void setOrder_total_z(double order_total_z) {
            this.order_total_z = order_total_z;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public long getOrder_creation_time() {
            return order_creation_time;
        }

        public void setOrder_creation_time(long order_creation_time) {
            this.order_creation_time = order_creation_time;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(String goods_list) {
            this.goods_list = goods_list;
        }
    }

    public static class UserAddressBean implements Serializable{
        @Override
        public String toString() {
            return "UserAddressBean{" +
                    "user_id='" + user_id + '\'' +
                    ", address_name='" + address_name + '\'' +
                    ", address_mobile='" + address_mobile + '\'' +
                    ", address_province='" + address_province + '\'' +
                    ", address_city='" + address_city + '\'' +
                    ", address_area='" + address_area + '\'' +
                    ", address_detailed='" + address_detailed + '\'' +
                    ", address_default=" + address_default +
                    ", address_id=" + address_id +
                    '}';
        }

        private String user_id;
        private String address_name;
        private String address_mobile;
        private String address_province;
        private String address_city;
        private String address_area;
        private String address_detailed;
        private int address_default;
        private int address_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public String getAddress_mobile() {
            return address_mobile;
        }

        public void setAddress_mobile(String address_mobile) {
            this.address_mobile = address_mobile;
        }

        public String getAddress_province() {
            return address_province;
        }

        public void setAddress_province(String address_province) {
            this.address_province = address_province;
        }

        public String getAddress_city() {
            return address_city;
        }

        public void setAddress_city(String address_city) {
            this.address_city = address_city;
        }

        public String getAddress_area() {
            return address_area;
        }

        public void setAddress_area(String address_area) {
            this.address_area = address_area;
        }

        public String getAddress_detailed() {
            return address_detailed;
        }

        public void setAddress_detailed(String address_detailed) {
            this.address_detailed = address_detailed;
        }

        public int getAddress_default() {
            return address_default;
        }

        public void setAddress_default(int address_default) {
            this.address_default = address_default;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }
    }

    public static class OrderGoodsListBean implements Serializable{
        @Override
        public String toString() {
            return "OrderGoodsListBean{" +
                    "goods_id='" + goods_id + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_play_img='" + goods_play_img + '\'' +
                    ", goods_price=" + goods_price +
                    ", goods_weight=" + goods_weight +
                    ", goods_num=" + goods_num +
                    ", goods_total=" + goods_total +
                    '}';
        }

        private String goods_id;
        private String goods_name;
        private String goods_play_img;
        private double goods_price;
        private double goods_vip_price;
        private int goods_weight;
        private int goods_num;
        private double goods_total;

        public double getGoods_vip_price() {
            return goods_vip_price;
        }

        public void setGoods_vip_price(double goods_vip_price) {
            this.goods_vip_price = goods_vip_price;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

        public int getGoods_weight() {
            return goods_weight;
        }

        public void setGoods_weight(int goods_weight) {
            this.goods_weight = goods_weight;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public double getGoods_total() {
            return goods_total;
        }

        public void setGoods_total(double goods_total) {
            this.goods_total = goods_total;
        }
    }

    public static class ExpressBean implements Serializable{
        @Override
        public String toString() {
            return "ExpressBean{" +
                    "express_id=" + express_id +
                    ", express_name='" + express_name + '\'' +
                    ", is_default=" + is_default +
                    ", express_price=" + express_price +
                    ", default_price=" + default_price +
                    '}';
        }

        private int express_id;
        private String express_name;
        private int is_default;
        private double express_price;
        private double default_price;

        public int getExpress_id() {
            return express_id;
        }

        public void setExpress_id(int express_id) {
            this.express_id = express_id;
        }

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public double getExpress_price() {
            return express_price;
        }

        public void setExpress_price(double express_price) {
            this.express_price = express_price;
        }

        public double getDefault_price() {
            return default_price;
        }

        public void setDefault_price(double default_price) {
            this.default_price = default_price;
        }
    }
}
