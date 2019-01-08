package com.rui.cn.service;

import com.rui.cn.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/14
 **/
public interface UserService {
    String adduser(User user, HttpServletRequest request);

    String updateUser(User user);
}
