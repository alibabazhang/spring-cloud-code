package com.rui.cn.intercepter;

import com.rui.cn.vo.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * resttemplate拦截器
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
public class RestTemplateUserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        User user = UserContextHolder.currentUser();
        request.getHeaders().add("x-user-id", user.getUserId());
        request.getHeaders().add("x-user-name", user.getUserName());
        request.getHeaders().add("x-user-serviceName", request.getURI().getHost());
        return execution.execute(request, body);
    }
}
