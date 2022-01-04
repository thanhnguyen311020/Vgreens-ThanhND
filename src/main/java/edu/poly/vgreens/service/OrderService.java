package edu.poly.vgreens.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.JsonNode;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.model.CloseCustomer;
import edu.poly.vgreens.model.StatisticRevenue;
import edu.poly.vgreens.model2.MailModel;

public interface OrderService {




	Float getRevenueByMonth(Integer month, Integer year);

	void deleteAll();

	void delete(Order entity);

	void deleteById(Integer id);

	long count();

	Optional<Order> findById(Integer id);

	List<Order> findAll();

	Order save(Order entity);

	Order create(JsonNode orderData) ;


    List<Order> findAllByAccountUsername(String username);

	List<Object[]> getStatusOrderFailAndSuccessByMonthAndYear(int year, int month);

	List<StatisticRevenue> getStatisticRevenueByMonthAndYear(Date startDate, Date endDate);

	List<Order> findOrderTodayByStatus(Integer status);

	Order updateStatus(Order entity);

	List<Order> findOrderByBetweenDate(Date startDate, Date endDate);

	Integer countOrderByStatus(Integer status);

	Integer countOrder();

	Double getTotalSales();

	Double sumAmountBetweenDate(Date starDate, Date endDate);

	Double sumAmountPaypalBetweenDate(Date starDate, Date endDate);

	Double getTotalPaypalSales();
	

	List<Order> findOrderByBetweenDateFillterByStatus(Date startDate, Date endDate, Integer status);

	List<Order> findFullInfoOrderByStatus4(String username);

	List<Order> findFullInfoOrderByStatus3(String username);

	List<Order> findFullInfoOrderByStatus2(String username);

	List<Order> findFullInfoOrderByStatus1(String username);

	List<Order> findFullInfoOrderByStatus5(String username);

	Integer findCountAllOrder(String username);

	Integer findCountOrderByStatus4(String username);

	Integer findCountOrderByStatus3(String username);

	Integer findCountOrderByStatus2(String username);

	Integer findCountOrderByStatus1(String username);

	Integer findCountOrderByStatus5(String username);

	Order cancelOrder(Order entity);

	Double getTotalSalesPaypal();

	List<CloseCustomer> topCloseCustomers(int month, int year);
}
