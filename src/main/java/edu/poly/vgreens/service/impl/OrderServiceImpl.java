package edu.poly.vgreens.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.model.CloseCustomer;
import edu.poly.vgreens.model.StatisticRevenue;
import edu.poly.vgreens.model2.MailResponse;
import edu.poly.vgreens.model2.MailModel;
import edu.poly.vgreens.repository.OrderRepository;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.ConsignmentService;
import edu.poly.vgreens.service.MailerService;
import edu.poly.vgreens.service.OrderDetailService;
import edu.poly.vgreens.service.OrderService;
import edu.poly.vgreens.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;

	@Autowired
	private ConsignmentService consignmentService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDetailService orderDetailsSer;

	@Autowired
	private MailerService EmailService;

	@Autowired
	AccountService accSer;

	@Override
	public Order create(JsonNode orderData) {

		ObjectMapper mapper = new ObjectMapper();// chuyen doi json
		Order order = mapper.convertValue(orderData, Order.class);

		String email = order.getAccount().getEmail();

		order.setStatus(0); /// set trang thai don hang la da cho
		orderService.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};

		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)

				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());

		for (OrderDetail orderDetail : details) {// duyet tu orderdetail tren angularjs

			Consignment con = consignmentService.getConsignmentById(orderDetail.getProduct().getId());
//			con.setCurrentQuantity(con.getNumberOfInputs() - con.getNumberOfSales());
			con.setCurrentQuantity(con.getCurrentQuantity() - orderDetail.getQuantity());
			con.setNumberOfSales(con.getNumberOfSales() + orderDetail.getQuantity());
			consignmentService.save(con);

		}

		orderDetailsSer.saveAll(details);
		for (OrderDetail orderDetail : details) {// duyet tu orderdetail tren angularjs

			Product p = productService.findById(orderDetail.getProduct().getId()).get();
			if (p.getConsignment().getCurrentQuantity() <= 0) {
				p.setAvailable(false);
				productService.save(p);
			}

		}

		return order;

	}

//	private void updateStatusProduct(int id) {
//		Product p = productService.findById(id).get();
//		if()
//	}

	@Override
	public Order cancelOrder(Order entity) {

		List<OrderDetail> orderdetails = orderDetailsSer.findOrderDetailByOrderId(entity.getId());
		for (OrderDetail detail : orderdetails) {
			Consignment consignment = consignmentService.getConsignmentById(detail.getProduct().getId());
		
			// chua biet nen dung hay khong :)
			consignment.setCurrentQuantity(detail.getQuantity() + consignment.getCurrentQuantity());
			consignment.setNumberOfReturns(consignment.getNumberOfReturns() + detail.getQuantity());
			consignment.setNumberOfSales(consignment.getNumberOfSales() - detail.getQuantity());
			
			consignmentService.save(consignment);
			Product pd = productService.getById(detail.getProduct().getId());
//			if (pd != null) {
//				if (pd.getConsignment().getCurrentQuantity() <= 0) {
//					System.out.println("Product: " + (pd.getConsignment().getCurrentQuantity() <= 0));
//					pd.setAvailable(true);
//					
//					System.out.println("PD:" + pd);
//				}
//			}
			// kiểm tra lô hàng
			pd.setAvailable(true);
			productService.save(pd);
		}
		entity.setStatus(5);
		return orderRepository.save(entity);
	}

	@Override
	public Order updateStatus(Order entity) {
		Order order = orderRepository.findById(entity.getId()).get();
		if (order == null) {
			return entity;
		} else {
			if (order.getStatus() == 5) {
				return entity;
			} else {
				entity.setStatus(order.getStatus() + 1);
				return orderRepository.save(entity);
			}
		}

	}

	@Override
	public Order save(Order entity) {
		return orderRepository.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return orderRepository.findById(id);
	}

	@Override
	public long count() {
		return orderRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderRepository.deleteById(id);
	}

	@Override
	public void delete(Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
	}

	@Override
	public Float getRevenueByMonth(Integer month, Integer year) {
		return orderRepository.getRevenueByMonth(month, year);
	}

