	package edu.poly.vgreens.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.OrderDetail;
import edu.poly.vgreens.model.StatisticalProduct;
import edu.poly.vgreens.repository.OrderDetailRepository;
import edu.poly.vgreens.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public OrderDetail findOrderDetailByProductId(Integer id) {
		return orderDetailRepository.findOrderDetailByProductId(id);
	}

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public List<OrderDetail> findOrderDetailByOrderId(Integer id) {
		return orderDetailRepository.findOrderDetailByOrderId(id);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	@Override
	public void deleteById(Integer id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public List<Object[]> getQuantityProductOrderByMonth(Integer month, Integer Year) {
		return orderDetailRepository.getQuantityProductOrderByMonth(month, Year);
	}

	@Override
	public List<OrderDetail> findOrderDetailsByDate(Date startDate, Date endDate) {
		return orderDetailRepository.findOrderDetailsByDate(startDate, endDate);
	}

	@Override
	public Integer countProductSales() {
		return orderDetailRepository.countProductSales();
	}

	@Override
	public List<OrderDetail> findOrderDetailByUsername(String username) {
		return orderDetailRepository.findOrderDetailByUsername(username);
	}

	@Override
	public List<StatisticalProduct> statisticalProductByOrderDetails(Date startDate, Date endDate) {
		return orderDetailRepository.statisticalProductByOrderDetails(startDate, endDate);
	}

	
	
	

}
