package edu.poly.vgreens.consignment;

import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Supplier;
import edu.poly.vgreens.repository.ConsignmentRepository;
import edu.poly.vgreens.service.ConsignmentService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConsignmentTest {

	@Autowired
	private ConsignmentRepository consignmentService;

	@Test
	@Rollback(value = false)
	public void AddNewConsiTest() {
		Consignment co = new Consignment();
		Supplier sup = new Supplier();

		co.setName("LH01");
		co.setPurchase_price((float) 100000);
		co.setSelling_price((float) 120000);
		co.setNumberOfInputs(20);
		co.setNumberOfSales(5);
		co.setNumberOfReturns(2);
		co.setCurrentQuantity(13);
		co.setCreated_time(new Date());
		co.setUpdated_time(new Date());
		co.setExpiry_time(new Date());
		co.setStatus(true);
		sup.setId(1);
		co.setSupplier(sup);

		Consignment con = consignmentService.save(co);
		Assertions.assertThat(con).isNotNull();
		Assertions.assertThat(con.getId()).isGreaterThan(0);

	}
}
