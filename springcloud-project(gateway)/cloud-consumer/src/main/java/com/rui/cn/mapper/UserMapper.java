package com.rui.cn.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteById(Integer id);
}
