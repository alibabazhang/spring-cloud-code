package com.rui.cn.controller;

import com.rui.cn.feignclients.HelloFenginService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class HelloFeigenController {
    @Autowired
    private HelloFenginService helloFenginService;

    @PostMapping("/search/github/{str}")
    public ResponseEntity<byte[]> searchRepo(@ApiParam(name = "关键字", required = true) @PathVariable("str") String queryStr){
        return helloFenginService.searchRepo(queryStr);
    }
}
