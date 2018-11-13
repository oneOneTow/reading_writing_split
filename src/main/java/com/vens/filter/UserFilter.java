package com.vens.filter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vens.utils.UserUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class UserFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // 得到用户个人相关的信息（登陆的用户，用户的语言）
        fillUserInfo((HttpServletRequest) request);

        try {
            chain.doFilter(request, response);
        } finally {
            // 由于tomcat线程重用，记得清空
            clearAllUserInfo();
        }
    }

    private void clearAllUserInfo() {
        UserUtil.clearAllUserInfo();
    }

    private void fillUserInfo(HttpServletRequest request) throws IOException {
        // 用户信息
        //String user = getUserFromSession(request);
        String user = null;
        String uri = request.getRequestURI();
        if (null != uri && uri.contains("login")) {
            BufferedReader br = request.getReader();
            String body = "";
            String temp;
            while (null != (temp = br.readLine())) {
                body += temp;
            }
            JSONObject bodyJson = JSON.parseObject(body);
            user = (String) bodyJson.get("phone");
        } else {
            String token = getTokenFromCookies(request);
            user = getUserByToken(token);
        }

        if (user != null) {
            UserUtil.setUser(user);
        }

        // 语言信息
        String locale = getLocaleFromCookies(request);

        // 放入到threadlocal，同一个线程任何地方都可以拿出来
        if (locale != null) {
            UserUtil.setLocale(locale);
        }
    }

    private String getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            if (UserUtil.KEY_TOKEN.equals(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }

        return null;
    }

    private String getUserByToken(String token) {
        return token;
    }

    private String getLocaleFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            if (UserUtil.KEY_LANG.equals(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }

        return null;
    }

    private String getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        // 从session中获取用户信息放到工具类中
        return (String) session.getAttribute(UserUtil.KEY_USER);
    }

    public void destroy() {

    }

}
