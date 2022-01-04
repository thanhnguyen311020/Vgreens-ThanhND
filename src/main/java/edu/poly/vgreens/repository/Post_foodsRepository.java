package edu.poly.vgreens.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Post_foods;

@Repository
public interface Post_foodsRepository extends JpaRepository<Post_foods, Integer> {

	@Query(value ="SELECT * From Post_foods f inner join Product_foods_tag t ON f.postfoods_Id = t.postfoods_Id Where t.id =?1 Order By f.post_view DESC limit 1", nativeQuery=true)
	Optional<Post_foods> findPostFoodsByProductId(Integer id);
}
