package com.rui.cn.feignclients;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.rui.cn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserFeignService userFeignService;

    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public void deleteById(Integer id) {
        userMapper.deleteById(11);
        userFeignService.deleteById(id);
    }
}
