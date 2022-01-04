package edu.poly.vgreens.restcontroller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.service.CountryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/country")
public class CountryRestController {
	@Autowired
	private CountryService countryService;
	
	
	@GetMapping("list")
	public ResponseEntity<List<Country>>  list(){
		if (countryService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(countryService.findAll()) ; // kiem tra de dua ta status ok
	}

}
