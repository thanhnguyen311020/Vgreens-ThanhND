package edu.poly.vgreens.controller.site;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.vgreens.config.Utility;
import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Authority;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.Role;
import edu.poly.vgreens.model.CustomerDTO;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.AuthorityService;
import edu.poly.vgreens.service.CountryService;
import edu.poly.vgreens.service.MailerService;
import net.bytebuddy.utility.RandomString;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("vgreens")
public class LoginController {
	@Autowired
	private CountryService countryService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuthorityService authorityService;

	@Autowired
	MailerService mailerService;

	@Autowired
	HttpSession session;

	@Autowired
	BCryptPasswordEncoder be;

	@RequestMapping("login")
	public String Login(Model model) {
		return "site/login/login";
	}

	@GetMapping("login/register")
	public String Register(Model model) {
		List<Country> listCountries = countryService.findAllByOrderByNameAsc();

		model.addAttribute("listcountries", listCountries);
		model.addAttribute("customer", new CustomerDTO());
		return "site/login/register";
	}

	@PostMapping("login/register")
	public ModelAndView PostRegister(ModelMap model, @Valid @ModelAttribute("customer") CustomerDTO dto,
			BindingResult result, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

		Optional<Account> opt = accountService.findById(dto.getEmail());
		if (result.hasErrors()) {
			model.addAttribute("error", "Thông tin người dùng không hợp lệ");
		} else if (opt.isPresent()) {
			System.out.println("present");
			model.addAttribute("error", "Email đã tồn tại");
			return new ModelAndView("site/login/register", model);

		} else {
			Authority au = new Authority();
			Role rl = new Role();
			Country country = new Country();
			Account account = new Account();

			BeanUtils.copyProperties(dto, account);
			account.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
			account.setEnable(false);
			account.setPhoto("");
			account.setVerification_code("");
			au.setAccount(account);
			country.setId(dto.getCountry_id());
			account.setCountry(country);

			rl.setId("CUS");
			au.setRole(rl);

			accountService.register(account, getSiteURL(request));
			authorityService.save(au);
			model.addAttribute("message", "Vui lòng verify trước khi đăng nhập");
			return new ModelAndView("site/login/login");
		}

		return new ModelAndView("site/login/register", model);
	}

	// forgot password
	@GetMapping("login/forgotpassword")
	public String showForgotpassword() {
		return "site/login/forgotPass";
	}

	@PostMapping("login/forgotpassword")
	public String processForgotPassword(HttpServletRequest request, Model model) throws MessagingException {
		String email = request.getParameter("email");
		String token = RandomString.make(30);
		try {
			accountService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/vgreens/login/reset_password?token=" + token;
			String subject = "Đây là liên kết để đặt lại mật khẩu của bạn";
			String body = "<p>Xin chào,</p>" + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
					+ "<p>Nhấp vào liên kết bên dưới để thay đổi mật khẩu của bạn:</p>" + "<p><a href=\""
					+ resetPasswordLink + "\">Thay đổi mật khẩu của bạn</a></p>" + "<br>"
					+ "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, "
					+ "hoặc bạn đã không thực hiện yêu cầu.</p>";
			mailerService.send(email, subject, body);
			model.addAttribute("message", "Chúng tôi đã gửi một liên kết đặt lại mật khẩu !");
		} catch (AccountNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (MessagingException e) {
			model.addAttribute("error", "Lỗi khi gửi email!");
		}
		return "site/login/forgotPass";
	}

	// reset passsword
	@GetMapping("login/reset_password")
	public String showResetPassword(@Param(value = "token") String token, Model model) {
		Account account = accountService.getByResetPasswordToken(token);
		model.addAttribute("token", token);
		if (account == null) {
			model.addAttribute("message", "Invalid token");
			return "message";
		}
		return "site/login/resetPass";
	}

	@PostMapping("login/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		Account account = accountService.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");
		if (account == null) {
			model.addAttribute("message", "Invalid token");
			return "message";
		} else {
			accountService.updatePassword(account, password);
			model.addAttribute("message", "Bạn đã thay đổi thành công mật khẩu của bạn");
		}
		return "site/login/login";
	}
	// change password

	@GetMapping("account/change_password")
	public String showChangePassword(Model model) {
		model.addAttribute("pageTitle", "Thay đổi mật khẩu đã hết hạn");
		return "site/login/changePass";
	}

	@PostMapping("account/change_password")
	public String processChangePassword(HttpServletRequest request, RedirectAttributes ra, Model model)
			throws ServletException {
		String username = (String) session.getAttribute("username");
		Optional<Account> account = accountService.findByUsername(username);
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		model.addAttribute("pageTitle", "Thay đổi mật khẩu đã hết hạn");
		if (oldPassword.equals(newPassword)) {
			model.addAttribute("message", "Mật khẩu mới của bạn phải khác mật khẩu cũ.");
			return "site/login/changePass";
		} else if (newPassword.equals(confirmPassword) == false) {
			model.addAttribute("message", "Xác nhận mật khẩu mới của bạn không trùng khớp.");
			return "site/login/changePass";
		}
		if (!be.matches(oldPassword, account.get().getPassword())) {
			model.addAttribute("message", "Mật khẩu cũ của bạn không chính xác.");
			return "site/login/changePass";
		} else {
			accountService.changePassword(account.get(), newPassword);
			request.logout();
			ra.addFlashAttribute("message", "Bạn đã đổi mật khẩu thành công.");
			return "site/login/login";
		}

	}

	@GetMapping("login/unauthoriezed")
	public String Unauthoriezed() {
		return "site/error/403";
	}

	@GetMapping("login/sendmail")
	public String Sendmail() {
		return "site/login/sendmail";
	}

	@GetMapping("/login/verify")
	public String verifyUser(Model model, @Param("code") String code) {
		if (accountService.verify(code)) {
			model.addAttribute("message", "Xác minh thành công, Vui lòng đăng nhập lại !!!");
			return "site/login/login";
		} else {
			model.addAttribute("message", "Xác minh thất bại, Vui lòng đăng ký tài khoản một lần nữa !!!");
			return "site/login/login";
		}
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

}
