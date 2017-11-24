package com.zumumu.imumu.ui.personcenter.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class UserAddressBean implements Serializable{
    private String user_id;
    private String address_name;
    private String address_mobile;
    private String address_province;
    private String address_city;
    private String address_area;
    private String address_detailed;
    private String address_isDefault;

    public String getAddress_isDefault() {
        return address_isDefault;
    }

    public void setAddress_isDefault(String address_isDefault) {
        this.address_isDefault = address_isDefault;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getAddress_name() {
        return address_name;
    }

    public String getAddress_mobile() {
        return address_mobile;
    }

    public String getAddress_province() {
        return address_province;
    }

    public String getAddress_city() {
        return address_city;
    }

    public String getAddress_area() {
        return address_area;
    }

    public String getAddress_detailed() {
        return address_detailed;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public void setAddress_mobile(String address_mobile) {
        this.address_mobile = address_mobile;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public void setAddress_area(String address_area) {
        this.address_area = address_area;
    }

    public void setAddress_detailed(String address_detailed) {
        this.address_detailed = address_detailed;
    }
}
