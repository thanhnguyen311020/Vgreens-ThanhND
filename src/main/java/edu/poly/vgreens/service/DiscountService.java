package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Discount;

public interface DiscountService {

	Discount getById(Integer id);

	void delete(Discount entity);

	void deleteById(Integer id);

	Optional<Discount> findById(Integer id);

	List<Discount> findAll(Sort sort);

	List<Discount> findAll();

	<S extends Discount> S save(S entity);

	Discount updateStatus(Discount entity);

	List<Discount> findAllDiscountByStatusAndImage();


}
