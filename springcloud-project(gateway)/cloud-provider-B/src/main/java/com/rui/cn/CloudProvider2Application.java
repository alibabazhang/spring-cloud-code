package com.rui.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
//@EnableResourceServer
public class CloudProvider2Application /*extends ResourceServerConfigurerAdapter */ {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider2Application.class, args);
    }

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.csrf()
//				.disable()
//				.authorizeRequests()
//				.antMatchers("/**")
//				.authenticated();
////				.antMatchers(HttpMethod.GET, "/user/add")
////				.hasAnyAuthority("WRIGTH_READ");
//	}
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources
//				.resourceId("WRIGTH")
//				.tokenStore(jwtTokenStore());
//	}
//
//	@Bean
//	public JwtAccessTokenConverter jwtAccessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey("springcloud123");
//		return converter;
//	}
//
//	@Bean
//	public TokenStore jwtTokenStore() {
//		return new JwtTokenStore(jwtAccessTokenConverter());
//	}
}
