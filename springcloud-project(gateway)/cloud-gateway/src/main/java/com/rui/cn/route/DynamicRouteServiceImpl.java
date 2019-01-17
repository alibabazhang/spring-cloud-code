package com.rui.cn.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * todo
 *
 * @author zhangrl
 * @time 2018/11/28-14:04
 **/
@Component
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 增加路由
     *
     * @param definition
     * @return
     */
    public String add(RouteDefinition definition) {
        this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * 更新路由
     *
     * @param definition
     * @return
     */
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: \"+definition.getId()";
        }
        try {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
