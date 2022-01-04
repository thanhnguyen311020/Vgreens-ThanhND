package edu.poly.vgreens.service;

import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.ShippingRate;
import edu.poly.vgreens.model.CheckoutInfo;



@Service
public class CheckoutService {
	
	public CheckoutInfo prepareCheckout(ShippingRate shippingRate) {
		CheckoutInfo checkoutInfo = new CheckoutInfo();
		
		checkoutInfo.setDeliverDays(shippingRate.getDays());
		checkoutInfo.setCodSupported(shippingRate.isCodSupported());
		checkoutInfo.setRate(shippingRate.getRate());
		return checkoutInfo;
	}

}
