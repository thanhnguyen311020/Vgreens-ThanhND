package edu.poly.vgreens.controller.site;

import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.model2.MailModel;
import edu.poly.vgreens.service.MailerService;
import edu.poly.vgreens.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.vgreens.service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("vgreens")
public class ReceiptController {

	@Autowired
	private OrderService orderSer;
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	MailerService mail;
	
	
	@GetMapping("order/receipt/{id}")
	public String Receipt(@PathVariable("id") Integer id,Model model, MailModel request) {
		
		List<OrderDetail> oddetail = orderDetailService.findOrderDetailByOrderId(id);
		
		
		
		
		
		Date today = new Date();
		model.addAttribute("oddetail",oddetail);
		model.addAttribute("gettoday",today);
		model.addAttribute("orderreceipt", orderSer.findById(id).get());
		
		
		
		
		
		
		return "site/receipt/receipt";
	}
}
