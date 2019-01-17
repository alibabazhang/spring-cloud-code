package com.rui.cn.controller;

import com.rui.cn.dynaicrouteconfig.GatewayPredicateDefinition;
import com.rui.cn.dynaicrouteconfig.GatewayRouteDefinition;
import com.rui.cn.route.DynamicRouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * 增加路由
     *
     * @return
     */
    @PostMapping("/add")
    public String add() {
        try {
            GatewayRouteDefinition gwdefinition = new GatewayRouteDefinition();
            GatewayPredicateDefinition predicate = new GatewayPredicateDefinition();
            Map<String, String> predicateParams = new HashMap<>(8);
            gwdefinition.setId("test_route");
            predicate.setName("Path");
            predicateParams.put("pattern", "/rui");
            predicate.setArgs(predicateParams);
            gwdefinition.setPredicates(Arrays.asList(predicate));
            String uri = "http://www.jd.com";
            gwdefinition.setUri(uri);
            RouteDefinition definition = assembleRouteDefinition(gwdefinition);
            return this.dynamicRouteService.add(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succss";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }

    @PostMapping("/update")
    public String update() {
        GatewayRouteDefinition gwdefinition = new GatewayRouteDefinition();
        GatewayPredicateDefinition predicate = new GatewayPredicateDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        gwdefinition.setId("test_route");
        predicate.setName("Path");
        predicateParams.put("pattern", "/rui");
        predicate.setArgs(predicateParams);
        gwdefinition.setPredicates(Arrays.asList(predicate));
        String uri = "http://www.baidu.com";
        gwdefinition.setUri(uri);
        RouteDefinition definition = assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.update(definition);
    }

    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = new RouteDefinition();
        List<PredicateDefinition> pdList = new ArrayList<>();
        definition.setId(gwdefinition.getId());
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gwdefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);
        URI uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }

}
