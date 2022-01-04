package edu.poly.vgreens.config;

import javax.servlet.http.HttpServletRequest;

// tạo liên kết đặt lại mật khẩu có chứa mã thông báo ngẫu nhiên dưới dạng tham số URL
public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
