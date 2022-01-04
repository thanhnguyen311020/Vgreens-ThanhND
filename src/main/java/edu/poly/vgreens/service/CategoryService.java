package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Category;

public interface CategoryService {

	void delete(Category entity);

	void deleteById(Integer id);

	Optional<Category> findById(Integer id);


	<S extends Category> S save(S entity);

	List<Category> findAll();

	Category update(Category entity);

}
