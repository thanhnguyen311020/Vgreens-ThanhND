package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Supplier;
import edu.poly.vgreens.repository.SupplierRepository;
import edu.poly.vgreens.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public <S extends Supplier> S save(S entity) {
		return supplierRepository.save(entity);
	}

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public List<Supplier> findAll(Sort sort) {
		return supplierRepository.findAll(sort);
	}

	@Override
	public Optional<Supplier> findById(Integer id) {
		return supplierRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		supplierRepository.deleteById(id);
	}

	@Override
	public void delete(Supplier entity) {
		supplierRepository.delete(entity);
	}
	
}
