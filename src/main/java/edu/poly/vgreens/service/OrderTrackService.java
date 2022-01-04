package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import edu.poly.vgreens.entity.Order;
import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.OrderTrack;
import org.springframework.data.jpa.repository.Query;

public interface OrderTrackService {

	void deleteById(Integer id);



	Optional<OrderTrack> findById(Integer id);

	List<OrderTrack> findAll(Sort sort);

    List<OrderTrack> findOrderTrackByOrderIdOrderByCreated_timeDesc(Integer id);

    <S extends OrderTrack> S save(S entity);


    @Query("SELECT o FROM Order o WHERE o.status >=2")
    List<Order> findOrderByShipper();

    void  updateStatus(Integer orderId, Integer status,String name);
}
