package com.rui.cn.hystrixconfig;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
* ErrorDecoder对接口调用长产生的400-500的错误进行包装
*
* @author zhangrl
* @time 2018/11/15-16:29
**/
@Component
public class FeignErrorDecoder implements feign.codec.ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
	 try {
            if (response.status() >= 400 && response.status() <= 499) {
                String error = Util.toString(response.body().asReader());
                return new HystrixBadRequestException(error);
            }
        } catch (IOException e) {
           System.out.println(e);
        }
        return feign.FeignException.errorStatus(methodKey, response);
	}
	
}