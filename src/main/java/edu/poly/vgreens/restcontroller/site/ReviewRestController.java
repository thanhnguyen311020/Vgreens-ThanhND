package edu.poly.vgreens.restcontroller.site;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.service.ReviewService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vgreens/rest")
public class ReviewRestController {
    @Autowired
    ReviewService reviewService;
    @GetMapping(value = "/comment/{id}")
    public List<Review> getCommentProduct(@PathVariable Integer id) {
        return reviewService.commentProduct(id);
    }

    @PostMapping(value = "/review/{id}")
    public Review postReviewProduct(@RequestBody Review review, @PathVariable Integer id, Authentication auth) {
        String username = auth.getName();
        review.setUsername(username);
        Product product = new Product();
        product.setId(id);
        review.setProduct(product);
        review.setTitle("Review");
        review.setCreated_time(new Date());
        review.setUpdated_time(new Date());
        return reviewService.save(review);
    }
    @PostMapping(value = "/comment/{id}")
    public Review postCommentProduct(@RequestBody Review review, @PathVariable Integer id, Authentication auth) {
        String username = auth.getName();
        review.setUsername(username);
        Product product = new Product();
        product.setId(id);
        review.setProduct(product);
        review.setTitle("Comment");
        review.setRating((float) 0);
        review.setCreated_time(new Date());
        review.setUpdated_time(new Date());
        return reviewService.save(review);
    }
	@PostMapping(value = "/review/{order_id}/{product_id}")
	public Review postReviewProduct(@RequestBody Review review, @PathVariable Integer product_id,
			@PathVariable Integer order_id, Authentication auth) {
		String username = auth.getName();
		
		Optional<Review> rvOpt = reviewService.findReviewByProductIdUsernameOrderId(product_id, username, order_id);
		
		if (rvOpt.isEmpty()) {
			review.setUsername(username);
			Product product = new Product();
			product.setId(product_id);
			review.setProduct(product);
			review.setTitle("Review");
			review.setCreated_time(new Date());
			review.setUpdated_time(new Date());
			review.setOrder_id(order_id);
			return reviewService.save(review);
		}else {
			Review rv = rvOpt.get();
			rv.setComment(review.getComment());
			rv.setUpdated_time(new Date());
			rv.setRating(review.getRating());
			return reviewService.save(rv);
		
		}
		
	}
    
    
}
