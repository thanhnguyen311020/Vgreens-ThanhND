package edu.poly.vgreens.controller.site;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.ShippingRate;
import edu.poly.vgreens.model.CheckoutInfo;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.AddressesService;
import edu.poly.vgreens.service.CheckoutService;
import edu.poly.vgreens.service.OrderService;
import edu.poly.vgreens.service.ShippingRateService;





@Controller
@RequestMapping("vgreens")
public class CheckoutController {
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
	
	@GetMapping("cart/checkout")
	public String Checkout(Model model, Authentication auth) {
		String username = auth.getName();
		
		Optional<Account> account =accountService.findByUsername(username);
		Optional<Addresses> addresses = addressesService.findAddressDefault(username);
		
		ShippingRate shippingRate=null;
		boolean usePrimaryAddressAsDefault = false;
		
		// lay dia chi tu address neu khong co address thi lay dia chi tu account
		if (addresses.isEmpty()){
			shippingRate= shippingRateService.getShippingRateForAccount(account.get());
			model.addAttribute("address",account.get());


		}else {
			// lay dia chi tu address
			shippingRate= shippingRateService.getShippingRateForAddress(addresses.get());
			model.addAttribute("address",addresses.get());

		}


		CheckoutInfo checkoutInfo = checkoutService.prepareCheckout(shippingRate);
		model.addAttribute("checkoutinfo", checkoutInfo);
		model.addAttribute("account",account.get());//lay email cho paypal
		return "site/checkout/billingDetails";
	}
	
	@GetMapping("cart/checkout/confirmorder")
	public String confirmorder(Model model, Authentication auth) {
		return "site/checkout/confirmOrder";
		
	}

}
