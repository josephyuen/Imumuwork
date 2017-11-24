package com.zumumu.imumu.ui.personcenter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC_p on 2017/1/17.
 */

public class MyAddressBean {


    /**
     * data : [{"address_area":"石门县","address_city":"常德市","address_default":1,"address_detailed":"石门一中","address_id":1590,"address_mobile":"15574819278","address_name":"从余","address_province":"湖南省","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1607,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1608,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1609,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1615,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1616,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1617,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1619,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1621,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1624,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1625,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1626,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1627,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1628,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1629,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1630,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1631,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1632,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1633,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1634,"address_mobile":"15574819278","address_name":"'sss'","user_id":"4678"},{"address_area":"'eee'","address_city":"'www'","address_default":1,"address_detailed":"'rrrr'","address_id":1635,"address_mobile":"15574819278","address_name":"'sss'","address_province":"'ssss'","user_id":"4678"}]
     * msg : 发送地址列表成功
     * status : 0
     */

    private String msg;
    private int status;
    /**
     * address_area : 石门县
     * address_city : 常德市
     * address_default : 1
     * address_detailed : 石门一中
     * address_id : 1590
     * address_mobile : 15574819278
     * address_name : 从余
     * address_province : 湖南省
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

    public static class DataBean implements Serializable{
        private String address_area;
        private String address_city;
        private int address_default;
        private String address_detailed;
        private int address_id;
        private String address_mobile;
        private String address_name;
        private String address_province;
        private String user_id;

        public String getAddress_area() {
            return address_area;
        }

        public void setAddress_area(String address_area) {
            this.address_area = address_area;
        }

        public String getAddress_city() {
            return address_city;
        }

        public void setAddress_city(String address_city) {
            this.address_city = address_city;
        }

        public int getAddress_default() {
            return address_default;
        }

        public void setAddress_default(int address_default) {
            this.address_default = address_default;
        }

        public String getAddress_detailed() {
            return address_detailed;
        }

        public void setAddress_detailed(String address_detailed) {
            this.address_detailed = address_detailed;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getAddress_mobile() {
            return address_mobile;
        }

        public void setAddress_mobile(String address_mobile) {
            this.address_mobile = address_mobile;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public String getAddress_province() {
            return address_province;
        }

        public void setAddress_province(String address_province) {
            this.address_province = address_province;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
