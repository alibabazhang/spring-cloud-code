package com.rui.cn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 管理atomikos的Configuration的生命周期
 *
 * @author zhangrl
 * @time 2019/3/21-13:11
 **/
@Configuration
public class AtomikosTccConfig {

    @Bean
    public AtomikosTccSpringAdapter atomikosTccSpringAdpater(){
        return new AtomikosTccSpringAdapter();
    }

    public static class AtomikosTccSpringAdapter {
        @PostConstruct
        public void start(){
            com.atomikos.icatch.config.Configuration.init();
        }

        @PreDestroy
        public void shutdown(){
            com.atomikos.icatch.config.Configuration.shutdown(false);
        }
    }
}