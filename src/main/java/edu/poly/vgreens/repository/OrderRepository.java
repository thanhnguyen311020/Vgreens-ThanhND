package edu.poly.vgreens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.model.CloseCustomer;
import edu.poly.vgreens.model.StatisticRevenue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("select o.amount from Order o where Month(o.createDate) = ?1 and Year(o.createDate) = ?2 and o.status = 2")
	public Float getRevenueByMonth(Integer month,Integer year);
	
	@Query("Select sum(o.amount) FROM Order o WHERE "
			+ " (MONTH(o.createDate) = MONTH(CURDATE()) AND YEAR(o.createDate) = Year(CURDATE())) and o.status = 4")
	Double getTotalSales();
	@Query("Select sum(o.amount) FROM Order o WHERE o.payment_method like '%Paypal%' AND"
			+ " (MONTH(o.createDate) = MONTH(CURDATE()) AND YEAR(o.createDate) = Year(CURDATE())) and o.status = 4")
	Double getTotalSalesPaypal();
	 
	@Query("Select sum(o.amount) FROM Order o WHERE o.payment_method like '%paypal%' AND"
			+ " (MONTH(o.createDate) = MONTH(CURDATE()) AND YEAR(o.createDate) = Year(CURDATE())) and o.status = 4")
	Double getTotalPaypalSales();
	
	@Query("Select COUNT(o) FROM Order o WHERE "
			+ " (MONTH(o.createDate) = MONTH(CURDATE()) AND YEAR(o.createDate) = Year(CURDATE()))"
			+ " and (o.status = 4 or o.status = 0)")
	Integer countOrder();
	
	@Query("SELECT new StatisticRevenue(YEAR(o.createDate), MONTH(o.createDate), SUM(o.amount), COUNT(o), SUM(o.discount)) FROM Order o "
			+ " where (o.createDate between ?1 and  ?2) and o.status = 4"
			+ " GROUP BY YEAR(o.createDate), MONTH(o.createDate)"
			+ " order by o.createDate")
	public List<StatisticRevenue> getStatisticRevenueByMonthAndYear(Date startDate, Date endDate);
	
	@Query("SELECT o.status, count(o.id) FROM Order o  "
			+ " WHERE (o.status = 0 or o.status = 4) and  (YEAR(o.createDate) = ?1 and MONTH(o.createDate) = ?2)"
			+ " group by o.status")
	public List<Object[]> getStatusOrderFailAndSuccessByMonthAndYear(int year, int month);
	
	@Query("SELECT SUM(o.amount) FROM Order o WHERE o.status = 4 and (o.createDate between ?1 and ?2)")
	Double sumAmountBetweenDate(Date starDate, Date endDate);
	
	@Query("SELECT SUM(o.amount) FROM Order o WHERE o.payment_method like '%paypal%' and o.status = 4 and (o.createDate between ?1 and ?2)")
	Double sumAmountPaypalBetweenDate(Date starDate, Date endDate);
	

	List<Order> findAllByAccountUsername(String username);
	
	@Query("SELECT o FROM Order o WHERE DATE(o.createDate) = DATE(NOW()) AND o.status = ?1")
	List<Order> findOrderTodayByStatus(Integer status);
	
	@Query("SELECT o FROM Order o WHERE o.createDate between ?1 and ?2")
	List<Order> findOrderByBetweenDate(Date startDate, Date endDate);
	
	@Query("SELECT o FROM Order o WHERE o.createDate between ?1 and ?2 and o.status=?3")
	List<Order> findOrderByBetweenDateFillterByStatus(Date startDate, Date endDate,Integer status);
	
	@Query("SELECT count(o) From  Order o  WHERE DATE(o.createDate) = DATE(NOW()) AND o.status = ?1")
	Integer countOrderByStatus(Integer status);
	
	@Query("SELECT count(o.id) FROM Order o WHERE o.status=5 and o.account.username =?1")
	Integer findCountOrderByStatus5(String username);
	@Query("SELECT count(o.id) FROM Order o WHERE o.status=1 and o.account.username =?1")
	Integer findCountOrderByStatus1(String username);
	@Query("SELECT count(o.id) FROM Order o WHERE o.status=2 and o.account.username =?1")
	Integer findCountOrderByStatus2(String username);
	@Query("SELECT count(o.id) FROM Order o WHERE o.status=3 and o.account.username =?1")
	Integer findCountOrderByStatus3(String username);
	@Query("SELECT count(o.id) FROM Order o WHERE o.status=4 and o.account.username =?1")
	Integer findCountOrderByStatus4(String username);
	
	
	@Query("SELECT count(o.id) FROM Order o Where o.account.username =?1")
	Integer findCountAllOrder(String username);
	
	
	
	@Query("SELECT o FROM Order o WHERE o.status=5 and o.account.username =?1")
	List<Order> findFullInfoOrderByStatus5(String username);
	
	@Query("SELECT o FROM Order o WHERE o.status=1 and o.account.username =?1")
	List<Order> findFullInfoOrderByStatus1(String username);
	
	@Query("SELECT o FROM Order o WHERE o.status=2 and o.account.username =?1")
	List<Order> findFullInfoOrderByStatus2(String username);
	
	@Query("SELECT o FROM Order o WHERE o.status=3 and o.account.username =?1")
	List<Order> findFullInfoOrderByStatus3(String username);
	
	@Query("SELECT o FROM Order o WHERE o.status=4 and o.account.username =?1")
	List<Order> findFullInfoOrderByStatus4(String username);
	@Query("SELECT o FROM Order o WHERE o.status >=1")
	List<Order> findOrderByShipper();
	
	@Query("SELECT new CloseCustomer(o.account.username, SUM(o.amount),   o.account)  FROM Order o WHERE MONTH(o.createDate) = ?1 and YEAR(o.createDate) = ?2 and o.status = 4"
			+ " group by o.account Order By   SUM(o.amount) desc " )
	List<CloseCustomer> topCloseCustomers(int month, int year);
}
