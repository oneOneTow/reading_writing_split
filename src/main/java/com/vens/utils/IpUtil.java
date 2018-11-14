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

    public static long ipToLong(String ip) {
        long result = 0;

        String[] ipAddressInArray = ip.split("\\.");

        for (int i = 3; i >= 0; i--) {
            long longIp = Long.parseLong(ipAddressInArray[3 - i]);
            //1. 192 << 24
            //1. 168 << 16
            //1. 1   << 8
            //1. 2   << 0
            result |= longIp << (i * 8);

        }
        return result;
    }

    public static String longToIp(long ip) {
        String strIp =
                ((ip >> 24) & 0xFF) + "."
                        + ((ip >> 16) & 0xFF) + "."
                        + ((ip >> 8) & 0xFF) + "."
                        + (ip & 0xFF);
        return strIp;
    }
}
