package edu.poly.vgreens.Discount;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Discount;
import edu.poly.vgreens.repository.DiscountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DiscountTest {
	@Autowired
	private DiscountRepository discountRepository;
	
	@Test
	@Rollback(value = false)
	public void TestAddNewDiscount() {
		Discount ds = new Discount();
		ds.setName_discount("Ngày thiếu ni");
		ds.setPercent_discount(10);
		ds.setStartDate(new Date());
		ds.setEndDate(new Date(2021-10-25));
		ds.setStatus(true);
		ds.setUpdated_time(new Date());
		ds.setDiscount_description("Lan tao");
		
		
		
		Discount discount = discountRepository.save(ds);
		Assertions.assertThat(discount).isNotNull();
        Assertions.assertThat(discount.getId()).isGreaterThan(0);
	}

}
