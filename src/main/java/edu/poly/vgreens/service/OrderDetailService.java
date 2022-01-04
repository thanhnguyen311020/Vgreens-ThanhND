package edu.poly.vgreens.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.model.StatisticalProduct;


public interface OrderDetailService {

	void delete(OrderDetail entity);

	void deleteById(Integer id);

	Optional<OrderDetail> findById(Integer id);


	List<OrderDetail> findOrderDetailByOrderId(Integer id);

	List<OrderDetail> findAll();

	<S extends OrderDetail> S save(S entity);

	<S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

	List<Object[]> getQuantityProductOrderByMonth(Integer month, Integer Year);

	List<OrderDetail> findOrderDetailsByDate(Date startDate, Date endDate);

	Integer countProductSales();

	OrderDetail findOrderDetailByProductId(Integer id);

	List<OrderDetail> findOrderDetailByUsername(String username);

	List<StatisticalProduct> statisticalProductByOrderDetails(Date startDate, Date endDate);




}
