package com.vens.utils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    private static String UNKNOW = "unknow";
    private static String FORWARD_IP = "x-forwarded-for";
    private static String PROXY_IP = "Proxy-Client-IP";
    private static String WL_PROXY_IP = "WL-Proxy-Client-IP";

    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader(FORWARD_IP);
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
