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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Discount;
import edu.poly.vgreens.service.DiscountService;

@RestController
@RequestMapping("/vgreens/rest/discount")
@CrossOrigin("*")
public class DiscountRestController {
	
	@Autowired
	DiscountService discountService;
	
	
	@GetMapping()
	public ResponseEntity<List<Discount>> list(){	
		return ResponseEntity.ok(discountService.findAll());		
	}
	
	@PostMapping()
	public ResponseEntity<Discount> create(@RequestBody Discount entity){
		
	
		
		return ResponseEntity.ok(discountService.save(entity));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Discount> update(@PathVariable("id")Integer id
			,@RequestBody Discount entity){
		
		if(discountService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(discountService.save(entity));
	}
	
	@PutMapping("/updateStatus/{id}")
	public Discount updateStatus(@PathVariable("id")Integer id, @RequestBody Discount discount) {
		return discountService.updateStatus(discount);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id){

		if(discountService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		discountService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Discount> getDiscountById(@PathVariable("id")Integer id){
		
		Optional<Discount> discountOpt = discountService.findById(id);
		if(discountOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(discountOpt.get());
	}
}
