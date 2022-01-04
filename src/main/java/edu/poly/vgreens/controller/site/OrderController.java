package edu.poly.vgreens.controller.site;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("vgreens")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@GetMapping("account/orders/findall")
	public String FindAllOrderByUsername(Authentication auth, Model model) {
		String username = auth.getName();
		List<Order> listorder =orderService.findAllByAccountUsername(username);

		model.addAttribute("listorder",listorder);


		return "site/order/order";
	}

}
