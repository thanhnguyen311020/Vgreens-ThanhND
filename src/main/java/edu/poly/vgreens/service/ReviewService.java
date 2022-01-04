package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.model.ProductReviewGroupByProductID;

public interface ReviewService {

	void delete(Review entity);

	void deleteById(Integer id);

	Optional<Review> findById(Integer id);

	List<Review> findAll();

	<S extends Review> S save(S entity);

	List<ProductReviewGroupByProductID> getProductReviewGroupByProductID();

	Object getCountRating1(Integer id);

	Object getCountRating2(Integer id);

	Object getCountRating3(Integer id);

	Object getCountRating4(Integer id);

	Object getCountRating5(Integer id);

	Object getCountRating(Integer id);

	List selectByRatingCount(Integer id);

	ProductReviewGroupByProductID getProductReview(Integer id);

	List findReviewByProductId(Integer id);

	Optional<Review> findByUsernameAndProduct(String username, Integer id);

	List<Review> commentProduct(Integer id);
	Optional<Review> findReviewByProductIdUsernameOrderId(Integer id, String username, Integer orderid);

}
