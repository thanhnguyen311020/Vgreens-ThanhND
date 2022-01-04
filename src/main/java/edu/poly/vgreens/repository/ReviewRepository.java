package edu.poly.vgreens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.model.ProductReviewGroupByProductID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

//	@Query("SELECT SUM(r.rating) FROM Review r group by r.product.id")
//	Double getSUMRating();
	
	@Query("SELECT new edu.poly.vgreens.model.ProductReviewGroupByProductID(r.product, (SUM(r.rating)/COUNT(r.rating))) FROM Review r "
			+ " group by r.product.id order by  (SUM(r.rating)/COUNT(r.rating))")
	List<ProductReviewGroupByProductID> getProductReviewGroupByProductID();
@Query(value = "SELECT * FROM vgreens.product_reviews  WHERE  username=?1 and product_id=?2", nativeQuery = true)
	Optional<Review> findByUsernameAndProduct(String username, Integer id);

	@Query(value = "select a.username , a.first_name ,a.last_name  ,a.photo, r.id ,r.product_id , r.title,r.comment,r.rating,r.created_time FROM vgreens.accounts a join vgreens.product_reviews r where a.username=r.username  and r.product_id=?1", nativeQuery = true)
	List findReviewByProductId(Integer id);

	@Query("SELECT new edu.poly.vgreens.model.ProductReviewGroupByProductID(p, (SUM(r.rating)/COUNT(r.rating))) FROM  Product p LEFT JOIN Review r on p.id=r.product.id"
			+ " where p.id=?1 group by p.id")
	ProductReviewGroupByProductID getProductReview(Integer id);

	@Query(value = "SELECT count(rating),rating FROM vgreens.product_reviews  WHERE  product_id=?1 group by rating", nativeQuery = true)
	List selectByRatingCount(Integer id);

	@Query(value = "SELECT count(rating) FROM vgreens.product_reviews  WHERE  product_id=?1", nativeQuery = true)
	Object getCountRating(Integer id);

	@Query(value = "SELECT count(rating),rating FROM vgreens.product_reviews  WHERE  product_id=?1 and rating =5", nativeQuery = true)
	Object getCountRating5(Integer id);

	@Query(value = "SELECT count(rating),rating FROM vgreens.product_reviews  WHERE  product_id=?1 and rating =4", nativeQuery = true)
	Object getCountRating4(Integer id);

	@Query(value = "SELECT count(rating),rating FROM vgreens.product_reviews  WHERE  product_id=?1 and rating =3", nativeQuery = true)
	Object getCountRating3(Integer id);

	@Query(value = "SELECT count(rating),rating FROM vgreens.product_reviews  WHERE  product_id=?1 and rating =2", nativeQuery = true)
	Object getCountRating2(Integer id);

	@Query(value = "SELECT count(rating),rating FROM vgreens.product_reviews  WHERE  product_id=?1 and rating =1", nativeQuery = true)
	Object getCountRating1(Integer id);
	// comment product
	@Query(value="SELECT * FROM vgreens.product_reviews where title='Comment' and product_id=?1", nativeQuery = true)
	List<Review> commentProduct(Integer id);
	
	@Query(value="SELECT * FROM vgreens.product_reviews r where r.product_id =?1 and r.username =?2 and r.order_id=?3", nativeQuery = true)
	Optional<Review> findReviewByProductIdUsernameOrderId(Integer id, String username, Integer orderid);
}
