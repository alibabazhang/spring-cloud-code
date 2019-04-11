package com.rui.cn.service;


/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-14 17:31
 **/
public class ExceptionCast {

    public static void cast(String resultCode){
        throw new RuntimeException(resultCode);
    }
}
