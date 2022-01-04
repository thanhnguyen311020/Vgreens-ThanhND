package edu.poly.vgreens.log;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.service.AccountService;

import edu.poly.vgreens.entity.Account;

@Controller
@RequestMapping("vgreens")
public class SecurityController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "login/success")
    public String loginSuccess(Model model, Authentication auth) {
        String username = auth.getName();
        Optional<Account> account = accountService.findByUsername(username);
        if (account.get().getEnable() == true) {
            model.addAttribute("message", "Đăng nhập thành công");
            return "redirect:/vgreens/index";
        } else {
            model.addAttribute("message", "Đăng nhập không thành công, Vui lòng check email của bạn!");
            return "site/login/login";
        }

    }

    @RequestMapping(value = "login/error")
    public String loginFailed(Model model) {
        model.addAttribute("message", "Tên đăng nhập/email or mật khẩu không chính xác");
        return "site/login/login";
    }

    @RequestMapping(value = "login/unauthorized")
    public String loginUnauthorized(Model model) {
        model.addAttribute("message", "Bạn không có quyền truy xuất!");
        return "site/error/403";
    }

    @RequestMapping(value = "logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất !");
        return "site/index/index";
    }

    // OAuth2
    @RequestMapping(value = "oauth2/login/success")
    public String success(OAuth2AuthenticationToken oauth2) {
        accountService.loginFromOAuth2(oauth2);
        return "redirect:/vgreens/index";
    }
}
