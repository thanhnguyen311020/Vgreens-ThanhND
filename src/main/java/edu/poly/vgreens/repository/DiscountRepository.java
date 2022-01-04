package edu.poly.vgreens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
	
	@Query("SELECT d From Discount d where d.discount_image !='' and d.status=1 and d.percent_discount!=0")
	List<Discount> findAllDiscountByStatusAndImage();

}