//	Thong ke service

	@Override
	public List<Order> findAllByAccountUsername(String username) {
		return orderRepository.findAllByAccountUsername(username);
	}

	@Override
	public List<StatisticRevenue> getStatisticRevenueByMonthAndYear(Date startDate, Date endDate) {
		return orderRepository.getStatisticRevenueByMonthAndYear(startDate, endDate);
	}

	@Override
	public List<Object[]> getStatusOrderFailAndSuccessByMonthAndYear(int year, int month) {
		return orderRepository.getStatusOrderFailAndSuccessByMonthAndYear(year, month);
	}

	@Override
	public List<Order> findOrderTodayByStatus(Integer status) {
		return orderRepository.findOrderTodayByStatus(status);
	}

	@Override
	public List<Order> findOrderByBetweenDate(Date startDate, Date endDate) {
		return orderRepository.findOrderByBetweenDate(startDate, endDate);
	}

	@Override
	public Integer countOrderByStatus(Integer status) {
		return orderRepository.countOrderByStatus(status);
	}

	@Override
	public Double getTotalSales() {
		return orderRepository.getTotalSales();
	}

	@Override
	public Integer countOrder() {
		return orderRepository.countOrder();
	}

	@Override
	public Double sumAmountBetweenDate(Date starDate, Date endDate) {
		return orderRepository.sumAmountBetweenDate(starDate, endDate);
	}

	@Override
	public Double sumAmountPaypalBetweenDate(Date starDate, Date endDate) {
		return orderRepository.sumAmountPaypalBetweenDate(starDate, endDate);
	}

	@Override
	public Double getTotalPaypalSales() {
		return orderRepository.getTotalPaypalSales();

	}

	@Override
	public List<Order> findOrderByBetweenDateFillterByStatus(Date startDate, Date endDate, Integer status) {
		return orderRepository.findOrderByBetweenDateFillterByStatus(startDate, endDate, status);
	}

	@Override
	public Integer findCountOrderByStatus5(String username) {
		return orderRepository.findCountOrderByStatus5(username);
	}

	@Override
	public Integer findCountOrderByStatus1(String username) {
		return orderRepository.findCountOrderByStatus1(username);
	}

	@Override
	public Integer findCountOrderByStatus2(String username) {
		return orderRepository.findCountOrderByStatus2(username);
	}

	@Override
	public Integer findCountOrderByStatus3(String username) {
		return orderRepository.findCountOrderByStatus3(username);
	}

	@Override
	public Integer findCountOrderByStatus4(String username) {
		return orderRepository.findCountOrderByStatus4(username);
	}

	@Override
	public Integer findCountAllOrder(String username) {
		return orderRepository.findCountAllOrder(username);
	}

	@Override
	public List<Order> findFullInfoOrderByStatus5(String username) {
		return orderRepository.findFullInfoOrderByStatus5(username);
	}

	@Override
	public List<Order> findFullInfoOrderByStatus1(String username) {
		return orderRepository.findFullInfoOrderByStatus1(username);
	}

	@Override
	public List<Order> findFullInfoOrderByStatus2(String username) {
		return orderRepository.findFullInfoOrderByStatus2(username);
	}

	@Override
	public List<Order> findFullInfoOrderByStatus3(String username) {
		return orderRepository.findFullInfoOrderByStatus3(username);
	}

	@Override
	public List<Order> findFullInfoOrderByStatus4(String username) {
		return orderRepository.findFullInfoOrderByStatus4(username);
	}

	@Override
	public Double getTotalSalesPaypal() {
		return orderRepository.getTotalSalesPaypal();
	}

	@Override
	public List<CloseCustomer> topCloseCustomers(int month, int year) {
		return orderRepository.topCloseCustomers(month, year);
	}

}
