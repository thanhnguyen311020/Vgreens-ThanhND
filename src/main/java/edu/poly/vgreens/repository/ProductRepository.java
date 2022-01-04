package edu.poly.vgreens.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Post_foods;
import edu.poly.vgreens.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


	List<Product> findProductByDiscountIsNull();
	List<Product> findProductByDiscountIsNotNull();
	Page<Product> findByNameContaining(String name, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.discount.percent_discount > 0")
	List<Product> findProductWithDiscountGreaterThanZero();

	Page<Product> findProductByNameContainingAndAndAvailableTrue(String name, Pageable pageable);

	Page<Product> findAllByAvailableIsTrue(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.id=?1 and p.available=true")
	Page<Product> findByCategoryId(Integer cid, Pageable pageable);
	@Query("SELECT DISTINCT p FROM Product p WHERE p.category.id=?1 and p.available=true")
	List<Product> findByCate(Integer id);
	@Query("SELECT p FROM Product p WHERE p.available.id=?1")
	List<Product> findByAvailable(Boolean available);
	
	@Query("SELECT p FROM Product p WHERE p.unit.id=?1")
	List<Product> findByUnit(Integer id);
	
	@Query("SELECT p FROM Product p WHERE p.available = ?1")
	List<Product> findByStatus(Boolean status);
	
	@Query("SELECT COUNT(p) FROM Product p WHERE p.consignment.currentQuantity < 10")
	Long countProductCurrenQuantity();
	
//	@Query("SELECT p FROM Product p order by p.consignment.numberOfInputs asc")
//	List<Product> getProductBy
	
	

	@Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id where p.available=true and c.selling_price >= ?1 and c.selling_price <= ?2 order by c.selling_price desc",nativeQuery = true)
	Page<Product> findProductByPriceBetwen(Float price1, Float price2, Pageable pageable); //tim gia beetwen


	@Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id  where p.available=true order by c.selling_price desc",nativeQuery = true)
	Page<Product> findProductBySellingPriceDesc(Pageable pageable); //tim product theo gia tien giam dan

	@Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id  where p.available=true order by c.selling_price asc",nativeQuery = true)
	Page<Product> findProductBySellingPriceAsc(Pageable pageable); //tim product theo gia tien tang dan

	@Query("SELECT p FROM Product p where p.available=true ORDER BY p.name DESC ")
	Page<Product> findProductOrderByNameDesc(Pageable pageable);

	@Query("SELECT p FROM Product p where p.available=true ORDER BY p.name ASC ")
	Page<Product> findProductOrderByNameAsc(Pageable pageable);

	Page<Product> findProductByDiscountIsNotNullAndAndAvailableIsTrue(Pageable pageable);
	
	// product selling
	@Query(value="select p.* from vgreens.orderdetails d inner join vgreens.products p on d.productid=p.id group by d.productid order by sum(d.quantity) desc limit 8",nativeQuery = true)
	List<Product> productSellingTop8();
	
	
	@Query("SELECT p From Product p where p.consignment.id =?1")
	Product findProductByConsId(Integer id);
	
	@Query("SELECT p From Product p where p.available=true and p.discount.id= ?1")
	Page<Product> findProductByDiscountId(Integer discountid, Pageable pageable);
	
	


}