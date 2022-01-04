package edu.poly.vgreens.restcontroller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.model.StatisticRevenue;
import edu.poly.vgreens.model.StatisticalProduct;
import edu.poly.vgreens.service.OrderDetailService;
import edu.poly.vgreens.service.OrderService;

@RestController
@RequestMapping("/vgreens/rest/orderDetailAdmin")
@CrossOrigin("*")
public class OrderDetailAdminRestcontroller {
	
	@Autowired
	OrderDetailService service;
	
	@GetMapping("countProduct")
	public Integer countProduct() {
		return service.countProductSales();
	}
	
	@GetMapping
	public List<OrderDetail> findOrderDetailByOrderId(@RequestParam(name="orderId")Integer id){
		return service.findOrderDetailByOrderId(id);
	}
	
	@GetMapping("/orderDetailBy-Date")
	public List<OrderDetail> findOrderDetailByDate(
			@RequestParam(name="startDate")String startDate,
			@RequestParam(name="endDate")String endDate) throws ParseException{
		
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		return service.findOrderDetailsByDate(st, en);
	}
	
	@GetMapping("getSumQuantityProductInOrDerDetail")
	public Map<String, Long> getQuantity(@RequestParam(name="year")Integer year
			, @RequestParam(name = "month")Integer month){
		List<Object[]> result = service.getQuantityProductOrderByMonth(month, year);
		
		Map<String, Long> map = null;
		if(result != null && !result.isEmpty()) {
			map = new HashMap<>();
			for(Object[] obj : result) {
				System.out.println(obj[1]);
				map.put((String) obj[1], (Long)obj[0]);
			}
		}
		
		return map;
	}
	
	@GetMapping("/statisticProductByOrder")
	public List<StatisticalProduct> getStatisticProductByOrder(@RequestParam(name = "startDate")String start, 
			@RequestParam(name = "endDate")String end) throws ParseException {
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start+" 00:00:00");
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end+" 23:00:00");
//		System.out.println(st);
		return service.statisticalProductByOrderDetails(st, en);
	}
	
	
	@GetMapping("/getBy")
	public List<OrderDetail> findOrderDetailsByUsername(@RequestParam(name="username")String username){
		return service.findOrderDetailByUsername(username);
	}
	
}
