package com.connor.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import sun.net.util.IPAddressUtil;


public class IpUtils {
    
    private static final String DOMAIN = "b.qiku.com";
    
    /**
     * 得到当前Locale
     */
    public static Locale getLocale(HttpServletRequest request) {

        Locale locale = null;
        if (request != null) {
            locale = request.getLocale();
        }
        if (locale == null) {
            locale = Locale.CHINA;
        }
        return locale;
    }

    public static Object getAttributeFromSession(String name,HttpServletRequest request) {

        Object result = null;

        if (StringUtil.isNotEmpty(name) && request != null) {
            HttpSession session = request.getSession();
            if (session != null) {
                result = session.getAttribute(name);
            }
        }

        return result;
    }


    /**
     * 获得客户端真实IP地址
     * 
     * @param request
     * @return
     * @date 2014-2-8
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * Convenience method to set a cookie
     * 
     * @param response the current response
     * @param name the name of the cookie
     * @param value the value of the cookie
     * @param path the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path) {
        setCookie(response, name, value, path, DOMAIN, 3600 * 24 * 30);
    }

    /**
     * Convenience method to set a cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param path
     * @param domain
     * @param expiry
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, String domain, int expiry) {


        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setMaxAge(expiry);

        response.addCookie(cookie);
    }

    /**
     * Convenience method to get a cookie by name
     * 
     * @param request the current request
     * @param name the name of the cookie to find
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (Cookie thisCookie : cookies) {
            if (thisCookie.getName().equals(name)) {
                // cookies with no value do me no good!
                if (!thisCookie.getValue().equals("")) {
                    returnCookie = thisCookie;

                    break;
                }
            }
        }

        return returnCookie;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String path) {
        Cookie cookie = getCookie(request, cookieName);

        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            // guangyaohui add 2014-5-6 start
            cookie.setDomain(DOMAIN);
            // guangyaohui add 2014-5-6 end
            response.addCookie(cookie);
        }
    }

    /**
     * Convenience method for deleting a cookie by name
     * 
     * @param response the current web response
     * @param cookie the cookie to delete
     * @param path the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String path) {

        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            // guangyaohui add 2014-5-6 start
            cookie.setDomain(DOMAIN);
            // guangyaohui add 2014-5-6 end
            response.addCookie(cookie);
        }
    }

    /**
     * Convenience method to get the application's URL based on request variables.
     * 
     * @param request the current request
     * @return URL to application
     */
    public static String getAppURL(HttpServletRequest request) {

        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }

    /**
     * 判断是否来自移动设备的请求
     * 
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {

        // loadInitMobileConfig();
        boolean isMobile = false;
        String header = new StringBuffer().append("=").append(request.getHeader("user-agent")).append("  ").append(request.getHeader("accept"))
                .append("  ").append(request.getHeader("via")).append("  ").append(request.getHeader("ua-os")).toString();
        Pattern pattern = Pattern.compile(defaultKeywords, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(header);
        if (m.find()) {
            isMobile = true;
        }

        return isMobile;
    }

    private static String defaultKeywords = "wap|Opera|Windows CE|Android|iPhone|Nokia";

    public static boolean internalIp(String ip) {
        if (ip.equals("127.0.0.1")) {
            return true;
        } else if (ip.equals("0:0:0:0:0:0:0:1")) {
            return true;
        }
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        return internalIp(addr);
    }

    public static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
        case SECTION_1:
            return true;
        case SECTION_2:
            if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                return true;
            }
        case SECTION_5:
            switch (b1) {
            case SECTION_6:
                return true;
            }
        default:
            return false;
        }
    }

}
