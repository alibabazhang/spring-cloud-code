package com.rui.cn.feignclients;


import com.rui.cn.config.FeigenServiceConfig;
import com.rui.cn.feignclients.impl.ICollapsingServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * hytrix请求合并
 *
 * @author zhangrl
 * @time 2018/11/19-14:33
 **/
@FeignClient(value = "CLOUD-ZUUL", configuration = FeigenServiceConfig.class, fallback = ICollapsingServiceImpl.class)
public interface ICollapsingService {
    @PostMapping(value = "/zuul/cloud-proxy/user/getAnimal", consumes = MediaType.APPLICATION_JSON_VALUE)
    String collapsing(Integer id);

    @PostMapping(value = "/zuul/cloud-proxy/user/getAnimalSyn", consumes = MediaType.APPLICATION_JSON_VALUE)
    String collapsingSyn(Integer id);

    @PostMapping(value = "/zuul/cloud-proxy/user/getAnimalGolbal", consumes = MediaType.APPLICATION_JSON_VALUE)
    String collapsingGlobal(Integer id);

}