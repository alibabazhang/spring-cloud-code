package com.rui.cn.controller;

import com.rui.cn.entity.Animal;
import com.rui.cn.entity.User;
import com.rui.cn.service.ICollapsingService;
import com.rui.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ICollapsingService collapsingService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(User user, HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            System.out.println(key + ": " + request.getHeader(key));
        }
        // 写入缓存
        userService.adduser(user, request);
        userService.adduser(user, request);
        userService.adduser(user, request);
        // 删除缓存
        userService.updateUser(user);
        userService.adduser(user, request);
        return userService.adduser(user, request);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user, HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            System.out.println(key + ": " + request.getHeader(key));
        }
        return userService.updateUser(user);
    }

    /**
     * feign文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(MultipartFile file) throws Exception {
        return file.getOriginalFilename();
    }
    //========================================================hytrix请求合并==================================================================//

    /**
     * 请求聚合/合并
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/getAnimal")
    public String getAnimal() throws Exception {
        Future<Animal> user = collapsingService.collapsing(1);
        Future<Animal> user2 = collapsingService.collapsing(2);
        System.out.println(user.get().getName());
        System.out.println(user2.get().getName());
        return "Success";
    }

    /**
     * 返回值必须是Future，否则不会进行合并/聚合
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/getAnimalSyn")
    public String getAnimalSyn() {
        Animal user = collapsingService.collapsingSyn(1);
        Animal user2 = collapsingService.collapsingSyn(2);
        System.out.println(user.getName());
        System.out.println(user2.getName());
        return "Success";
    }


    /**
     * 请求聚合/合并,整个应用的
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/getAnimalGolbal")
    public String getAnimalGolbal() throws Exception {
        Future<Animal> user = collapsingService.collapsingGlobal(1);
        Future<Animal> user2 = collapsingService.collapsingGlobal(2);
        System.out.println(user.get().getName());
        System.out.println(user2.get().getName());
        return "Success";
    }

}