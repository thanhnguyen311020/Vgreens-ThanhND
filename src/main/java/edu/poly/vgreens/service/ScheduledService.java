package edu.poly.vgreens.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Coupon;
import edu.poly.vgreens.entity.Discount;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.model2.MailModel;
import edu.poly.vgreens.model2.MailResponse;
import edu.poly.vgreens.repository.ConsignmentRepository;
import edu.poly.vgreens.repository.ProductRepository;

@Service
public class ScheduledService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ConsignmentRepository consignmentRepository;
	
	@Autowired
	private MailerService mailerService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CouponService couSer;
	
	
	
//	@Scheduled(fixedDelay = 1000) /// lam xong task sau 1s chay
//	public void disableProductWhenQuantityZero() { /// disable san pham khi san pham do het hang
//		List<Consignment> con = consignmentRepository.findAll();
//		for (Consignment consignment : con) {
//			if (consignment.getCurrentQuantity()==0) {
//				 Product pro = productRepository.findProductByConsId(consignment.getId());
//				 
//				 if (pro==null) {
//					 System.out.println("Not found Product to consignment in Scheduled: "+ consignment.getId());
//				
//				 }else {
//					 pro.setAvailable(false);
//					 productRepository.save(pro);
//					 
//					 System.out.println("Product id set to disable from Scheduled Quantity =0: "+ pro.getId());
//				 }
//				
//				
//			}
//			
//		}
//		
//	
//		
//		
//	}
//
//	@Scheduled(fixedDelay = 1000) /// lam xong cong viec sau 1s thuc hien
//	public void disableProductWhenExpiryTime() { // disable khi san pham het han su dung
//		List<Consignment> con = consignmentRepository.findAll();
//
//		for (Consignment consignment : con) {
//			Date date1 = new Date();
//
//			if (consignment.getExpiry_time().before(date1) == true) {
//				Product pro = productRepository.findProductByConsId(consignment.getId());
//				if (pro == null) {
//					System.out.println("Not found Product to consignment in Scheduled: " + consignment.getId());
//
//				} else {
//					pro.setAvailable(false);
//					productRepository.save(pro);
//
//					System.out.println("Product id set to disable from Scheduled Exprity_Time: " + pro.getId());
//				}
//
//			}
//
//		}
//	}
	
//	@Scheduled(fixedDelay = 1000) /// lam xong cong viec sau 1s thuc hien
//	public void AddDiscountWhenProductExpiryTime() { // add discount khi han su dung con 3 ngay
//		List<Consignment> con = consignmentRepository.findAll();
//
//		for (Consignment consignment : con) {
//			
//			Date date1 = new Date();
//			Date date2 = consignment.getExpiry_time();
//			System.out.println("Date 1: "+date1);
//			System.out.println("Date 2: "+date2);
//		
//			long diff = date2.getTime() - date1.getTime();
//	        long diffHours = diff / (60 * 60 * 1000);
//
//			System.out.println("diffHours: "+diffHours);	
//			
//		
//			if (diffHours==72) {
//				Product pro = productRepository.findProductByConsId(consignment.getId());
//				if (pro == null) {
//					System.out.println("Not found Product to consignment in Scheduled: " + consignment.getId());
//
//				} else {
//					Discount dis = new Discount();
//					dis.setId(3);
//					pro.setDiscount(dis);
//					productRepository.save(pro);
//
//					System.out.println("Product id set to discount 30% from Scheduled Exprity_Time: " + pro.getId());
//				}
//
//			}
//
//		}
//	}
	
	 


}
