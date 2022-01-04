package edu.poly.vgreens.restcontroller.site;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.ShippingRate;
import edu.poly.vgreens.model.CheckoutInfo;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.AddressesService;
import edu.poly.vgreens.service.CheckoutService;
import edu.poly.vgreens.service.OrderService;
import edu.poly.vgreens.service.ShippingRateService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest")
public class CheckoutRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	AddressesService addressesService;
	@Autowired
	OrderService orderService;
	@Autowired
	ShippingRateService shippingRateService;
	@Autowired
	CheckoutService checkoutService;

	
	@GetMapping("checkout/account")
	public ResponseEntity<Account> getAddressForAccount(Authentication auth) {
		String username = auth.getName();

		Optional<Account> account = accountService.findByUsername(username);
		Optional<Addresses> addresses = addressesService.findAddressDefault(username);
		
		
		if (addresses.isEmpty()){
			return ResponseEntity.ok(account.get());
			


		}else {
			return null;
			

		}
		

	}
	@GetMapping("checkout/address")
	public ResponseEntity<Addresses> getAddressForAddressDefault(Authentication auth) {
		String username = auth.getName();

//		Optional<Account> account = accountService.findByUsername(username);
		Optional<Addresses> addresses = addressesService.findAddressDefault(username);
		
		
		if (addresses.isPresent()){
			return ResponseEntity.ok(addresses.get());
			


		}else {
			return null;
			

		}
		

	}
	@GetMapping("checkout/checkoutinfo")
	public ResponseEntity<CheckoutInfo> getCheckoutInfo(Authentication auth) {
		String username = auth.getName();

		Optional<Account> account = accountService.findByUsername(username);
		ShippingRate shippingRate=null;
		
		
		if (account.isPresent()){
			shippingRate= shippingRateService.getShippingRateForAccount(account.get());
			


		}else {
			
			return null;

		}
		CheckoutInfo checkoutInfo = checkoutService.prepareCheckout(shippingRate);
		return ResponseEntity.ok(checkoutInfo);
		

	}


}
