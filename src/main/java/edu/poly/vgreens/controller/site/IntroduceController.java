package edu.poly.vgreens.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("vgreens")
public class IntroduceController {
    @GetMapping(value="/introduce")
    public String getMethodName() {
        return "/site/introduce/introduce";
    }
     @GetMapping(value="/dieu-khoan-su-dung")
     public String getDieuKhoan() {
         return "/site/introduce/dieuKhoanSD";
     }
    @GetMapping(value="/hinh-thuc-thanh-toan")
    public String getHinhThucTT() {
        return "/site/introduce/payments";
    }
    @GetMapping(value="/chinh-sach-van-chuyen")
    public String getVanChuyen() {
        return "/site/introduce/transport";
    }
    @GetMapping(value="/chinh-sach-doi-tra")
    public String getDoiTra() {
        return "/site/introduce/returnGoods";
    }
     @GetMapping(value="/chinh-sach-bao-mat")
    public String getBaoMat() {
        return "/site/introduce/security";
    }
}
