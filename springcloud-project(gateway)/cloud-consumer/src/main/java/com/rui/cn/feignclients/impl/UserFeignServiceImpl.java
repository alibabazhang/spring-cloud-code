package com.rui.cn.feignclients.impl;

import com.rui.cn.entity.User;
import com.rui.cn.feignclients.UserFeignService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/14
 **/
@Service
public class UserFeignServiceImpl implements UserFeignService {
    @Override
    public String addUser(User user) {
        return "This user is locked !!";
    }

    @Override
    public String updateUser(User user) {
        return null;
    }

    @Override
    public String fileUpload(MultipartFile file) {
        return null;
    }
}
