package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Coupon;
import edu.poly.vgreens.repository.CouponReponsitory;
import edu.poly.vgreens.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
	@Autowired
	CouponReponsitory couponReponsitory;

	@Override
	public List<Coupon> findAll() {
		// TODO Auto-generated method stub
		return couponReponsitory.findAll();
	}

	@Override
	public <S extends Coupon> S save(S entity) {
		return couponReponsitory.save(entity);
	}

	@Override
	public <S extends Coupon> Optional<S> findOne(Example<S> example) {
		return couponReponsitory.findOne(example);
	}

	@Override
	public Page<Coupon> findAll(Pageable pageable) {
		return couponReponsitory.findAll(pageable);
	}

	@Override
	public List<Coupon> findAllById(Iterable<Integer> ids) {
		return couponReponsitory.findAllById(ids);
	}

	@Override
	public Optional<Coupon> findById(Integer id) {
		return couponReponsitory.findById(id);
	}

	@Override
	public <S extends Coupon> List<S> saveAll(Iterable<S> entities) {
		return couponReponsitory.saveAll(entities);
	}


	@Override
	public long count() {
		return couponReponsitory.count();
	}

	@Override
	public void deleteById(Integer id) {
		couponReponsitory.deleteById(id);
	}

	@Override
	public void delete(Coupon entity) {
		couponReponsitory.delete(entity);
	}


	@Override
	public void deleteAll() {
		couponReponsitory.deleteAll();
	}

	@Override
	public Optional<Coupon> findByNameLike(String name) {
		return couponReponsitory.findByNameLike(name);
	}



	
}
