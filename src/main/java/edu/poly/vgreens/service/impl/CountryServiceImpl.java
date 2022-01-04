package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.repository.CountryRepository;
import edu.poly.vgreens.service.CountryService;
@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryRepository countryRepository;

	@Override
	public List<Country> findAllByOrderByNameAsc() {
		return countryRepository.findAllByOrderByNameAsc();
	}

	@Override
	public <S extends Country> S save(S entity) {
		return countryRepository.save(entity);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public List<Country> findAll(Sort sort) {
		return countryRepository.findAll(sort);
	}

	@Override
	public Optional<Country> findById(Integer id) {
		return countryRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		countryRepository.deleteById(id);
	}

	@Override
	public void delete(Country entity) {
		countryRepository.delete(entity);
	}
	
	
}
