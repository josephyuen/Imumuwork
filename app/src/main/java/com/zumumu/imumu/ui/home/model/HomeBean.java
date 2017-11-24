package com.zumumu.imumu.ui.home.model;

import java.util.List;

/**
 * Created by PC_p on 2017/1/12.
 * 首页的数据bean
 */

public class HomeBean {

    /**
     * data : [{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":827,"goods_inventory":9854,"goods_name":"黄牛","goods_play_img":"/mumu/mumu_data/images/goods_images/cattle/t1.jpg","goods_price":5188,"goods_priceY":5300,"goods_sales":147,"goods_vip_private":["高级会员价：5084.24","VIP会员价4565.44"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":835,"goods_inventory":9788,"goods_name":"黑山羊","goods_play_img":"/mumu/mumu_data/images/goods_images/black_goat/t1.jpg","goods_price":980,"goods_priceY":1000,"goods_sales":212,"goods_vip_private":["高级会员价：960.4","VIP会员价862.4"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":862,"goods_inventory":9709,"goods_name":"宁乡花猪","goods_play_img":"/mumu/mumu_data/images/goods_images/NxPig/t1.jpg","goods_price":898,"goods_priceY":968,"goods_sales":293,"goods_vip_private":["高级会员价：880.04","VIP会员价790.24"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":876,"goods_inventory":9997,"goods_name":"鹅","goods_play_img":"/mumu/mumu_data/images/goods_images/goose/t1.jpg","goods_price":251,"goods_priceY":290,"goods_sales":3,"goods_vip_private":["高级会员价：245.98","VIP会员价220.88"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":838,"goods_inventory":9874,"goods_name":"土老母鸡","goods_play_img":"/mumu/mumu_data/images/goods_images/chicken/t1.jpg","goods_price":198,"goods_priceY":211,"goods_sales":126,"goods_vip_private":["高级会员价：194.04","VIP会员价174.24"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":875,"goods_inventory":9999,"goods_name":"母洋鸭","goods_play_img":"/mumu/mumu_data/images/goods_images/Yduck/t1.jpg","goods_price":179,"goods_priceY":188,"goods_sales":1,"goods_vip_private":["高级会员价：175.42","VIP会员价157.52"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":839,"goods_inventory":9850,"goods_name":"土水鸭","goods_play_img":"/mumu/mumu_data/images/goods_images/Tduck/t1.jpg","goods_price":112,"goods_priceY":120,"goods_sales":150,"goods_vip_private":["高级会员价：109.75999999999999","VIP会员价98.56"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":891,"goods_inventory":10000,"goods_name":"腱子肉","goods_play_img":"/mumu/mumu_data/images/goods_images/Nxpork/jianzirou/t1.jpg","goods_price":65.8,"goods_priceY":69.8,"goods_sales":0,"goods_vip_private":["高级会员价：64.484","VIP会员价57.903999999999996"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":892,"goods_inventory":10000,"goods_name":"里脊肉","goods_play_img":"/mumu/mumu_data/images/goods_images/Nxpork/lijirou/t1.jpg","goods_price":52.8,"goods_priceY":55.8,"goods_sales":0,"goods_vip_private":["高级会员价：51.744","VIP会员价46.464"],"id":1},{"carousel_img":"/mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png","carousel_img_arr":["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"],"carousel_url":"","goods_id":861,"goods_inventory":9694,"goods_name":"土鸡蛋","goods_play_img":"/mumu/mumu_data/images/goods_images/egg/t1.jpg","goods_price":2.38,"goods_priceY":3,"goods_sales":307,"goods_vip_private":["高级会员价：2.3324","VIP会员价2.0944"],"id":1}]
     * like_goods : []
     * msg : 发送图片成功！
     * status : 0
     */

    private String msg;
    private int status;
    /**
     * carousel_img : /mumu/mumu_data/images/home_images/t1.png,/mumu/mumu_data/images/home_images/t2.png
     * carousel_img_arr : ["/mumu/mumu_data/images/home_images/t1.png","/mumu/mumu_data/images/home_images/t2.png"]
     * carousel_url :
     * goods_id : 827
     * goods_inventory : 9854
     * goods_name : 黄牛
     * goods_play_img : /mumu/mumu_data/images/goods_images/cattle/t1.jpg
     * goods_price : 5188.0
     * goods_priceY : 5300.0
     * goods_sales : 147
     * goods_vip_private : ["高级会员价：5084.24","VIP会员价4565.44"]
     * id : 1
     */

    private List<DataBean> data;
    private List<?> like_goods;

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

    public List<?> getLike_goods() {
        return like_goods;
    }

    public void setLike_goods(List<?> like_goods) {
        this.like_goods = like_goods;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", like_goods=" + like_goods +
                '}';
    }

    public static class DataBean {
        private String carousel_img;
        private String carousel_url;
        private int goods_id;
        private int goods_inventory;
        private String goods_name;
        private String goods_play_img;
        private double goods_price;
        private double goods_priceY;
        private int goods_sales;
        private int id;
        private List<String> carousel_img_arr;
        private List<String> goods_vip_private;

        public String getCarousel_img() {
            return carousel_img;
        }

        public void setCarousel_img(String carousel_img) {
            this.carousel_img = carousel_img;
        }

        public String getCarousel_url() {
            return carousel_url;
        }

        public void setCarousel_url(String carousel_url) {
            this.carousel_url = carousel_url;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getCarousel_img_arr() {
            return carousel_img_arr;
        }

        public void setCarousel_img_arr(List<String> carousel_img_arr) {
            this.carousel_img_arr = carousel_img_arr;
        }

        public List<String> getGoods_vip_private() {
            return goods_vip_private;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "carousel_img='" + carousel_img + '\'' +
                    ", carousel_url='" + carousel_url + '\'' +
                    ", goods_id=" + goods_id +
                    ", goods_inventory=" + goods_inventory +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_play_img='" + goods_play_img + '\'' +
                    ", goods_price=" + goods_price +
                    ", goods_priceY=" + goods_priceY +
                    ", goods_sales=" + goods_sales +
                    ", id=" + id +
                    ", carousel_img_arr=" + carousel_img_arr +
                    ", goods_vip_private=" + goods_vip_private +
                    '}';
        }

        public void setGoods_vip_private(List<String> goods_vip_private) {
            this.goods_vip_private = goods_vip_private;
        }
    }
}
