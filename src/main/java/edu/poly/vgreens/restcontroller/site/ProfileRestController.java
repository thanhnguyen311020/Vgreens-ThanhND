package edu.poly.vgreens.restcontroller.site;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.poly.vgreens.service.CountryService;
import edu.poly.vgreens.entity.Country;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.service.AccountService;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens")
public class ProfileRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	private CountryService countryService;

	@Autowired
	HttpSession session;

	@GetMapping(value = "account/rest/profile")
	public Optional<Account> profile(Model model, Authentication auth) {
		List<Country> listCountries = countryService.findAllByOrderByNameAsc();
		model.addAttribute("listcountries", listCountries);
		String username = auth.getName();
		// String username = (String) session.getAttribute("username");
		return accountService.findByUsername(username);
	}

	@GetMapping(value = "account/rest/country")
    public List<Country> getCountry(Model model) {
        List<Country> listCountries = countryService.findAllByOrderByNameAsc();
        return listCountries;
        
	}

	@PutMapping("account/rest/profile/{username}")
	public Account getAccountByUsername(@PathVariable("username") String username, @RequestBody Account account) {
		return accountService.save(account);
	}
}
