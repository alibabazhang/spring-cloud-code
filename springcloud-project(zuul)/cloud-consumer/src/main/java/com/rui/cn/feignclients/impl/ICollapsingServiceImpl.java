package com.rui.cn.feignclients.impl;

import com.rui.cn.feignclients.ICollapsingService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/19
 **/
@Service
public class ICollapsingServiceImpl implements ICollapsingService {
    @Override
    public String collapsing(Integer id) {
        return null;
    }

    @Override
    public String collapsingSyn(Integer id) {
        return null;
    }

    @Override
    public String collapsingGlobal(Integer id) {
        return null;
    }
}
