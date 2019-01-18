package com.rui.cn.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author zhangrl
 * @time 2019/1/17-15:22
 **/
@Data
public class User implements Serializable {


    public final static String CONTEXT_KEY_USERID = "x-user-id";

    private String userId;

    private String userName;

    private List<String> allowPermissionService;
}
