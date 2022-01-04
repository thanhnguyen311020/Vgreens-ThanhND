package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.vgreens.entity.Coupon;

public interface CouponService {

	List<Coupon> findAll();

	void deleteAll();

	void delete(Coupon entity);

	void deleteById(Integer id);

	long count();

	<S extends Coupon> List<S> saveAll(Iterable<S> entities);

	Optional<Coupon> findById(Integer id);

	List<Coupon> findAllById(Iterable<Integer> ids);

	Page<Coupon> findAll(Pageable pageable);

	<S extends Coupon> Optional<S> findOne(Example<S> example);

	<S extends Coupon> S save(S entity);

	Optional<Coupon> findByNameLike(String name);

	

}
