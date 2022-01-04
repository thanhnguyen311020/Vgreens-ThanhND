package edu.poly.vgreens.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Collection;
import java.util.Date;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.vgreens.entity.Authority;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Authority;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.Role;
import edu.poly.vgreens.repository.AccountRepository;
import edu.poly.vgreens.repository.AuthorityRepository;
import edu.poly.vgreens.service.AccountService;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import net.bytebuddy.utility.RandomString;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired UploadImageImpl uploadService;

	@Override
	public boolean existsById(String id) {
		return accountRepository.existsById(id);
	}

	@Override
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		Account opt = accountRepository.findByEmail(email);
		if (opt != null) {
			UserDetails user = User.withUsername(email).password(bCryptPasswordEncoder.encode(password)).roles("CUS")
					.build();
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} else {
			Authority au = new Authority();
			Role rl = new Role();

			Account account = new Account();
			Country country = new Country();
			account.setUsername(email);
			account.setPassword(password);
//			for (String w : ((String) oauth2.getPrincipal().getAttribute("name")).split("\\s", 0)) {
//				System.out.println(w);
//				account.setFirst_name(w);
//				break;
//			}
//			for (String w : ((String) oauth2.getPrincipal().getAttribute("name")).split("\\s", 0)) {
//				System.out.println(w);
//				account.setLast_name(w.indent(1));
//			}
			account.setFirst_name((String) oauth2.getPrincipal().getAttribute("given_name"));
			account.setLast_name((String) oauth2.getPrincipal().getAttribute("family_name"));

			account.setPhone_number("");
			account.setPostal_code("");
			account.setState("");
			account.setEmail(email);
			account.setEnable(true);
			account.setPhoto("");
			account.setVerification_code("");
			account.setAddress_line("");
			country.setId(1);
			account.setCountry(country);
			au.setAccount(account);
			rl.setId("CUS");
			au.setRole(rl);

			accountRepository.save(account);
			authorityRepository.save(au);
			UserDetails user = User.withUsername(email).password(bCryptPasswordEncoder.encode(password)).roles("CUS")
					.build();

			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);

		}
	}

	@Override
	public void sendVerificationEmail(Account account, String siteURL)
			throws MessagingException, UnsupportedEncodingException {

		String toAddress = account.getEmail();
		String fromAddress = "Your email address";
		String senderName = "Your company name";
		String subject = "Vui lòng xác minh tài khoản đã đăng ký";
		String content = "Dear [[name]],<br>" + "Vui lòng nhấn vào đường dẫn dưới đây để xác minh tài khoản:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">Xác Minh Tài Khoản</a></h3>" + "Cảm ơn bạn đã đăng ký,<br>"
				+ "VGreens | Market.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("lanvunguyen1101@gmail.com", "VGreens");
		helper.setTo(account.getEmail());
		helper.setSubject("Xác minh email trước khi đăng nhập vào VGreens");

		content = content.replace("[[name]]", account.getFirst_name());
		String verifyURL = siteURL + "/vgreens/login/verify?code=" + account.getVerification_code();

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@Override
	public boolean verify(String verificationCode) {
		Account account = accountRepository.findByVerificationCode(verificationCode); // tim kiem chuoi string do

		if (account == null || account.getEnable()) {
			return false;
		} else {
			account.setVerification_code(null);
			account.setEnable(true);
			accountRepository.save(account);

			return true;
		}

	}

	@Override
	public void register(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException {

		String randomcode = RandomString.make(64); // random code verify
		account.setVerification_code(randomcode);
		account.setEnable(false); // set tai khoan hoat dong = false;

		accountRepository.save(account);
		sendVerificationEmail(account, siteURL);

	}

	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	public Account findByVerificationCode(String code) {
		return accountRepository.findByVerificationCode(code);
	}

	@Override
	public <S extends Account> S save(S entity) {
		Optional<Account> optExist = findById(entity.getUsername());
		if(optExist.isPresent()) {
			if(StringUtils.isEmpty(entity.getPassword()) && StringUtils.isEmpty(entity.getImg())) {
				entity.setPassword(optExist.get().getPassword());
				entity.setPhoto(optExist.get().getPhoto());
			}else if(StringUtils.isEmpty(entity.getPassword()) && !StringUtils.isEmpty(entity.getImg())){
//				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
				entity.setPassword(optExist.get().getPassword());
				entity.setPhoto(uploadService.uploadImage(entity.getImg()));
			}else if(!StringUtils.isEmpty(entity.getPassword()) && StringUtils.isEmpty(entity.getImg())){
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
				entity.setPhoto(optExist.get().getPhoto());
			}else if(!StringUtils.isEmpty(entity.getPassword()) && !StringUtils.isEmpty(entity.getImg())) {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
				entity.setPhoto(uploadService.uploadImage(entity.getImg()));
			}
		}else {
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			entity.setPhoto(uploadService.uploadImage(entity.getImg()));
		}
		
		
		return accountRepository.save(entity);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return accountRepository.findAll(sort);
	}

	@Override
	public Optional<Account> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public void deleteById(String id) {
		if(id.equals("root")) {
			return;
		}
		accountRepository.deleteById(id);
	}

	@Override
	public void delete(Account entity) {
		accountRepository.delete(entity);
	}

	// update resetPasswordToken
	@Override
	public void updateResetPasswordToken(String token, String email) throws AccountNotFoundException {
		Account account = accountRepository.findByEmail(email);
		if (account != null) {
			account.setResetPasswordToken(token);
			accountRepository.save(account);
		} else {
			throw new AccountNotFoundException("Could not find any account with the email " + email);
		}
	}

	// get by ResetPasswordToken
	@Override
	public Account getByResetPasswordToken(String token) {
		return accountRepository.findByResetPasswordToken(token);

	}

	// update Password
	@Override
	public void updatePassword(Account account, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(newPassword);
		account.setPassword(encodePassword);
		account.setResetPasswordToken(null);
		accountRepository.save(account);
	}

	// cập nhật mật khẩu của khách hàng
	@Override
	public void changePassword(Account account, String newPassword) {
		String encodePassword = bCryptPasswordEncoder.encode(newPassword);
		account.setPassword(encodePassword);
		account.setPasswordChangedTime(new Date());
		accountRepository.save(account);
	}

	@Override
	public List<Account> getAdministrators() {
		return accountRepository.getAdministrators();
	}
	
	@Override
	public Account updateStatus(Account entity) {
		if(entity.getEnable()) {
			entity.setEnable(false);
			return accountRepository.save(entity);
		}else {
			entity.setEnable(true);
			return accountRepository.save(entity);
		}
	}

	@Override
	public List<Account> getAccountByAvailabel(Boolean status) {
		return accountRepository.getAccountByAvailabel(status);
	}
	
	

}
