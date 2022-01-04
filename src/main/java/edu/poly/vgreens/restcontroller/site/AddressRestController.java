package edu.poly.vgreens.restcontroller.site;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.service.AddressesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/address")
public class AddressRestController {

	
	@Autowired
	private AddressesService addressesService;
	
	@GetMapping("{id}")
	public  ResponseEntity<Addresses> getAddressById(@PathVariable("id")Integer id) {
		
		Optional<Addresses> addressOpt = addressesService.findById(id);
		if (!addressOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(addressOpt.get());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Addresses> updateAddressById(@PathVariable("id")Integer id,
			@RequestBody Addresses address) {
		
		if (!addressesService.existsById(address.getId())) {
			return ResponseEntity.notFound().build();
		}
	
		
		addressesService.save(address);
		return ResponseEntity.ok(address);
	}
	
	@PostMapping("")
	public  ResponseEntity<Addresses> addNewAddress(@RequestBody Addresses addresses,Authentication auth) {
		Account ac = new Account();
		ac.setUsername(auth.getName());
		addresses.setAddress_default(false);
		addresses.setAccount(ac);
		return ResponseEntity.ok(addressesService.save(addresses));
		
	}
	
		
	
}
