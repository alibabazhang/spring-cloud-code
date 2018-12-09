package com.rui.cn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class SecondPreFilter extends ZuulFilter {
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("这是SecondPreFilter！");
        //从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = ctx.getRequest();
        //从request尝试获取a参数值
        String a = request.getRequestURI();
        System.out.println("请求地址：" + a);
        //如果a参数值为空则进入此逻辑
        if (a.contains("getAnimalSyn")) {
            //对该请求禁止路由，也就是禁止访问下游服务
            ctx.setSendZuulResponse(false);
            //设定responseBody供PostFilter使用
            ctx.setResponseBody("{\"status\":500,\"message\":\"id参数为空！\"}");
            //logic-is-success保存于上下文，作为同类型下游Filter的执行开关
            //到这里此Filter逻辑结束
            return null;
        }
        return null;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
