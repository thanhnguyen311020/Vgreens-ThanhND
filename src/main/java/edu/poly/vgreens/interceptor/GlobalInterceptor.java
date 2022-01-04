package edu.poly.vgreens.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.service.CategoryService;
import edu.poly.vgreens.service.CountryService;

@Component
public class GlobalInterceptor implements HandlerInterceptor  {
	@Autowired
	private CountryService countryService;
	@Autowired
	private CategoryService categoryService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		List<Country> listCountries = countryService.findAllByOrderByNameAsc();
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("listcountries", listCountries);
	
	}
	
	

}
