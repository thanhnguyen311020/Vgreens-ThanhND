package edu.poly.vgreens.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Consignment;


@Repository
public interface ConsignmentRepository extends JpaRepository<Consignment, Integer> {
	
	@Query(value="SELECT * FROM consignments con JOIN products p ON p.consignment_id=con.id Where p.id= ?1", nativeQuery = true)
	Consignment getConsignmentById(Integer id);
	
	@Query("SELECT con FROM Consignment con  WHERE con.created_time between ?1 and ?2 order by con.expiry_time desc")
	List<Consignment> findConsignmentDate(Date startDate, Date endDate);
	
	// lấy lô hết hạn
	@Query("SELECT con FROM Consignment con WHERE con.expiry_time <= DATE(NOW()) order by con.expiry_time desc")
	List<Consignment> findConsignmentByExpiryLessDateNow();
	// lấy lô còn hạn
	@Query("SELECT con FROM Consignment con WHERE con.expiry_time >= DATE(NOW()) order by con.expiry_time desc")
	List<Consignment> findConsignmentByExpiryGreaterThanOrEqualToDateNow();
	
	@Query(value = "SELECT con.* FROM consignments con  Where con.supplier_id=?1", nativeQuery = true)
	List<Consignment> findBySup(Integer id);


} 
