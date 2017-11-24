package cn.qqtheme.framework.entity;

import java.util.ArrayList;

/**
 * 地市
 * <br/>
 */
public class City extends Area {
    private ArrayList<County> counties = new ArrayList<County>();

    public ArrayList<County> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<County> counties) {
        this.counties = counties;
    }

}