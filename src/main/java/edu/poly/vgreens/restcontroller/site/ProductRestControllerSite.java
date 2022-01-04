package edu.poly.vgreens.restcontroller.site;

import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.repository.ProductRepository;
import edu.poly.vgreens.service.ProductService;
import edu.poly.vgreens.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/site/rest/product")
public class ProductRestControllerSite {
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepo;

	@Autowired
	ReviewService reviewService;
	@GetMapping("list")
	public ResponseEntity<List<Product>> list() {
		if (productService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productService.findAll()); // kiem tra de dua ta status ok
	}

	@GetMapping("list/discountisnull")
	public ResponseEntity<List<Product>> discountisnull() {
		if (productService.findProductByDiscountIsNull().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productService.findProductByDiscountIsNull()); // kiem tra de dua ta status ok
	}

	@GetMapping("list/discountisnotnull")
	public ResponseEntity<List<Product>> discountisnotnull() {
		if (productRepo.findProductByDiscountIsNotNull().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productRepo.findProductByDiscountIsNotNull()); // kiem tra de dua ta status ok
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) { // lay 1 san pham theo Id
		if (productService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(productService.findById(id).get());

	}
	// detail product with review
	@GetMapping("/review/{id}")
	public ResponseEntity<List> getProductReview(@PathVariable("id") Integer id) { // lay 1 san pham theo Id
		if (reviewService.findReviewByProductId(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(reviewService.findReviewByProductId(id));

	}

}
