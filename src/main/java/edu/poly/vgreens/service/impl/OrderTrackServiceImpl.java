package edu.poly.vgreens.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.OrderTrack;
import edu.poly.vgreens.repository.OrderTrackRepository;
import edu.poly.vgreens.service.OrderTrackService;
@Service
public class OrderTrackServiceImpl implements OrderTrackService {
	@Autowired
	OrderTrackRepository orderTrackRepository;
	@Autowired
	private OrderRepository orderRepository;


	@Override
	public List<OrderTrack> findOrderTrackByOrderIdOrderByCreated_timeDesc(Integer id) {
		return orderTrackRepository.findOrderTrackByOrderIdOrderByCreated_timeDesc(id);
	}

	@Override
	public <S extends OrderTrack> S save(S entity) {
		return orderTrackRepository.save(entity);
	}






	@Override
	public List<OrderTrack> findAll(Sort sort) {
		return orderTrackRepository.findAll(sort);
	}



	@Override
	public Optional<OrderTrack> findById(Integer id) {
		return orderTrackRepository.findById(id);
	}



	@Override
	public void deleteById(Integer id) {
		orderTrackRepository.deleteById(id);
	}

	@Override
	@Query("SELECT o FROM Order o WHERE o.status >=2")
	public List<Order> findOrderByShipper() {
		return orderRepository.findOrderByShipper();
	}

	@Override
	public void  updateStatus(Integer orderId, Integer status, String name){
		Order orderInDB = orderRepository.findById(orderId).get();
		if (orderInDB.getStatus()!=status){
			Set<OrderTrack> orderTracks = orderInDB.getOrderTracks();

			OrderTrack track = new OrderTrack();
			track.setOrder(orderInDB);
			if (status==2){
				track.setTitle("Shipper "+name+" đang đến cửa hàng để lấy hàng");
				track.setDescription("Đang thực hiện");
			}else if (status==3){
				track.setTitle("Shipper "+name+" đang giao hàng đến người mua");
				track.setDescription("Đang thực hiện");
			}else if (status==4){
				orderInDB.setPayment_status("Đã Thanh Toán");
				track.setTitle("Shipper "+name+ " đã giao hàng thành công");
				track.setDescription("Đã giao hàng");
			}else if (status==5){
				track.setTitle("Người mua không nhận hàng, trả hàng Shipper "+name+" nhận hàng");
				track.setDescription("Đã nhận lại hàng");
			}
			track.setCreated_time(new Date());
			track.setUpdated_time(new Date());
			


			
			orderTrackRepository.save(track);
			orderInDB.setStatus(status);
			orderRepository.save(orderInDB);


		}
	}
}
