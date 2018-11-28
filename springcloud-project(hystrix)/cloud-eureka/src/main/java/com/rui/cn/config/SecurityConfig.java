package com.rui.cn.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
*@描述：安全校验
*@创建人：[zhang]
*@类名：SecurityConfig
*@时间：2018/11/4 0004-下午 16:20
**/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 安全校验默认开启了csrf对于client费界面应用不合适 故禁用
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void  configure(HttpSecurity httpSecurity) throws Exception{
        super.configure(httpSecurity);
        httpSecurity.csrf().disable();
    }

}
