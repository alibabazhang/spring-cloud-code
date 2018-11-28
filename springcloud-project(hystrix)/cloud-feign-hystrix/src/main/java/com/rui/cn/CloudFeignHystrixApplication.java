package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

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
public class CloudFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudFeignHystrixApplication.class, args);
    }

    /**
     * 初始RestTemplate
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
