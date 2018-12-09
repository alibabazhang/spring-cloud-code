package com.rui.cn;

import com.rui.cn.filter.SecondPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class CloudZullApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZullApplication.class, args);
    }
    @Bean
    public SecondPreFilter secondPreFilter(){
        return new SecondPreFilter();
    }

}
