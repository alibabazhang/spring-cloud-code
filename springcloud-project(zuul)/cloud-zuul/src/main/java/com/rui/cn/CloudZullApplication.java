package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
//@EnableOAuth2Sso
public class CloudZullApplication /*extends WebSecurityConfigurerAdapter */ {

    public static void main(String[] args) {
        SpringApplication.run(CloudZullApplication.class, args);
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("login", "/cloud-proxy/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable();
//    }
//    @Bean
//    public SecondPreFilter secondPreFilter(){
//        return new SecondPreFilter();
//    }

}
