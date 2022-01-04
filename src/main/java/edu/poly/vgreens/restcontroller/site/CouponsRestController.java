package edu.poly.vgreens.restcontroller.site;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.Coupon;
import edu.poly.vgreens.service.CouponService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vgreens/rest/site")
public class CouponsRestController {
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping("coupon/{name}")
	public ResponseEntity<Coupon> findcouponByName(@PathVariable("name") String name){
		Optional<Coupon> cp = couponService.findByNameLike(name);
		
		if (cp.isEmpty()) {
			 return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cp.get());
	}

}
