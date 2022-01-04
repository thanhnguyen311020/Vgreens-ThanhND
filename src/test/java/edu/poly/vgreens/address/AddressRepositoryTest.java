package edu.poly.vgreens.address;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.repository.AddressesRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AddressRepositoryTest {
	
	 @Autowired
	    private AddressesRepository addressesRepository;

	    @Test
	    @Rollback(value = false)
	    public void TestAddressNew(){
	        String username = "landemo1";
	        Account account = new Account();
	        Country country = new Country();
	        account.setUsername(username);
	        Addresses addresses = new Addresses();
	        addresses.setAccount(account);
	        addresses.setFirst_name("Lan Vu2");
	        addresses.setLast_name("Nguyen");
	        addresses.setAddress_line("08 Hà Văn Tính222");
	       
	        addresses.setCity("Đà Nẵng");
	        addresses.setState("Hòa khánh");
	        country.setId(1);
	        addresses.setCountry(country);
	        addresses.setPostal_code("05555");
	        addresses.setPhone_number("032144455");
	        addresses.setAddress_default(false);

	        Addresses saveAddress = addressesRepository.save(addresses);

	        Assertions.assertThat(saveAddress).isNotNull();
	        Assertions.assertThat(saveAddress.getId()).isGreaterThan(0);

	    }

}
