package com.rui.cn.controller;

import com.rui.c.TccParticipantController;
import com.rui.cn.dao.OrderDao;
import com.rui.cn.domain.OrderState;
import com.rui.cn.domain.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by caibosi on 2018-07-27.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends TccParticipantController<UserOrder> {

    @Autowired
    OrderDao orderDao;


    @Override
    public String getParticipantName() {
        return "order-service";
    }

    @Override
    public ResponseEntity executeTry(String txId, UserOrder body) {
        body.setTxId(txId);
        body.setState(OrderState.ORDERED);
        body.setExpireTime(LocalDateTime.now().plusMinutes(30));
        try{
            orderDao.save(body);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @Override
    public ResponseEntity executeCancel(String txId) {
        UserOrder userOrder = orderDao.findByTxId(txId);
        if (userOrder == null) {
//            return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        userOrder.setState(OrderState.CANCELED);
        orderDao.save(userOrder);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity executeConfirm(String txId) {
        UserOrder userOrder = orderDao.findByTxId(txId);
        if (userOrder == null) {
//            return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        userOrder.setState(OrderState.CONFIRMED);
        orderDao.save(userOrder);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}