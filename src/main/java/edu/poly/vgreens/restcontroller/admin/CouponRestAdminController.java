package edu.poly.vgreens.restcontroller.admin;

import java.util.Date;
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

import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Coupon;

import edu.poly.vgreens.service.CouponService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest/coupon")
public class CouponRestAdminController {
	@Autowired
	CouponService couponService;
	
	@GetMapping("list")
	public ResponseEntity<List<Coupon>>  list(){
		if (couponService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(couponService.findAll()) ; // kiem tra de dua ta status ok
	}
	@PostMapping("create")
	public ResponseEntity<Coupon> create(@RequestBody Coupon entity){
//		entity.setCreated_time(new Date());
		entity.setUpdated_time(new Date());	
		return ResponseEntity.ok(couponService.save(entity));
		
	}
	@PutMapping("{id}")
	public ResponseEntity<Coupon> update(@PathVariable("id")Integer id,
			@RequestBody Coupon entity){
		
		if(couponService.findById(id).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		entity.setUpdated_time(new Date());
		return ResponseEntity.ok(couponService.save(entity));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id ){
		
		if(!couponService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		couponService.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
}
