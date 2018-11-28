package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description：启动类
 * @author：[zhangruilin]
 * @className：CloudPermisssionRepositoryApplication
 * @time：2018/5/18-14:42
 **/
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@ComponentScan(basePackages = {"com.rui.cn"})
public class CloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerApplication.class, args);
    }
}
