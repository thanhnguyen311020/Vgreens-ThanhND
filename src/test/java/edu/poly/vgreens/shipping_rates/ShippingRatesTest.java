package edu.poly.vgreens.shipping_rates;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.ShippingRate;
import edu.poly.vgreens.repository.ShippingRateRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ShippingRatesTest {
	
	@Autowired
	private ShippingRateRepository shippingRateRepo;
	
	@Test
	public void testAddnewShippingRates() {
		ShippingRate rate = new ShippingRate();
		Country ct= new Country();
		ct.setId(2);
		rate.setCodSupported(true);
		rate.setCountry(ct);
		rate.setDays(4);
		rate.setState("Đại Lộc");
		rate.setRate(7);
		
		
		ShippingRate saveRate = shippingRateRepo.save(rate);
		 Assertions.assertThat(saveRate).isNotNull();
	        Assertions.assertThat(saveRate.getId()).isGreaterThan(0);
	}
	
//	@Test
//	public void testFindByCountryAndState() {
//		
//		Country dn = new Country();
//		dn.setId(1);
//		String state = "Liên Chiểu";
//		ShippingRate shippingRate = shippingRateRepo.findByCountryAndState(dn, state);
//	
//		assertThat(shippingRate).isNotNull();
//		
//	
//	}

}
