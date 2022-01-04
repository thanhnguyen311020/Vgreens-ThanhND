package edu.poly.vgreens.restcontroller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.service.OrderDetailService;
import edu.poly.vgreens.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vgreens/rest")
public class OrderDetailRestController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping("order/{id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("id") Integer id){
		if (orderService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(orderService.findById(id).get());
	}
	

     @GetMapping("orders/orderdetail/findall/{id}")
	public ResponseEntity<List<OrderDetail>>  findallOrderDetail(@PathVariable("id") Integer id) {
	
		if (orderDetailService.findOrderDetailByOrderId(id).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderDetailService.findOrderDetailByOrderId(id));
	}

}
