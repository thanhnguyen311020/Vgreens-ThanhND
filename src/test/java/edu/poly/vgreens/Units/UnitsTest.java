package edu.poly.vgreens.Units;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Unit;
import edu.poly.vgreens.repository.UnitRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UnitsTest {
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Test
	@Rollback(value = false)
	public void TestAddNewUnit() {
		Unit unit = new Unit();
		unit.setName("Hạt");
		unit.setDescription("Số lượng hạt");
		unit.setCreated_time(new Date());
		unit.setUpdated_time(new Date());
		
		Unit unitsave = unitRepository.save(unit);
		Assertions.assertThat(unitsave).isNotNull();
        Assertions.assertThat(unitsave.getId()).isGreaterThan(0);
	}

}
