package com.nzf.mvpframe.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则
 * Created by Administrator on 2016/12/27 0027.
 */

public class ClassPathResource {

    /**
     * 手机号码正则表达式
     * @param mobiles 手机号码
     * @return boolean
     */
    public static boolean isPhone(String mobiles){
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$");
        Matcher matcher = pattern.matcher(mobiles);
        return matcher.matches();
    }
}