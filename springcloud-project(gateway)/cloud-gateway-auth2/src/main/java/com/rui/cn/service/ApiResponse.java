package com.rui.cn.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 接口统一返回对象
 *
 * @author zhangrl
 * @time 2018/8/9-11:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {
    private String code;

    private String msg;
    private T result;

    public ApiResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 成功 不返回result
     *
     * @return
     */
    public static <T> ApiResponse getSuccessResponse() {
        return new ApiResponse("1000", "处理成功", null);
    }


    /**
     * 获取失败的Response 自定义code msg
     *
     * @return
     */
    public static ApiResponse getFailResponse(String code, String msg) {
        ApiResponse apiResponse = new ApiResponse(code, msg);
        return apiResponse;
    }


    public static <T> ApiResponse getFailResponse(String code, String msg, T data) {
        ApiResponse apiResponse = new ApiResponse(code, msg);
        apiResponse.setResult(data);
        return apiResponse;
    }


    /**
     * 获取Response
     *
     * @return
     */
    public static <T> ApiResponse getResponse(String code, String msg, T data) {
        ApiResponse apiResponse = new ApiResponse(code, msg, data);
        return apiResponse;
    }


}
