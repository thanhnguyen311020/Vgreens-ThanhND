package edu.poly.vgreens.restcontroller.admin;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.model.CloseCustomer;
import edu.poly.vgreens.model.StatisticRevenue;
import edu.poly.vgreens.service.OrderService;

@RestController
@RequestMapping("/vgreens/rest/orderAdmin")
@CrossOrigin("*")
public class OrderAdminRestController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("Amount")
	public Double getAmountOrder() {
		return orderService.getTotalSales();
	}
	@GetMapping("AmountPaypal")
	public Double getAmountPaypalOrder() {
		return orderService.getTotalPaypalSales();
	}
	@GetMapping("countOrderFinal")
	public Integer countOrder() {
		return orderService.countOrder();
	}

	@GetMapping()
	public List<Order> findByStatus(@RequestParam(name ="status")Integer status){
		return orderService.findOrderTodayByStatus(status);
	}
	@GetMapping("/findAll")
	public List<Order> findAllOrderBetweenDate(@RequestParam(name="startDate")String startDate,
			@RequestParam(name="endDate")String endDate) throws ParseException{
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		return orderService.findOrderByBetweenDate(st, en);
	}
	
	@GetMapping("/findByDateAndStatus")
	public List<Order> findAllOrderBetweenDateFillterByStatus(@RequestParam(name="startDate")String startDate,
			@RequestParam(name="endDate")String endDate,@RequestParam(name="status") String status) throws ParseException{
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		Integer sts = Integer.parseInt(status);
		return orderService.findOrderByBetweenDateFillterByStatus(st, en,sts);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Order> updateStatus(@PathVariable("id")Integer id, @RequestBody Order order) {
		if (orderService.findById(order.getId()).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		orderService.updateStatus(order);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/StatisticRevenue/{start}/{end}")
	public List<StatisticRevenue> getStatisticRevenueByMonthAnYear(@PathVariable("start")String start, @PathVariable("end")String end) throws ParseException {
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start+" 00:00:00");
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end+" 23:00:00");
//		System.out.println(st);
		return orderService.getStatisticRevenueByMonthAndYear(st, en);
	}
	
	@GetMapping("countOrder")
	public Integer countOrder(@RequestParam(name = "status")Integer status) {
		return orderService.countOrderByStatus(status);
	}
	


	@GetMapping("/getStatusOrder")
	public Map<Integer, Long> getStatusOrder(@RequestParam(name = "year") Integer year ,
			@RequestParam(name = "month") Integer month) {
		
		List<Object[]> result = orderService.getStatusOrderFailAndSuccessByMonthAndYear(year, month);
//		System.out.println("Result: "+ result);
		Map<Integer, Long> map = null;
		if(result != null && !result.isEmpty()) { 
			map = new HashMap<Integer, Long>();
			for(Object[] object : result) {
				map.put((Integer) object[0], (Long)object[1]);
			}
		}

		return map;
	}
	
	@GetMapping("sumAmountBetweenDate")
	public Double sumAmountOrderBetweenDate(@RequestParam(name="startDate")String startDate,
			@RequestParam(name="endDate")String endDate) throws ParseException{
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		
		return orderService.sumAmountBetweenDate(st, en);
	}
	
	@GetMapping("sumAmountPayPalBetweenDate")
	public Double sumAmountPayPalOrderBetweenDate(@RequestParam(name="startDate")String startDate,
			@RequestParam(name="endDate")String endDate) throws ParseException{
		Date st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		Date en = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		
		return orderService.sumAmountPaypalBetweenDate(st, en);
	}
	
	
	@GetMapping("getUSD")
	public String getUSD() {
		String temp ="";
		try {
			
			URL url = new URL("https://www.dongabank.com.vn/exchange/export");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			if (conn.getResponseCode() == 200) {
				Scanner scan = new Scanner(url.openStream());
				while (scan.hasNext()) {
					 temp = scan.nextLine();
					 temp = temp.replace("(", "");
					 temp = temp.replace(")", "");
					temp.trim();
//					JSONParser parser = new JSONParser();
//					JSONObject json = (JSONObject) parser.parse(temp);
//					System.out.println("Temp: "+ json);
				}
			}
			return temp;
		} catch (Exception e) {
			System.out.println("Ex"+ e.getMessage());
			return temp;
		}
	}
	
	@GetMapping("closeCustomer")
	public List<CloseCustomer> getCloseCustomerByMonth(@RequestParam(name="month")String month,
			@RequestParam(name = "year")String year){
		int m = Integer.parseInt(month);
		int y = Integer.parseInt(year);
		return orderService.topCloseCustomers(m, y);
	}
}
