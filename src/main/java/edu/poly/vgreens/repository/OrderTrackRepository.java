package edu.poly.vgreens.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.OrderTrack;

@Repository
public interface OrderTrackRepository extends JpaRepository<OrderTrack, Integer> {
	@Query("SELECT odt FROM OrderTrack odt WHERE odt.order.id =?1 Order By odt.created_time desc")
	List<OrderTrack> findOrderTrackByOrderIdOrderByCreated_timeDesc(Integer id);
}
