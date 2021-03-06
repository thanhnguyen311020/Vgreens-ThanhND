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
			model.addAttribute("error", "Th??ng tin ng?????i d??ng kh??ng h???p l???");
		} else if (opt.isPresent()) {
			System.out.println("present");
			model.addAttribute("error", "Email ???? t???n t???i");
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
			model.addAttribute("message", "Vui l??ng verify tr?????c khi ????ng nh???p");
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
			String subject = "????y l?? li??n k???t ????? ?????t l???i m???t kh???u c???a b???n";
			String body = "<p>Xin ch??o,</p>" + "<p>B???n ???? y??u c???u ?????t l???i m???t kh???u c???a m??nh.</p>"
					+ "<p>Nh???p v??o li??n k???t b??n d?????i ????? thay ?????i m???t kh???u c???a b???n:</p>" + "<p><a href=\""
					+ resetPasswordLink + "\">Thay ?????i m???t kh???u c???a b???n</a></p>" + "<br>"
					+ "<p>B??? qua email n??y n???u b???n nh??? m???t kh???u c???a m??nh, "
					+ "ho???c b???n ???? kh??ng th???c hi???n y??u c???u.</p>";
			mailerService.send(email, subject, body);
			model.addAttribute("message", "Ch??ng t??i ???? g???i m???t li??n k???t ?????t l???i m???t kh???u !");
		} catch (AccountNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (MessagingException e) {
			model.addAttribute("error", "L???i khi g???i email!");
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
			model.addAttribute("message", "B???n ???? thay ?????i th??nh c??ng m???t kh???u c???a b???n");
		}
		return "site/login/login";
	}
	// change password

	@GetMapping("account/change_password")
	public String showChangePassword(Model model) {
		model.addAttribute("pageTitle", "Thay ?????i m???t kh???u ???? h???t h???n");
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
		model.addAttribute("pageTitle", "Thay ?????i m???t kh???u ???? h???t h???n");
		if (oldPassword.equals(newPassword)) {
			model.addAttribute("message", "M???t kh???u m???i c???a b???n ph???i kh??c m???t kh???u c??.");
			return "site/login/changePass";
		} else if (newPassword.equals(confirmPassword) == false) {
			model.addAttribute("message", "X??c nh???n m???t kh???u m???i c???a b???n kh??ng tr??ng kh???p.");
			return "site/login/changePass";
		}
		if (!be.matches(oldPassword, account.get().getPassword())) {
			model.addAttribute("message", "M???t kh???u c?? c???a b???n kh??ng ch??nh x??c.");
			return "site/login/changePass";
		} else {
			accountService.changePassword(account.get(), newPassword);
			request.logout();
			ra.addFlashAttribute("message", "B???n ???? ?????i m???t kh???u th??nh c??ng.");
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
			model.addAttribute("message", "X??c minh th??nh c??ng, Vui l??ng ????ng nh???p l???i !!!");
			return "site/login/login";
		} else {
			model.addAttribute("message", "X??c minh th???t b???i, Vui l??ng ????ng k?? t??i kho???n m???t l???n n???a !!!");
			return "site/login/login";
		}
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

}
