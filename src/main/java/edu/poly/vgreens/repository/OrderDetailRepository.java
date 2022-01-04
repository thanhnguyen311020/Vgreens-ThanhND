package edu.poly.vgreens.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.model.StatisticalProduct;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	
//	@Query("SELECT od from OrderDetail od where od.Order.createDate >= ?1 and od.Order.createDate <= ?2")
//	public List<OrderDetail> getOrderDetailByStartDateAndEndDate(String startDate, String endDate);

    List<OrderDetail> findOrderDetailByOrderId(Integer id);
    
    @Query("SELECT od from OrderDetail od WHERE (od.order.createDate between ?1 and ?2) and od.order.status = 4 "
    		+ " order by od.order.createDate")
    List<OrderDetail> findOrderDetailsByDate(Date startDate, Date endDate);
    
    @Query("SELECT sum(od.quantity), od.product.name FROM OrderDetail od"
    		+ " WHERE MONTH(od.order.createDate) = ?1 AND YEAR(od.order.createDate) = ?2 AND od.order.status = 4 "
    		+ " GROUP BY od.product.id")
    List<Object[]> getQuantityProductOrderByMonth(Integer month, Integer Year);
    
    @Query("SELECT SUM(o.quantity) FROM OrderDetail o WHERE "
    		+ " (MONTH(o.order.createDate) = MONTH(CURDATE()) AND YEAR(o.order.createDate) = Year(CURDATE())) and o.order.status = 4")
    Integer countProductSales();

    OrderDetail findOrderDetailByProductId(Integer id);
    
    @Query("SELECT od FROM OrderDetail od WHERE od.order.account.username like ?1 AND od.order.status = 4 Order By od.order.createDate desc")
    List<OrderDetail> findOrderDetailByUsername(String username);
    
    @Query("SELECT new edu.poly.vgreens.model.StatisticalProduct(od.product, COUNT(od.quantity), SUM(od.unit_price * od.quantity), od.order) FROM OrderDetail od WHERE od.order.status = 4 AND "
    		+ " (od.order.createDate between ?1 and  ?2) "
    		+ " GROUP BY od.product.id")
    List<StatisticalProduct> statisticalProductByOrderDetails(Date startDate, Date endDate);
    
}
