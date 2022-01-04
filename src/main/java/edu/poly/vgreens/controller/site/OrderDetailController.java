package edu.poly.vgreens.controller.site;

import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.entity.OrderTrack;
import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.vgreens.service.OrderService;
import edu.poly.vgreens.service.OrderTrackService;

import java.util.List;

@Controller
@RequestMapping("vgreens")
public class OrderDetailController {
	@Autowired
	private OrderService orderSer;
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderTrackService odtrackSer;
	
	@GetMapping("orderdetail/{id}")
	public String OrderDetail(@PathVariable("id") Integer id,Model model) {
		
		List<OrderTrack> ordertrack_list =odtrackSer.findOrderTrackByOrderIdOrderByCreated_timeDesc(id);
		
		List<OrderDetail> detailList = orderDetailService.findOrderDetailByOrderId(id);
		model.addAttribute("inforOrder", orderSer.findById(id).get());
		model.addAttribute("detailList",detailList);
		
		model.addAttribute("tracklist", ordertrack_list);
		return "site/order/orderdetail";
	}

}
