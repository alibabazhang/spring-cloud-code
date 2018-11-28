package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description：启动类
 * @author：[zhangruilin]
 * @className：CloudPermisssionRepositoryApplication
 * @time：2018/5/18-14:42
 **/
@SpringBootApplication
//本服务启动后会自动注册进eureka服务中
//服务发现
@EnableFeignClients
@EnableEurekaClient
public class
CloudFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudFeignApplication.class, args);
    }

}
