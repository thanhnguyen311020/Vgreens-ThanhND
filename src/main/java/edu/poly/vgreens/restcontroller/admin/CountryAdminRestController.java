package edu.poly.vgreens.restcontroller.admin;

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
@RequestMapping("/vgreens/rest/countryAdmin")
public class CountryAdminRestController {
	
	@Autowired 
	CountryService countryService;
	
	@GetMapping()
	public List<Country> list(){
		
		return countryService.findAll();
	}
	
	@PostMapping()
	public Country create(@RequestBody Country country) {
		
		return countryService.save(country);
	}
	
	@PutMapping("{id}")
	public  ResponseEntity<Country>  update(@RequestBody Country country, @PathVariable("id")Integer id) {
		if(countryService.findById(id).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(countryService.save(country));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		countryService.deleteById(id);
	}
	
	@GetMapping("{id}")
	public  ResponseEntity<Country> getCountryById(@PathVariable("id")Integer id) {
		
		if(countryService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return  ResponseEntity.ok(countryService.findById(id).get());
		
	}

}
