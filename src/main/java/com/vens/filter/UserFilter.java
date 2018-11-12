package com.vens.filter;


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
        }
        finally {
            // ����tomcat�߳����ã��ǵ����
            clearAllUserInfo();
        }
    }

    private void clearAllUserInfo() {
        UserUtil.clearAllUserInfo();
    }

    private void fillUserInfo(HttpServletRequest request) {
        // �û���Ϣ
        String user = getUserFromSession(request);

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