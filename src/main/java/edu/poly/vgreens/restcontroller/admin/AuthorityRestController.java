package edu.poly.vgreens.restcontroller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Authority;
import edu.poly.vgreens.service.AuthorityService;

@RestController
@RequestMapping("/vgreens/rest/authorities")
@CrossOrigin("*")
public class AuthorityRestController {
	
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping
	public List<Authority> findALl(@RequestParam("admin")Optional<Boolean> admin){
		
		if(admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		
		return authorityService.findAll();
	}
	
	@PostMapping
	public Authority create(@RequestBody Authority auth) {
		return authorityService.save(auth);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		authorityService.deleteById(id);
	}
	
}
