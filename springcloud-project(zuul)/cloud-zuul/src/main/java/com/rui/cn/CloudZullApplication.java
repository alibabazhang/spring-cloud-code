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

//    当应用需要与外网交互时，由于网络开销比较大且请求比较耗时，这时候选用线程隔离模式，可以保证有剩余的容器线程可用，而不会由于外部原因使线程一直处于阻塞或者等待的状态，可以快速失败返回。
//    当应用只在内网交互且体量比较大，这时使用信号量隔离比较好，因为这类的响应通常比较快，不会占用容器线程太多时间，使用信号量线程上下文就会成为一个瓶颈，可以减少线程切换的开销，提高应用的运转效率！
}
