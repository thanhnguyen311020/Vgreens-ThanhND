package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Post_foods;

public interface Post_foodsService {

	Post_foods getById(Integer id);

	void deleteById(Integer id);

	Optional<Post_foods> findById(Integer id);

	List<Post_foods> findAll(Sort sort);

	<S extends Post_foods> S save(S entity);

	Post_foods update(Post_foods entity);

	List<Post_foods> findAll();

	Optional<Post_foods> findPostFoodsByProductId(Integer id);

}
