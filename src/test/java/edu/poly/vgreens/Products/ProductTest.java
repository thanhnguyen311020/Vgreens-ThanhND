package edu.poly.vgreens.Products;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import edu.poly.vgreens.entity.Category;
import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.entity.Unit;
import edu.poly.vgreens.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Test
	@Rollback(value = false)
	public void TestAddNewProduct() {
		Product pr = new Product();
		Category cate = new Category();
		Consignment con = new Consignment();
		Unit un = new Unit();
		pr.setName("Táo");
		pr.setDescription("Lân thêm");
		pr.setImage("92eda60d.png");
		pr.setAvailable(true);
		pr.setAvailable_discount(true);
		pr.setCreateDate(new Date());
		pr.setUpdateDate(new Date());
		cate.setId(10);
		pr.setCategory(cate);
		un.setId(2);
		pr.setUnit(un);
		con.setId(3);
		pr.setConsignment(con);
		
		
		Product product = productRepository.save(pr);
		Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isGreaterThan(0);
	}

}
