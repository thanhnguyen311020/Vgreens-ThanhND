package edu.poly.vgreens.restcontroller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.service.CountryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/countrysite")
public class CountrySiteRestController {
	
	@Autowired 
	CountryService countryService;
	
	@GetMapping()
	public List<Country> list(){
		
		return countryService.findAll();
	}
	
	

}
