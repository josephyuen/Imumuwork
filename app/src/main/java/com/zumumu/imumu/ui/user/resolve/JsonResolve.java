package com.zumumu.imumu.ui.user.resolve;

import com.nzf.mvpframe.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static com.nzf.mvpframe.utils.JsonUtils.getValue;

/**
 * Created by PC_p on 2016/12/30.
 * 解析登录,注册,忘记密码
 */

public class JsonResolve {

    public static Map<String,String> parseJson(String json){
        String status = JsonUtils.getValue(json,"status");
        String msg = getValue(json,"msg");
        String data = getValue(json,"data");
        Map<String,String > map = new HashMap<>();
        if("0".equals(status)){     //成功时的状态
            map.put("status","0");
            map.put("msg",msg);
            map.put("data",data);
            return map;
        }else{                    //未成功
            map.put("status","");
            map.put("msg",msg);
            map.put("data",data);
            return map;
        }

    }
}
