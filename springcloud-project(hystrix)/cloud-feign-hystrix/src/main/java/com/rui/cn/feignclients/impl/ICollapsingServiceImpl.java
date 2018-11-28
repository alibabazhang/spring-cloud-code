package com.rui.cn.feignclients.impl;

import com.rui.cn.feignclients.ICollapsingService;
import org.springframework.stereotype.Component;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/19
 **/
@Component
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
