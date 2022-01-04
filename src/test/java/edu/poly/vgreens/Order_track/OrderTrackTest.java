package edu.poly.vgreens.Order_track;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.entity.OrderTrack;
import edu.poly.vgreens.repository.OrderTrackRepository;
import edu.poly.vgreens.service.OrderTrackService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderTrackTest {
	
	@Autowired
	private OrderTrackRepository odtrackRepo;
	

	@Test
	@Rollback(value = false)
	public void TestAddNewOrderTrack() {
		Order or = new Order();
		OrderTrack odtrack = new OrderTrack();
		or.setId(35);
		odtrack.setOrder(or);
		odtrack.setCreated_time(new Date());
		odtrack.setTitle("Đang vận chuyển hàng");
		odtrack.setDescription("Shipper nguyễn vũ lân xác đang vận chuyển");
		odtrack.setUpdated_time(null);
		
		OrderTrack odt = odtrackRepo.save(odtrack);
		Assertions.assertThat(odt).isNotNull();
        Assertions.assertThat(odt.getId()).isGreaterThan(0);
	}

}
