package edu.poly.vgreens.controller.site;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Post_foods;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.Post_foodsService;
import edu.poly.vgreens.service.ProductService;
import edu.poly.vgreens.service.ReviewService;

@Controller
@RequestMapping("vgreens")
public class ProductDetailController {
	@Autowired
	private ProductService proSer;
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private Post_foodsService postfoodsService;

	@Autowired
	private AccountService accountService;
	static String[] suffix = new String[] { "", "K", "M", "B", "T" };
    static int MAX_LENGTH = 4;
	@GetMapping("product/detail/{id}")
	public String Detail(Model model, @PathVariable("id") Integer id) {
		Optional<Product> detailitem = proSer.findById(id);
		Optional<Post_foods> postfoods= postfoodsService.findPostFoodsByProductId(id);
		
		if (postfoods.isEmpty()) {
			Post_foods pf = new Post_foods();
			pf.setPost_description("1");
			model.addAttribute("postfoods", pf);
			
			
			
			
			model.addAttribute("prodetail", detailitem.get());
			model.addAttribute("review", reviewService.getProductReview(id));
		    model.addAttribute("reviewPr", reviewService.findReviewByProductId(id));
			
			model.addAttribute("rating", reviewService.selectByRatingCount(id));
			Object number = reviewService.getCountRating(id);
			model.addAttribute("r",format(number));
			model.addAttribute("Sumrat", reviewService.getCountRating(id));
			Object[] rating1 = (Object[]) reviewService.getCountRating1(id);
			model.addAttribute("rating1", rating1);
			model.addAttribute("r1", format(rating1[0]));
			Object[] rating2 = (Object[]) reviewService.getCountRating2(id);
			model.addAttribute("rating2", rating2);
			model.addAttribute("r2", format(rating2[0]));
			Object[] rating3 = (Object[]) reviewService.getCountRating3(id);
			model.addAttribute("rating3", rating3);
			model.addAttribute("r3", format(rating3[0]));
			Object[] rating4 = (Object[]) reviewService.getCountRating4(id);
			model.addAttribute("rating4", rating4);
			model.addAttribute("r4", format(rating4[0]));
			Object[] rating5 = (Object[]) reviewService.getCountRating5(id);
			model.addAttribute("rating5", rating5);
			model.addAttribute("r5", format(rating5[0]));
			return "site/detail/detail";
		}else {
			model.addAttribute("postfoods", postfoods.get());
			
			
			model.addAttribute("prodetail", detailitem.get());
			model.addAttribute("review", reviewService.getProductReview(id));
		    model.addAttribute("reviewPr", reviewService.findReviewByProductId(id));
			
			model.addAttribute("rating", reviewService.selectByRatingCount(id));
			Object number = reviewService.getCountRating(id);
			model.addAttribute("r",format(number));
			model.addAttribute("Sumrat", reviewService.getCountRating(id));
			Object[] rating1 = (Object[]) reviewService.getCountRating1(id);
			model.addAttribute("rating1", rating1);
			model.addAttribute("r1", format(rating1[0]));
			Object[] rating2 = (Object[]) reviewService.getCountRating2(id);
			model.addAttribute("rating2", rating2);
			model.addAttribute("r2", format(rating2[0]));
			Object[] rating3 = (Object[]) reviewService.getCountRating3(id);
			model.addAttribute("rating3", rating3);
			model.addAttribute("r3", format(rating3[0]));
			Object[] rating4 = (Object[]) reviewService.getCountRating4(id);
			model.addAttribute("rating4", rating4);
			model.addAttribute("r4", format(rating4[0]));
			Object[] rating5 = (Object[]) reviewService.getCountRating5(id);
			model.addAttribute("rating5", rating5);
			model.addAttribute("r5", format(rating5[0]));
			return "site/detail/detail";
			
		}
		
		
		
	}

	private static String format(Object number) {
    String r = new DecimalFormat("##0E0").format(number);
    r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
    while(r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")){
        r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
    }
	return r;

}
}
