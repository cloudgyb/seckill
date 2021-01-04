package com.gyb.seckill.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
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

    public static String getCookieValue(String cookieName){
        HttpServletRequest httpRequest = getHttpRequest();
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cookie : cookies) {
            if(cookieName.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    public static HttpSession getHttpSession(boolean creatable){
        return getHttpRequest().getSession(creatable);
    }

    public static boolean isAjaxRequest() {
        final HttpServletRequest httpRequest = getHttpRequest();
        final String header = httpRequest.getHeader("X-Requested-With");
        return header != null && !"".equals(header);
    }
}
