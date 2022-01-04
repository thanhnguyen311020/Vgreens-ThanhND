package edu.poly.vgreens.restcontroller.admin;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.repository.ProductRepository;
import edu.poly.vgreens.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest/product")
public class ProductRestController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("list")
	public ResponseEntity<List<Product>>  list(){
		if (productService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productService.findAll()) ; // kiem tra de dua ta status ok
	}
	
	@PostMapping("create")
	public Product create (@RequestBody Product product) {
		
		return productService.save(product);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id")Integer id, @RequestBody Product product) {
		product.setUpdateDate(new Date());
		return productService.save(product);
	}
	
	@PutMapping("/updateAvailable/{id}")
	public Product updateAvailable(@PathVariable("id")Integer id, @RequestBody Product product) {
		return productService.updateAvailable(product);
	}
	
	
//	@GetMapping("{id}")
//	public	Product getOne(@PathVariable("id") Integer id) { //lay 1 san pham theo Id
//		return productService.findById(id).get();
//	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		productService.deleteById(id);
	}
	
	@GetMapping("{cate}")
  public List<Product> getByCate(@PathVariable("cate") Integer id) {
      return  productService.findByCate(id);
    }
	
	@GetMapping("/units/{unit}")
	  public List<Product> getByUnit(@PathVariable Integer unit) {
	      return  productService.findByUnit(unit);
	 }
	
	@GetMapping("availabel")
	public ResponseEntity<List<Product>>  getStatus(@RequestParam(name="availabel")String status){
//		if (productService.findAll().isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
		boolean st = false;
		if(status.equals("1")) {
			st = true;
		}
		return ResponseEntity.ok(productService.findByStatus(st)) ; // kiem tra de dua ta status ok
	}
	
	@GetMapping("countProductLessThan10")
	Long countProductByCurrentQuantityLessThan10() {
		return productService.countProductCurrenQuantity();
	}
	
//	@GetMapping("/availabe/AVA/{availabe}")
//  public List<Product> getByAvailable(@PathVariable("availabe") Boolean available) {
//      return productService.findByAvailable(available);
//    }	

}
