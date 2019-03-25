package com.rui.cn.dao;

import com.rui.cn.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by caibosi on 2018-07-27.
 */
public interface InventoryDao extends JpaRepository<Inventory, Integer> {
    Inventory findByProductCode(String productCode);
}