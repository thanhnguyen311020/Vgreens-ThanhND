package edu.poly.vgreens.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vgreens/admin")
public class AdminController {
	
	@GetMapping("dashboard")
	public String dashboard(Model model) {
		return "/admin/dashboard/index";
	}
	
	@GetMapping("account-management")
	public String account(Model model) {
		
		return "/admin/account/index";
	}
	
	@GetMapping("product-management")
	public String product(Model model) {
		return "/admin/product/index";
	}
	
	@GetMapping("category-management")

	public String category(Model model) {
		return "/admin/category/index";
	}
	@GetMapping("unit-management")
	public String unit(Model model) {
		
		return "/admin/unit/index";
	}
	
	@GetMapping("consignment-management")
	public String consignment(Model model) {
		
		return "/admin/consignment/index";
	}
	
	@GetMapping("supplier-management")
	public String supplier(Model model) {
		
		return "/admin/supplier/index";
	}
	
	@GetMapping("discount-management")
	public String discount() {
		
		return "/admin/discount/index";
	}
	
	@GetMapping("authority-management")
	public String authority() {
		return "/admin/authority/index";
	}
	
	@GetMapping("product-review")
	public String reviewProduct() {
		return "/admin/review/index";
	}
	
	@GetMapping("country-management")
	public String country() {
		return "/admin/country/index";
	}
	
	@GetMapping("order-management")
	public String order() {
		return "/admin/order/index";
	}
	@GetMapping("shippingrate-management")
	public String shippingRate() {
		return "/admin/shipping_rates/index";
	}
	@GetMapping("coupon-management")
	public String coupon() {
		return "/admin/coupon/index";
	}
    @GetMapping("ordertrack-management")
    public String OrderTrack() {
        return "/admin/order_track/index";
    }
    @GetMapping("help-management")
    public String others() {
        return "/admin/others/index";
    }
    
    @GetMapping("advertisement-management")
    public String advertisement() {
        return "/admin/advertisement/index";
	}
    @GetMapping("post-management")
    public String post() {
        return "/admin/Post_foods/index";
    }

	
}
