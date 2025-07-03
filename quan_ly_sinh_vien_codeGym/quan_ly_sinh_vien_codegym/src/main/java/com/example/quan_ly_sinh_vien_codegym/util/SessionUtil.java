package com.example.quan_ly_sinh_vien_codegym.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static void set(HttpServletRequest request, String key, Object value){
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    public static Object get(HttpServletRequest request, String key){
        HttpSession session = request.getSession(false);
        return (session != null) ? session.getAttribute(key) : null;
    }

    public static void remove(HttpServletRequest request, String key){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute(key);
        }
    }
}
