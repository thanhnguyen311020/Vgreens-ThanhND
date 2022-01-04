package edu.poly.vgreens.controller.site;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.vgreens.entity.Category;
import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Discount;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.service.CategoryService;
import edu.poly.vgreens.service.ConsignmentService;
import edu.poly.vgreens.service.DiscountService;
import edu.poly.vgreens.service.ProductService;

@Controller
@RequestMapping("vgreens")
public class IndexController {
	@Autowired
	private ProductService proSer;
	@Autowired
	private DiscountService disService;
	@Autowired
	private CategoryService catesSer;
	
	@GetMapping("index")
	public String index(Model model) {
		
		List<Product> listProDiscountIsNull = proSer.findProductByDiscountIsNull();
		List<Product> listProDiscountIsNotNull = proSer.findProductWithDiscountGreaterThanZero();
		List<Product> productsSelling = proSer.productSellingTop8();
		List<Discount> listDiscount = disService.findAllDiscountByStatusAndImage();
		
		model.addAttribute("listDiscount", listDiscount);
		model.addAttribute("proSell", productsSelling);
		model.addAttribute("listproDisNull", listProDiscountIsNull);
		model.addAttribute("listproDisNotNull", listProDiscountIsNotNull);
		return "site/index/index";
	}

}
