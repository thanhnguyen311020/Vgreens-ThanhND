package edu.poly.vgreens.restcontroller.site;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Coupon;
import edu.poly.vgreens.model.CloseCustomer;
import edu.poly.vgreens.model2.MailModel;
import edu.poly.vgreens.model2.MailResponse;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.CouponService;
import edu.poly.vgreens.service.MailerService;
import edu.poly.vgreens.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vgreens/rest")

public class ScheduleRestController {
	
	@Autowired
	private MailerService mailerService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CouponService couSer;
	
	@Autowired
	OrderService orderService;
	
	
	@Scheduled(cron = "0 00 01 12 12 ?") // lên lịch chạy vào lúc 01:00: am ngày 12 tháng 12
	@PostMapping("sale/12-12")
	public MailResponse sendEmail() { // gửi mã giảm giá sale 12/12 cho tất cả khách hàng
		MailModel request = new MailModel();
		Map<String, Object> model = new HashMap<>();
		List<Account> listacc = accountService.findAll();
		
		Coupon cp = new Coupon();
		cp.setCoupon_percent(30);
		cp.setName("1212SIEUSALE");
		cp.setNote("Mã giảm giá tự động 12-12");
		cp.setCreated_time(new Date());
		cp.setUpdated_time(new Date());
		
		couSer.save(cp);
		
		
		for (Account account : listacc) {
			
			
			model.put("day", "12-12");
			model.put("name", cp.getName());
			request.setFrom("VGreens Market <vgreenMarket@gmail.com>");
			request.setSubject("Đại Hội Siêu Sale 12/12");
			request.setTo(account.getEmail());
			System.out.println("Đã gửi: "+ account.getEmail());
			mailerService.sendEmailWithHTML(request, model);
			
		}
		return null;
		
		

	}
	

	
	@Scheduled(cron = "00 32-33 13 * * ?") 
	@PostMapping("sendMailCloseCustomer")
	public MailResponse sendCouponCodeForCloseCustomerInMonth() { 
		MailModel request = new MailModel();
		Map<String, Object> model = new HashMap<>();
		int month =  Calendar.getInstance().get(Calendar.MONTH);
		int year =  Calendar.getInstance().get(Calendar.YEAR);;
		if(month == 0) {
			month = 12;
			year = year -1;
		}
		

		List<CloseCustomer> closeCustomers = orderService.topCloseCustomers(12, 2021);
		System.out.println(closeCustomers.size());	
		CloseCustomer closeC = closeCustomers.get(0);
		System.out.println("closeC"+ closeC.getUsername());	
		
		for(int i = 0; i< closeCustomers.size(); i++) {
			if(i==10) {
				break;
			}
			model.put("day", "<br/> Top 10 khách hàng trong tháng" + month);
			model.put("name", "123FDF");
			request.setFrom("VGreens Market <vgreenMarket@gmail.com>");
			request.setSubject("VGreens | Khuyến mãi giành riêng cho top 10 khách hàng trong tháng " + month);
			request.setTo(closeCustomers.get(i).getAccount().getEmail());
			System.out.println("Đã gửi: "+ closeCustomers.get(i).getAccount().getEmail());
			mailerService.sendEmailWithHTML(request, model);
			
		}

//		for (Account account : listacc) {
//			
//			
//			model.put("day", "12-12");
//			model.put("name", cp.getName());
//			request.setFrom("VGreens Market <vgreenMarket@gmail.com>");
//			request.setSubject("Đại Hội Siêu Sale 12/12");
//			request.setTo(account.getEmail());
//			System.out.println("Đã gửi: "+ account.getEmail());
//			mailerService.sendEmailWithHTML(request, model);
//			
//		}
		return null;
		
		

	}

}
