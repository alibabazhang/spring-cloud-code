package com.rui.cn.dynaicrouteconfig;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言定义模型
 *
 * @author zhangrl
 * @time 2019/1/16-16:53
 **/
@Data
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}