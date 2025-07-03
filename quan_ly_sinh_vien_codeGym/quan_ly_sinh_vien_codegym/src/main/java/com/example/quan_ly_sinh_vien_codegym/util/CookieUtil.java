package com.example.quan_ly_sinh_vien_codegym.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void add(HttpServletResponse response, String name, String value, int maxAgeInSecond){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAgeInSecond);
        response.addCookie(cookie);
    }

    public static String get(HttpServletRequest request, String name){
        if(request.getCookies() == null) return null;
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void remove(HttpServletResponse response, String name){
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
