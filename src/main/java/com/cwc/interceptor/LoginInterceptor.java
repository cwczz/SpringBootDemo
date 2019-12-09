package com.cwc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            request.setAttribute("msg","你没有权限请登录");
            request.getRequestDispatcher("/login").forward(request,response);
        }
        return true;
    }
}
