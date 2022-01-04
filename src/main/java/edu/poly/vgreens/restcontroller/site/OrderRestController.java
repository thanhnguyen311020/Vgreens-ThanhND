package edu.poly.vgreens.restcontroller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.model2.MailModel;

import edu.poly.vgreens.service.MailerService;
import edu.poly.vgreens.service.OrderService;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vgreens/rest")
public class OrderRestController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	MailerService mail;

	@PostMapping("orders")
	public ResponseEntity<Order> createOrder(@RequestBody JsonNode orderData ){
		
		return ResponseEntity.ok(orderService.create(orderData));
	}

	@GetMapping("orders/findall")
	public ResponseEntity<List<Order>> findallOrder(Authentication auth) {
		String username = auth.getName();
		if (orderService.findAllByAccountUsername(username).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findAllByAccountUsername(username));
	}
	
	@GetMapping("orders/countallorders")
	public ResponseEntity<Integer> findOrderCountAll(Authentication auth) {
		String username = auth.getName();
		if (orderService.findCountAllOrder(username) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findCountAllOrder(username));
	}

	@GetMapping("orders/countstatus5")
	public ResponseEntity<Integer> findOrderCountStatus0(Authentication auth) {
		String username = auth.getName();
		if (orderService.findCountOrderByStatus5(username) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findCountOrderByStatus5(username));
	}

	@GetMapping("orders/countstatus1")
	public ResponseEntity<Integer> findOrderCountStatus1(Authentication auth) {
		String username = auth.getName();
		if (orderService.findCountOrderByStatus1(username) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findCountOrderByStatus1(username));
	}

	@GetMapping("orders/countstatus2")
	public ResponseEntity<Integer> findOrderCountStatus2(Authentication auth) {
		String username = auth.getName();
		if (orderService.findCountOrderByStatus2(username) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findCountOrderByStatus2(username));
	}

	@GetMapping("orders/countstatus3")
	public ResponseEntity<Integer> findOrderCountStatus3(Authentication auth) {
		String username = auth.getName();
		if (orderService.findCountOrderByStatus3(username) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findCountOrderByStatus3(username));
	}

	@GetMapping("orders/countstatus4")
	public ResponseEntity<Integer> findOrderCountStatus4(Authentication auth) {
		String username = auth.getName();
		if (orderService.findCountOrderByStatus4(username) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findCountOrderByStatus4(username));
	}

	@GetMapping("orders/findorderstatus5")
	public ResponseEntity<List<Order>> findOrderStatus5(Authentication auth) {
		String username = auth.getName();
		if (orderService.findFullInfoOrderByStatus5(username).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findFullInfoOrderByStatus5(username));
	}

	@GetMapping("orders/findorderstatus1")
	public ResponseEntity<List<Order>> findOrderStatus1(Authentication auth) {
		String username = auth.getName();
		if (orderService.findFullInfoOrderByStatus1(username).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findFullInfoOrderByStatus1(username));
	}

	@GetMapping("orders/findorderstatus2")
	public ResponseEntity<List<Order>> findOrderStatus2(Authentication auth) {
		String username = auth.getName();
		if (orderService.findFullInfoOrderByStatus2(username).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findFullInfoOrderByStatus2(username));
	}

	@GetMapping("orders/findorderstatus3")
	public ResponseEntity<List<Order>> findOrderStatus3(Authentication auth) {
		String username = auth.getName();
		if (orderService.findFullInfoOrderByStatus3(username).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findFullInfoOrderByStatus3(username));
	}

	@GetMapping("orders/findorderstatus4")
	public ResponseEntity<List<Order>> findOrderStatus4(Authentication auth) {
		String username = auth.getName();
		if (orderService.findFullInfoOrderByStatus4(username).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderService.findFullInfoOrderByStatus4(username));
	}
	
	@PostMapping("order/cancel/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable("id") Integer orderId){
		Optional<Order> opt = orderService.findById(orderId);
		
		if (opt.get().getStatus()!=0) {
			return ResponseEntity.noContent().build();
		}
		
//		opt.get().setStatus(5);
		return ResponseEntity.ok(orderService.cancelOrder(opt.get()));
	}
	
//	@PostMapping("testsendmail")
//	public MailResponse sendmail(MailModel request) {
//		
//		request.setTo("nguyenvulan1998@gmail.com");
//		request.setSubject("TEst");;
//		return mail.sendEmailWithTemplate(request, null);
//		
//	}
	
	
}
