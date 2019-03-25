package com.rui.cn.dao.order;

import com.rui.cn.domain.order.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by caibosi on 2018-07-25.
 */
public interface UserOrderDao extends JpaRepository<UserOrder, Integer> {
}