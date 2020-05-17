package com.gyb.seckill.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author geng
 * 2020/5/17
 */
public final class HttpServletUtil {
    public static HttpServletRequest getHttpRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest();
    }

    public static HttpServletResponse getHttpResponse(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getResponse();
    }

    public static HttpSession getHttpSession(boolean creatable){
        return getHttpRequest().getSession(creatable);
    }

}
