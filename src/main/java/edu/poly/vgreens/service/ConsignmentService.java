package edu.poly.vgreens.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Consignment;


public interface ConsignmentService {

	void delete(Consignment entity);

	void deleteById(Integer id);

	Optional<Consignment> findById(Integer id);

	List<Consignment> findAll(Sort sort);

	List<Consignment> findAll();

	<S extends Consignment> S save(S entity);

	Consignment getById(Integer id);

	Consignment getConsignmentById(Integer id);

	List<Consignment> findConsignmentDate(Date startDate, Date endDate);
	List<Consignment> findBySup(Integer id);

	List<Consignment> findConsignmentByExpiryGreaterThanOrEqualToDateNow();

	List<Consignment> findConsignmentByExpiryLessDateNow();

}
