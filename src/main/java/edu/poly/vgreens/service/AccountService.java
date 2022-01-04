package edu.poly.vgreens.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import edu.poly.vgreens.entity.Account;

public interface AccountService {

	void delete(Account entity);

	void deleteById(String id);

	Optional<Account> findById(String id);

	List<Account> findAll(Sort sort);

	List<Account> findAll();

	<S extends Account> S save(S entity);

	void register(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException;

	boolean verify(String verificationCode);

	void sendVerificationEmail(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException;

	boolean existsById(String id);

	void updatePassword(Account account, String newPassword);

	Account getByResetPasswordToken(String token);

	void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;

	Optional<Account> findByUsername(String username);

	void changePassword(Account account, String newPassword);

	List<Account> getAdministrators();

	void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

	Account updateStatus(Account entity);

	List<Account> getAccountByAvailabel(Boolean status);
}
