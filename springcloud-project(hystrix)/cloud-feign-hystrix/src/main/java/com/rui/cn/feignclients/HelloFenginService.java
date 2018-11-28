package com.rui.cn.feignclients;

import com.rui.cn.config.HelloFeigenServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
* 由于feign开启gzip之后，feign、之间通过二进制数据传输所以 ResponseEntity<byte[]>接受才正常
* 用于测试连接
* @author zhangrl
* @time 2018/11/10-13:07
**/
@FeignClient(name = "github-client111111111111",url = "https://api.github.com",configuration = HelloFeigenServiceConfig.class)
@Service
public interface HelloFenginService {
    @RequestMapping(value = "/search/repositories",method = RequestMethod.GET)
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String queryStr);
}
