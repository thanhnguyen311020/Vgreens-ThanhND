package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Unit;

import edu.poly.vgreens.repository.UnitRepository;

import edu.poly.vgreens.service.UnitService;
@Service
public class UnitServiceImpl implements UnitService {
	@Autowired
	UnitRepository unitRepository;

	@Override
	public <S extends Unit> S save(S entity) {
		return unitRepository.save(entity);
	}

	@Override
	public List<Unit> findAll() {
		return unitRepository.findAll();
	}

	@Override
	public List<Unit> findAll(Sort sort) {
		return unitRepository.findAll(sort);
	}

	@Override
	public Optional<Unit> findById(Integer id) {
		return unitRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		unitRepository.deleteById(id);
	}

	@Override
	public void delete(Unit entity) {
		unitRepository.delete(entity);
	}
	
}
