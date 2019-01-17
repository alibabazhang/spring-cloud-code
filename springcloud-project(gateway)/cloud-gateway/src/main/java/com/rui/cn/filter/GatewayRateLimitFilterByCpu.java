package com.rui.cn.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 根据cpu使用情况进行限流
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Component
public class GatewayRateLimitFilterByCpu implements GatewayFilter, Ordered {

    private final Logger log = LoggerFactory.getLogger(GatewayRateLimitFilterByCpu.class);
    @Autowired
    private MetricsEndpoint metricsEndpoint;

    /**
     * 常量cpu
     */
    private static final String METRIC_NAME = "system.cpu.usage";

    /**
     * 使用率达到该节点 开启限流
     */
    private static final double MAX_USAGE = 0.50D;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Double systemCpuUsage = metricsEndpoint.metric(METRIC_NAME, null)
                .getMeasurements()
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(MetricsEndpoint.Sample::getValue)
                .filter(Double::isFinite)
                .orElse(0.0D);
        Boolean isOpenRateLimit = systemCpuUsage > MAX_USAGE;
        log.info("当前机器的cpu使用率为: {}, 开启限流节点的阈值为: {}, 故当前的限流开启状态为：{}", systemCpuUsage, MAX_USAGE, isOpenRateLimit);
        if (isOpenRateLimit) {
            //当CPU的使用超过设置的最大阀值开启限流
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
