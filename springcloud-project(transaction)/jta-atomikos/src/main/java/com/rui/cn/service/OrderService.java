package com.rui.cn.service;

import com.rui.cn.dao.log.EventLogDao;
import com.rui.cn.dao.order.UserOrderDao;
import com.rui.cn.domain.log.EventLog;
import com.rui.cn.domain.order.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by caibosi on 2018-07-25.
 */
@Component
public class OrderService {

    @Autowired
    UserOrderDao userOrderDao;

    @Autowired
    EventLogDao eventLogDao;

    @Transactional(rollbackOn = Exception.class)
    public void newOrder(String userId,String productCode,int quantity){
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId);
        userOrder.setProductCode(productCode);
        userOrder.setQuantity(quantity);
        userOrderDao.save(userOrder);

        EventLog eventLog = new EventLog();
        eventLog.setOperation("new order");
        eventLog.setOperator(userId);
        eventLogDao.save(eventLog);
        List<EventLog> all = eventLogDao.findAll();
        all.forEach(x -> System.out.println("添加后测试------------》"+x.getOperation()));
        eventLogDao.deleteAll();
        int i=1/0;
    }

    @Transactional(rollbackOn = Exception.class)
    public void newOrderRollback(String userId,String productCode,int quantity){
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId);
        userOrder.setProductCode(productCode);
        userOrder.setQuantity(quantity);
        userOrderDao.save(userOrder);

        EventLog eventLog = new EventLog();
        eventLog.setOperation("new order");
        eventLog.setOperator(userId);
        eventLogDao.save(eventLog);

        throw new RuntimeException("test jta rollback");
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(int i) {
        userOrderDao.deleteAll();
        eventLogDao.deleteAll();
    }
}