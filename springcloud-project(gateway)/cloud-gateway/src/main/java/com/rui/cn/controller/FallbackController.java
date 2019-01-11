package com.rui.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 测试hytrix熔断
 *
 * @author zhangrl
 * @time 2019/1/9-16:37
 **/
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "Spring Cloud Gateway Fallback！";
    }

}