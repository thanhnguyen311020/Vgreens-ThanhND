package edu.poly.vgreens.restcontroller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.service.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/statistic")
public class StatisticRestController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/getRevenueByMounth/{month}/{year}")
	public Float getRevenueByMonth(@PathVariable("month")Integer month
			, @PathVariable("year")Integer year) {
		
		return orderService.getRevenueByMonth(month,year);
	}
	
}
