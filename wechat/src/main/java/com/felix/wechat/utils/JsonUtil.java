package com.felix.wechat.utils;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 16:29
 */


import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

public class JsonUtil {

    public static String toJson(Object obj) {
        return WxMpGsonBuilder.create().toJson(obj);
    }

    public static <T> T jsonToBean(String result,Class<T> clazz){
        return WxMpGsonBuilder.create().fromJson(result,clazz);
    }
}
