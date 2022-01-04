package edu.poly.vgreens.restcontroller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Advertisement;
import edu.poly.vgreens.service.AdvertisementService;

@RequestMapping("/vgreens/rest/advertisement")
@RestController
@CrossOrigin("*")
public class AdvertisementRestController {

	@Autowired
	AdvertisementService service;
	
	@GetMapping
	public List<Advertisement> list() {
		return service.findAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<Advertisement>  create(@RequestBody Advertisement advertisement) {
//		Account entity = new Account();
//		BeanUtils.copyProperties(dto, entity);
//	if (service.getById(advertisement.getId()) == null) {
//			return ResponseEntity.badRequest().build();
//		}
	
		service.save(advertisement);
		return ResponseEntity.ok(advertisement);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")String id) {
		
		if (service.getById(id) == null ){
			return ResponseEntity.notFound().build();
		}
	
		
		
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Advertisement> update(@PathVariable("id")String id, @RequestBody Advertisement advertisement){
		if(service.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		service.save(advertisement);
		return ResponseEntity.ok(advertisement);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<Advertisement> getById(@PathVariable("id")String id){
	 Optional<Advertisement> adver = service.findById(id);
		if (adver.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(adver.get());
	}
	
}
