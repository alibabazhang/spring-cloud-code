package com.rui.cn.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway 自带的限流工厂 重新令牌桶的key
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
public class RemoteAddrKeyResolver implements KeyResolver {

    /**
     * 定义当前bean的name
     */
    public static final String BEAN_NAME = "remoteAddrKeyResolver";

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
