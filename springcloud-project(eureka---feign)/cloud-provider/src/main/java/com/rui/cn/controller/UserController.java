package com.rui.cn.controller;

import com.rui.cn.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(User user , HttpServletRequest request){
		String token=request.getHeader("oauthToken");
		return "hello,"+user.getName();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser( @RequestBody User user){
		return "hello,"+user.getName();
	}

	@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUploadServer(MultipartFile file ) throws Exception{
		return file.getOriginalFilename();
	}

}