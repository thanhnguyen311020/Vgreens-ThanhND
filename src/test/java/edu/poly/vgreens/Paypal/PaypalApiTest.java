package edu.poly.vgreens.Paypal;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PaypalApiTest {
	private static final String BASE_URL = "https://api.sanbox.paypal.com";
	private static final String GET_ORDER_API = "/v2/checkout/orders/";
	private static final String CLIENT_ID = "AU-ylVeFxcA_EbmX5p2kj0qcvDpTh7JMAeOUNWGhU4a5akHCvdsDDCUcjvQiCEeLpYGqaMFGd8D17ddl";
	private static final String CLIENT_SECRET = "EM3qHUSCKyo-YvBQoE73PPH1GjTPDL4O1t1mLw6logjX963pgpy7MIogbBl5VGFBTueeeKVyVV8U9Xi7";
	
	
	@Test
	public void testGetOderDetails() {
		String orderId= "2JY67841MX914432V";
		String requestURL = BASE_URL + GET_ORDER_API+ orderId;
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Accept-Language", "en_US");
		headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
		RestTemplate restTempalte = new RestTemplate();
		
		ResponseEntity<String> response = restTempalte.exchange(requestURL, HttpMethod.GET, request,String.class);
		
		System.out.println(response);
	}

}
