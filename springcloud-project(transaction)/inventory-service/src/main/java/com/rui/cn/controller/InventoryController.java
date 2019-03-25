package com.rui.cn.controller;

import com.rui.c.TccParticipantController;
import com.rui.cn.dao.FrozeRequestDao;
import com.rui.cn.dao.InventoryDao;
import com.rui.cn.domain.FrozeRequest;
import com.rui.cn.domain.Inventory;
import com.rui.cn.service.FrozeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Created by caibosi on 2018-07-27.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController extends TccParticipantController<FrozeRequest> {

    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    FrozeRequestDao frozeRequestDao;

    @Autowired
    FrozeService frozeService;

    @Override
    public String getParticipantName() {
        return "inventory-service";
    }

    @Override
    public ResponseEntity executeTry(String txId, FrozeRequest body) {
        Inventory inventory = inventoryDao.findByProductCode(body.getProductCode());
      /*  if (inventory == null) {
            return ResponseEntity.notFound().build();
        }
        if (inventory.getLeftNum() < body.getFrozenNum()) {
            return ResponseEntity.notFound().build();
        }*/
        body.setTxId(txId);
        try {
            frozeRequestDao.save(body);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @Override
    public ResponseEntity executeCancel(String txId) {
        Optional<FrozeRequest> optional = frozeRequestDao.findById(txId);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        FrozeRequest frozeRequest = optional.get();
        Inventory inventory = inventoryDao.findByProductCode(frozeRequest.getProductCode());
        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }
        frozeService.cancel(frozeRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity executeConfirm(String txId) {
//        try {
//            TimeUnit.MINUTES.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Optional<FrozeRequest> optional = frozeRequestDao.findById(txId);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        FrozeRequest frozeRequest = optional.get();
        Inventory inventory = inventoryDao.findByProductCode(frozeRequest.getProductCode());
        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }
        frozeService.confirm(frozeRequest, inventory);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
