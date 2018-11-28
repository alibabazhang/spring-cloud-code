package com.rui.cn.feignclients;

import com.rui.cn.config.HelloFeigenServiceConfig;
import com.rui.cn.entity.User;
import com.rui.cn.feignclients.impl.UserFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "CLOUD-PROVIDER", configuration = HelloFeigenServiceConfig.class, fallback = UserFeignServiceImpl.class)
@Component
public interface UserFeignService {

    @RequestMapping(value = "/user/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    String updateUser(@RequestBody User user);

    /***
     * 1.produces,consumes必填
     * 2.注意区分@RequestPart和RequestParam，不要将
     * @RequestPart(value = "file") 写成@RequestParam(value = "file")
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user/uploadFile",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);

}