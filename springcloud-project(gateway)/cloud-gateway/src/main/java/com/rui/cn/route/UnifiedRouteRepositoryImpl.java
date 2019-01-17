package com.rui.cn.route;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.synchronizedMap;
/**
 * 基于数据库的动态路由实现（适用于集群环境）
 *
 * @author zhangrl
 * @time 2019/1/16-17:23
 **/
public class UnifiedRouteRepositoryImpl implements RouteDefinitionRepository {

    private final Map<String, RouteDefinition> routes = synchronizedMap(new LinkedHashMap<String, RouteDefinition>());


    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return null;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }





}