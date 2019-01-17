package com.rui.cn.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 统计某个或某种路由的处理时长
 * GatewayFilter是从web filter中复制来的，相当于一个filter过滤器，可以应用到一个路由或一个分组的路由上
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
public class CustomGatewayFilter implements GatewayFilter, Ordered {

    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String COUNT_Start_TIME = "countStartTime";

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_Start_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(COUNT_Start_TIME);
                    Long endTime = System.currentTimeMillis() - startTime;
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + "-------------------》请求耗时:" + endTime + "ms");
                    }
                })
        );
    }
}
