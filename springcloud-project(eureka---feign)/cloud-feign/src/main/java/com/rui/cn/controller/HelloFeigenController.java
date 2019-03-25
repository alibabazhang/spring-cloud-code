package com.rui.cn.controller;

import com.rui.cn.feignclients.HelloFenginService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class HelloFeigenController {
    @Autowired
    private HelloFenginService helloFenginService;

    @GetMapping("/search/github")
    public ResponseEntity<byte[]> searchRepo(@RequestParam("str") @ApiParam(name = "关键字", required = true) String queryStr) {
        return helloFenginService.searchRepo(queryStr);
    }
}
