package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductService {

	void delete(Product entity);

	void deleteById(Integer id);

	Optional<Product> findById(Integer id);

	List<Product> findAll(Sort sort);

    List<Product> findProductByDiscountIsNull();

    List<Product> findProductByDiscountIsNotNull();

    List<Product> findAll();

	<S extends Product> S save(S entity);

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	Page<Product> findByCategoryId(Integer cid, Pageable pageable);

	Product getById(Integer id);

	Product updateAvailable(Product entity);

	List<Product> findByCate(Integer id);

	List<Product> findByAvailable(Boolean available);

	List<Product> findByUnit(Integer id);

	List<Product> findByStatus(Boolean status);

	

	


    Page<Product> findProductByNameContainingAndAndAvailableTrue(String name, Pageable pageable);

    Page<Product> findAllByAvailableIsTrue(Pageable pageable);

    @Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id where p.available=true and c.selling_price >=?1 and c.selling_price <= ?2 order by c.selling_price desc", nativeQuery = true)
    Page<Product> findProductByPriceBetwen(Float price1, Float price2, Pageable pageable);

    @Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id  where p.available=true order by c.selling_price desc", nativeQuery = true)
    Page<Product> findProductBySellingPriceDesc(Pageable pageable);

    @Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id  where p.available=true order by c.selling_price asc", nativeQuery = true)
    Page<Product> findProductBySellingPriceAsc(Pageable pageable);



	@Query("SELECT p FROM Product p where p.available=true ORDER BY p.name DESC ")
	Page<Product> findProductOrderByNameDesc(Pageable pageable);

	@Query("SELECT p FROM Product p where p.available=true ORDER BY p.name ASC ")
	Page<Product> findProductOrderByNameAsc(Pageable pageable);

	Page<Product> findProductByDiscountIsNotNullAndAndAvailableIsTrue(Pageable pageable);

	List<Product> productSellingTop8();

	List<Product> findProductWithDiscountGreaterThanZero();

	Long countProductCurrenQuantity();

	Page<Product> findProductByDiscountId(Integer discountid, Pageable pageable);


	
}
