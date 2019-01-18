package com.rui.cn.config;

import com.rui.cn.intercepter.RestTemplateUserContextInterceptor;
import com.rui.cn.intercepter.UserContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 通用的拦截器类
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Configuration
@EnableWebMvc
public class ComConfiguration extends WebMvcConfigurationSupport {
    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextInterceptor());
    }


    /***
     * RestTemplate 拦截器，在发送请求前设置鉴权的用户上下文信息
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RestTemplateUserContextInterceptor());
        return restTemplate;
    }
}
