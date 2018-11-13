package com.vens.filter;


import com.vens.utils.IpUtil;
import com.vens.utils.UserUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        // �õ��û�������ص���Ϣ����½���û����û������ԣ�
        fillUserInfo((HttpServletRequest) request);

        try {
            chain.doFilter(request, response);
        } finally {
            // ����tomcat�߳����ã��ǵ����
            clearAllUserInfo();
        }
    }

    private void clearAllUserInfo() {
        UserUtil.clearAllUserInfo();
    }

    private void fillUserInfo(HttpServletRequest request) throws IOException {
        // �û���Ϣ
        //String user = getUserFromSession(request);
        String user = null;
        String uri = request.getRequestURI();
        if (null != uri
                && uri.contains("login")
                && uri.contains("register")) {
//            BufferedReader br = request.getReader();
//            String body = "";
//            String temp;
//            while (null != (temp = br.readLine())) {
//                body += temp;
//            }
//            JSONObject bodyJson = JSON.parseObject(body);
//            user = (String) bodyJson.get("phone");
            user= IpUtil.getRealIp(request);
        } else {
            String token = getTokenFromCookies(request);
            user = getUserByToken(token);
        }

        if (user != null) {
            UserUtil.setUser(user);
        }

        // ������Ϣ
        String locale = getLocaleFromCookies(request);

        // ���뵽threadlocal��ͬһ���߳��κεط��������ó���
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

        // ��session�л�ȡ�û���Ϣ�ŵ���������
        return (String) session.getAttribute(UserUtil.KEY_USER);
    }

    public void destroy() {

    }

}
