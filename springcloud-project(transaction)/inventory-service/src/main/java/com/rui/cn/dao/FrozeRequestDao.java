package com.rui.cn.dao;


import com.rui.cn.domain.FrozeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by caibosi on 2018-07-27.
 */
public interface FrozeRequestDao extends JpaRepository<FrozeRequest, String> {
}