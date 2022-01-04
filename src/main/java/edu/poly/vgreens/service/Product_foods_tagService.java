package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Product_foods_tag;
import edu.poly.vgreens.repository.Product_Post_tagRepository;

public interface Product_foods_tagService {

	Product_foods_tag getById(Integer id);

	void deleteById(Integer id);

	Optional<Product_foods_tag> findById(Integer id);

	List<Product_foods_tag> findAll(Sort sort);

	List<Product_foods_tag> findAll();

	<S extends Product_foods_tag> S save(S entity);


}
