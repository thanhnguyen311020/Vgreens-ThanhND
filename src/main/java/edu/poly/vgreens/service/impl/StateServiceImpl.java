package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.State;
import edu.poly.vgreens.repository.StateRepository;
import edu.poly.vgreens.service.StateService;
@Service

public class StateServiceImpl implements StateService {
	@Autowired
	StateRepository stateRepository;

	@Override
	public List<State> findByCountryOrderByNameAsc(Country country) {
		return stateRepository.findByCountryOrderByNameAsc(country);
	}

	@Override
	public <S extends State> S save(S entity) {
		return stateRepository.save(entity);
	}

	@Override
	public List<State> findAll() {
		return stateRepository.findAll();
	}

	@Override
	public List<State> findAll(Sort sort) {
		return stateRepository.findAll(sort);
	}

	@Override
	public Optional<State> findById(Integer id) {
		return stateRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		stateRepository.deleteById(id);
	}

	@Override
	public void delete(State entity) {
		stateRepository.delete(entity);
	}

	@Override
	public List<State> getStateByCountryID(Integer id) {
		return stateRepository.getStateByCountryID(id);
	}
	
	
}
