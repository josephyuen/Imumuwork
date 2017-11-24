package com.zumumu.imumu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PC_p on 2016/12/27.
 * 检查手机号格式
 */

public class CheckPhoneUtil {
    public static boolean check(String phonenum){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phonenum);
        return m.matches();
    }
}
