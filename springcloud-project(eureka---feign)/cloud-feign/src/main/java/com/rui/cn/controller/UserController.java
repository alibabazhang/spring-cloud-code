package com.rui.cn.controller;

import com.rui.cn.entity.User;
import com.rui.cn.feignclients.UserFeignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserFeignService userFeignService;

	/**
	 * 用于演示Feign的Get请求多参数传递
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
		return userFeignService.addUser(user);
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
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ApiOperation(value = "文件上传", notes = "请选择文件上传" )
	public String imageUpload(@ApiParam(value="文件上传",required = true) MultipartFile file ) throws Exception{
		return userFeignService.fileUpload(file);
	}
}
