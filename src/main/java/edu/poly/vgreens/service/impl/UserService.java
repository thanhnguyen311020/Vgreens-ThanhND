package edu.poly.vgreens.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.service.AccountService;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	HttpSession session;
	@Autowired
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<Account> user = accountService.findById(username);
			session.setAttribute("username", user.get().getUsername());
			// System.out.println("username" + username);
			// String password = pe.encode(user.get().getPassword());
			String password = user.get().getPassword();
			if (user.isPresent() && pe.matches(pe.encode(password), user.get().getPassword())) {
				user.get().setPassword("");

			}
			String[] roles = user.get().getAuthorities().stream().map(er -> er.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username).password(password).roles(roles).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found");
		}
	}

	
}
