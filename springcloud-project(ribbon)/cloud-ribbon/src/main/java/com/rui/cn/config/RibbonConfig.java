package com.rui.cn.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.rui.cn.annotation.AvoidScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon负载均衡策略
 *
 * @author zhangrl
 * @time 2018/11/13
 **/
@Configuration
@AvoidScan
public class RibbonConfig {
    @Autowired
    private IClientConfig iClientConfig;

    @Bean
    public IRule RibonRule(IClientConfig iClientConfig) {
        return new RandomRule();

//        RandomRule 随机策略：随机选择server
//        RoundRobinRule 轮训策略：按顺序循环选择server
//        RetryRule 重试策略：在一个配置时间段内当选择的server不成功，则尝试选择一个可用的server
//        BestAvailableRule 最低并发策略：逐个考察server，若server断路器打开则忽略，在选择其中连接最低的server
//        AvailabilityFilteringRule 可用过滤策略：过滤掉一直连接失败并标记为circuit tripped的server，过滤掉高并发的的server
//        WeightedResponseTimeRule 响应时间加权策略：根据server响应时间分配权重，响应时间越长权重越低，被选择的概率越低，反之越高。这个策略很贴切，结合了各种因素，如网络，磁盘的直接影响响应时间
//        ZoneAvoidanceRule 区域权重策略：综合判断server所在区域的性能和server的可用性轮询选择server，并且判断一个AWS zone的运行性能是否可用，提出不可用zone中的所有server
    }
}
