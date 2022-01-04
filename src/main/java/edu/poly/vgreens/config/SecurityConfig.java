package edu.poly.vgreens.config;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;


import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.impl.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	HttpSession session;

//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserService userService;

//	@Autowired
//	private JwtRequestFilter jwtRequestFilter;

	@Primary
	@Bean
	public FreeMarkerConfigurationFactoryBean factoryBean() {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("classpath:/templates/mailtemplates");
		return bean;
	}

	// Cung cấp nguồn dữ liệu đăng nhập
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	// Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

	// phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().disable();
		// http.authorizeRequests()
		// .antMatchers("/vgreens/order/**").authenticated()
		// .antMatchers("/vgreens/account/**").authenticated()
		// .antMatchers("/vgreens/admin/**").hasAnyRole("STAF", "DIRE")
		// .antMatchers("/vgreens/admin/authorities").hasRole("DIRE")
		// // .antMatchers("/vgreens/order").hasAnyRole("STAF", "DIRE")
		// .anyRequest().permitAll();

		//
//		http.antMatcher("/vgreens/admin/ordertrack-management")
//		.authorizeRequests().anyRequest().hasRole("SHIP").and()
//		.formLogin().loginPage("/vgreens/login").loginProcessingUrl("/vgreens/login")
//		.failureForwardUrl("/vgreens/login/error").defaultSuccessUrl("/vgreens/admin/ordertrack-management",true);

		http.formLogin().loginPage("/vgreens/login").loginProcessingUrl("/vgreens/login")
				.defaultSuccessUrl("/vgreens/login/success", false).failureForwardUrl("/vgreens/login/error");
		http.authorizeRequests().antMatchers("/vgreens/order/**").authenticated().antMatchers("/vgreens/account/**")
				.authenticated().antMatchers("/vgreens/cart/checkout").authenticated()
				// .antMatchers("/vgreens/order").hasAnyRole("STAF", "DIRE")
				.antMatchers("/vgreens/admin/authority-management", "/vgreens/rest/authorities/**").hasRole("DIRE")
				.antMatchers("/vgreens/admin/ordertrack-management").hasAnyRole("SHIP", "DIRE")
				.antMatchers("/vgreens/admin/**").hasAnyRole("STAF", "DIRE")

				.anyRequest().permitAll();

		http.formLogin().loginPage("/vgreens/login").loginProcessingUrl("/vgreens/login")
				.defaultSuccessUrl("/vgreens/login/success", false).failureForwardUrl("/vgreens/login/error");
		http.rememberMe().tokenValiditySeconds(88400);

		// k có quyen truy xuat
		http.exceptionHandling().accessDeniedPage("/vgreens/login/unauthoriezed");

		http.logout().logoutUrl("/vgreens/logoff").logoutSuccessUrl("/vgreens/logoff/success");
		// OAuth2-Đăng nhập từ mạng xã hội
		http.oauth2Login().loginPage("/vgreens/login").defaultSuccessUrl("/vgreens/oauth2/login/success", false)
				.failureUrl("/vgreens/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}

	// Cho phép truy xuất RESP API từ bên ngoài (domain khác)
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}
