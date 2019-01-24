package com.rui.cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Configuration
@EnableWebMvc
public class ApplicationExceptionAdapter extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("swagger-ui.html")
               .addResourceLocations("classpath:/META-INF/resources/");
       registry.addResourceHandler("/webjars")
               .addResourceLocations("classpath:/META-INF/webjars/");
    }
}
