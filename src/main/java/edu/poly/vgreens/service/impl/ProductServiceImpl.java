package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.repository.ConsignmentRepository;
import edu.poly.vgreens.repository.ProductRepository;
import edu.poly.vgreens.service.ProductService;
import edu.poly.vgreens.service.UploadService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	ConsignmentRepository conRepo;

	@Override
	public List<Product> productSellingTop8() {
		return productRepository.productSellingTop8();
	}

	@Override
	public <S extends Product> S save(S entity) {
		if (entity.getId() != null) {
			Optional<Product> optProduct = productRepository.findById(entity.getId());
			if (optProduct.isPresent()) {
				if (StringUtils.isEmpty(entity.getImg())) {
					entity.setImage(optProduct.get().getImage());
				} else {
					entity.setImage(uploadService.uploadImage(entity.getImg()));
				}
			}
		}else {
			entity.setImage(uploadService.uploadImage(entity.getImg()));
//			return productRepository.save(entity);
		}

		return productRepository.save(entity);
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.getById(id);
	}

	@Override
	public Page<Product> findByCategoryId(Integer cid,  Pageable pageable) {
		return productRepository.findByCategoryId(cid, pageable);
	}

	@Override
	public List<Product> findProductByDiscountIsNull() {
		return productRepository.findProductByDiscountIsNull();
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productRepository.findByNameContaining(name, pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> findProductByDiscountIsNotNull() {
		return productRepository.findProductByDiscountIsNotNull();
	}
	
	@Override
	public List<Product> findProductWithDiscountGreaterThanZero() {
		return productRepository.findProductWithDiscountGreaterThanZero();
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}
	
	@Override
	public Product updateAvailable(Product entity) {
		if(entity.getAvailable()) {
			entity.setAvailable(false);
			return productRepository.save(entity);
		}else {
			entity.setAvailable(true);
			return productRepository.save(entity);
		}
		
	}

	@Override
	public List<Product> findByCate(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findByCate(id);
	}

	@Override
	public List<Product> findByAvailable(Boolean available) {
		// TODO Auto-generated method stub
		return productRepository.findByAvailable(available);
	}

	@Override
	public List<Product> findByUnit(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findByUnit(id);
	}

	@Override
	public List<Product> findByStatus(Boolean status) {
		return productRepository.findByStatus(status);
	}

	
	public Page<Product> findProductByNameContainingAndAndAvailableTrue(String name, Pageable pageable) {
		return productRepository.findProductByNameContainingAndAndAvailableTrue(name, pageable);
	}

	@Override
	public Page<Product> findAllByAvailableIsTrue(Pageable pageable) {
		return productRepository.findAllByAvailableIsTrue(pageable);
	}

	@Override
	@Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id where p.available=true and c.selling_price >=?1 and c.selling_price <= ?2 order by c.selling_price desc", nativeQuery = true)
	public Page<Product> findProductByPriceBetwen(Float price1, Float price2, Pageable pageable) {
		return productRepository.findProductByPriceBetwen(price1, price2, pageable);
	}

	@Override
	@Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id  where p.available=true order by c.selling_price desc", nativeQuery = true)
	public Page<Product> findProductBySellingPriceDesc(Pageable pageable) {
		return productRepository.findProductBySellingPriceDesc(pageable);
	}

	@Override
	@Query(value = "SELECT * FROM Products p inner join Consignments c on p.consignment_id=c.id  where p.available=true order by c.selling_price asc", nativeQuery = true)
	public Page<Product> findProductBySellingPriceAsc(Pageable pageable) {
		return productRepository.findProductBySellingPriceAsc(pageable);
	}

	@Override
	@Query("SELECT p FROM Product p where p.available=true ORDER BY p.name DESC ")
	public Page<Product> findProductOrderByNameDesc(Pageable pageable) {
		return productRepository.findProductOrderByNameDesc(pageable);
	}

	@Override
	@Query("SELECT p FROM Product p where p.available=true ORDER BY p.name ASC ")
	public Page<Product> findProductOrderByNameAsc(Pageable pageable) {
		return productRepository.findProductOrderByNameAsc(pageable);
	}

	@Override
	public Page<Product> findProductByDiscountIsNotNullAndAndAvailableIsTrue(Pageable pageable) {
		return productRepository.findProductByDiscountIsNotNullAndAndAvailableIsTrue(pageable);
	}

	@Override
	public Long countProductCurrenQuantity() {
		return productRepository.countProductCurrenQuantity();
	}
	
	@Override
	public Page<Product> findProductByDiscountId(Integer discountid, Pageable pageable) {
		return productRepository.findProductByDiscountId(discountid,pageable);
	}
	

		
	
	

}
