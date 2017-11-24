package cn.qqtheme.framework.entity;

import java.util.ArrayList;

/**
 * 省份
 * <br/>
 */
public class Province extends Area {
    private ArrayList<City> cities = new ArrayList<City>();

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

}