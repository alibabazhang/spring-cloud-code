package com.rui.cn.intercepter;

import com.alibaba.fastjson.JSON;
import com.rui.cn.util.UserPermissionUtil;
import com.rui.cn.vo.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模拟用户信息拦截
 *
 * @author zhangrl
 * @time 2019/1/17-15:29
 **/
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2) throws Exception {
        User user = getUser(request);
        UserPermissionUtil.permission(user);
        if (!UserPermissionUtil.verify(user, request)) {
            respone.setHeader("Content-Type", "application/json");
            String jsonstr = JSON.toJSONString("no permisson access service, please check");
            respone.getWriter().write(jsonstr);
            respone.getWriter().flush();
            respone.getWriter().close();
            throw new PermissionException("no permisson access service, please check");
        }
        UserContextHolder.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2, ModelAndView arg3)
            throws Exception {
        // DOING NOTHING
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse respone, Object arg2, Exception arg3)
            throws Exception {
        UserContextHolder.shutdown();
    }

    private User getUser(HttpServletRequest request) {
        String userid = request.getHeader("x-user-id");
        String username = request.getHeader("x-user-name");
        User user = new User();
        user.setUserId(userid);
        user.setUserName(username);
        return user;
    }

    static class PermissionException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public PermissionException(String msg) {
            super(msg);
        }
    }
}