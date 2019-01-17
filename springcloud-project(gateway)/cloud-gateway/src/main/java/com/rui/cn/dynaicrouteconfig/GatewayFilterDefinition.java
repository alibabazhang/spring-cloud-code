package com.rui.cn.dynaicrouteconfig;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器定义模型
 *
 * @author zhangrl
 * @time 2019/1/16-16:54
 **/
@Data
public class GatewayFilterDefinition {

    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}