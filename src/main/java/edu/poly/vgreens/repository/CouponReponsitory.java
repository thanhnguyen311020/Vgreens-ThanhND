package edu.poly.vgreens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Coupon;

@Repository
public interface CouponReponsitory extends JpaRepository<Coupon, Integer>{
	
  public Optional<Coupon> findByNameLike(String name);

}
