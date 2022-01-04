package edu.poly.vgreens.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.repository.ConsignmentRepository;
import edu.poly.vgreens.service.ConsignmentService;
@Service
public class ConsignmentServiceImpl implements ConsignmentService {
	@Autowired
	ConsignmentRepository consignmentRepository;

	@Override
	public <S extends Consignment> S save(S entity) {
		return consignmentRepository.save(entity);
	}

	@Override
	public Consignment getConsignmentById(Integer id) {
		return consignmentRepository.getConsignmentById(id);
	}

	@Override
	public List<Consignment> findAll() {
		return consignmentRepository.findAll();
	}

	@Override
	public Consignment getById(Integer id) {
		return consignmentRepository.getById(id);
	}

	@Override
	public List<Consignment> findAll(Sort sort) {
		return consignmentRepository.findAll(sort);
	}

	@Override
	public Optional<Consignment> findById(Integer id) {
		return consignmentRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		consignmentRepository.deleteById(id);
	}

	@Override
	public void delete(Consignment entity) {
		consignmentRepository.delete(entity);
	}

	@Override
	public List<Consignment> findConsignmentDate(Date startDate, Date endDate) {
		return consignmentRepository.findConsignmentDate(startDate, endDate);
	}
	
	public List<Consignment> findBySup(Integer id) {
		
		return consignmentRepository.findBySup(id);
	}

	@Override
	public List<Consignment> findConsignmentByExpiryLessDateNow() {
		return consignmentRepository.findConsignmentByExpiryLessDateNow();
	}

	@Override
	public List<Consignment> findConsignmentByExpiryGreaterThanOrEqualToDateNow() {
		return consignmentRepository.findConsignmentByExpiryGreaterThanOrEqualToDateNow();
	}
	
	

	
	
}
