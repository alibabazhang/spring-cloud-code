package com.rui.cn.filter;

import com.rui.cn.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

/**
 * 全局的网关过滤器
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI uri = gatewayUrl.getUri();
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders httpHeaders = request.getHeaders();
        // 从请求头中获取token、信息
        String token = httpHeaders.getFirst(JwtUtil.HEADER_AUTH);
        Map<String, String> userMap = JwtUtil.validateToken(token);
        ServerHttpRequest.Builder mutate = request.mutate();
        if (userMap.get("user").equals("admin") || userMap.get("user").equals("spring") || userMap.get("user").equals("clou")) {
            mutate.header("x-user-id", userMap.get("id"));
            mutate.header("x-user-name", userMap.get("user"));
            mutate.header("x-user-serviceName", uri.getHost());
        } else {
            throw new RuntimeException("user not exist, please check");
        }
        ServerHttpRequest buildRequest = mutate.build();
        return chain.filter(exchange.mutate().request(buildRequest).build());
    }

    @Override
    public int getOrder() {
        return -999;
    }
}
