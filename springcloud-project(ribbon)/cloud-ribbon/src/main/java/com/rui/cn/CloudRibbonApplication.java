package com.rui.cn;

import com.rui.cn.annotation.AvoidScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @description：启动类
 * @author：[zhangruilin]
 * @className：CloudPermisssionRepositoryApplication
 * @time：2018/5/18-14:42
 **/
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "/CLOUD-PROVIDER", configuration = RibbonConfig.class) //CLOUD-PROVIDER 服务实例名称
//@RibbonClients(value = {
//		@RibbonClient(name = "client-a", configuration = RibbonConfig.class),
//		@RibbonClient(name = "client-b", configuration = RibbonConfig.class)
//})
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {AvoidScan.class})})
public class CloudRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
