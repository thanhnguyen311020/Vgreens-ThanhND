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

import edu.poly.vgreens.entity.ShippingRate;
import edu.poly.vgreens.service.ShippingRateService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest/shippingRate")
public class ShippingRateAdminRestController {
	@Autowired
	ShippingRateService shippingrateService;
	
	@GetMapping("list")
	public ResponseEntity<List<ShippingRate>>  list(){
		if (shippingrateService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(shippingrateService.findAll()) ; // kiem tra de dua ta status ok
	}
	@PostMapping("create")
	public ShippingRate create (@RequestBody ShippingRate shippingRate) {		
		return shippingrateService.save(shippingRate);
	}
	
	@PutMapping("{id}")
	public ShippingRate update(@PathVariable("id")Integer id, @RequestBody ShippingRate shippingRate) {
		return shippingrateService.save(shippingRate);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		shippingrateService.deleteById(id);
	}
	@PutMapping("/updateCodSupported/{id}")
	public ShippingRate updateCodSupported(@PathVariable("id")Integer id, @RequestBody ShippingRate shippingRate) {
		return shippingrateService.updateCodSupported(shippingRate);
	}
}
