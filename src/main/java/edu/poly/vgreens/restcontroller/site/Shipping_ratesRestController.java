package edu.poly.vgreens.restcontroller.site;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.ShippingRate;
import edu.poly.vgreens.model.CheckoutInfo;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.AddressesService;
import edu.poly.vgreens.service.CheckoutService;
import edu.poly.vgreens.service.ShippingRateService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest")
public class Shipping_ratesRestController {
	@Autowired
	private ShippingRateService shippingRateService;
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressesService addressesService;
	@Autowired
	CheckoutService checkoutService;
	
	
	@GetMapping("checkout/shippingrates")
	public ResponseEntity<ShippingRate> getShippingRatesForAccount(Authentication auth) {
		String username = auth.getName();

		Optional<Account> account = accountService.findByUsername(username);
		Optional<Addresses> addresses = addressesService.findAddressDefault(username);
		
		ShippingRate shippingRate=null;
		if (addresses.isEmpty()){
			shippingRate= shippingRateService.getShippingRateForAccount(account.get());
			


		}else {
			// lay dia chi tu address
			shippingRate= shippingRateService.getShippingRateForAddress(addresses.get());
			

		}
		return ResponseEntity.ok(shippingRate);

	}
	@GetMapping("shippingrates/findbystate/{state}")
	public ResponseEntity<CheckoutInfo> getShippingRatesForCheckout(@PathVariable("state") String state){
		ShippingRate shippingRate = shippingRateService.findShippingRateByStateLike(state);
		CheckoutInfo checkoutInfo = checkoutService.prepareCheckout(shippingRate);
		return ResponseEntity.ok(checkoutInfo);
	}

}
