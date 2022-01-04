package edu.poly.vgreens.restcontroller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.model.ProductReviewGroupByProductID;
import edu.poly.vgreens.service.ReviewService;

@RestController
@RequestMapping("/vgreens/rest/product-review")
@CrossOrigin("*")
public class ProductReviewRestController {

	@Autowired
	ReviewService reviewService;

	@GetMapping()
	public List<Review> list() {

		return reviewService.findAll();
	}

	@GetMapping("/group-product")
	public List<ProductReviewGroupByProductID> listProductByID() {

		return reviewService.getProductReviewGroupByProductID();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		reviewService.deleteById(id);
	}

}
