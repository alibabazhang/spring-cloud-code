package com.rui.cn.controller;

import com.rui.cn.entity.User;
import com.rui.cn.feignclients.ICollapsingService;
import com.rui.cn.feignclients.UserFeignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private ICollapsingService iCollapsingService;

    /**
     * 用于演示Feign的Get请求多参数传递
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public void addUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user, HttpServletResponse response) throws Exception {
        response.getOutputStream().write(userFeignService.addUser(user).getBytes());
    }

    /**
     * 用于演示Feign的Post请求多参数传递
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        return userFeignService.updateUser(user);
    }

    /**
     * 使用feign文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "文件上传", notes = "请选择文件上传")
    public String imageUpload(@ApiParam(value = "文件上传", required = true) MultipartFile file) throws Exception {
        return userFeignService.fileUpload(file);
    }
    //========================================================hytrix请求合并==================================================================//

    /**
     * 请求聚合/合并
     *
     * @return
     */
    @PostMapping("/getAnimal")
    @ApiOperation(value = "请求聚合/合并-1")
    public String getAnimal() {
        return iCollapsingService.collapsing(1);
    }

    /**
     * 返回值必须是Future，否则不会进行合并/聚合
     *
     * @return
     * @throws InterruptedException
     */
    @PostMapping("/getAnimalSyn")
    @ApiOperation(value = "请求聚合/合并-2")
    public String getAnimalSyn() {
        return iCollapsingService.collapsing(2);
    }


    /**
     * 请求聚合/合并,整个应用的
     *
     * @return
     * @throws InterruptedException
     */
    @PostMapping("/getAnimalGolbal")
    @ApiOperation(value = "请求聚合/合并-3")
    public String getAnimalGolbal() throws Exception {
        return iCollapsingService.collapsing(3);
    }
}
