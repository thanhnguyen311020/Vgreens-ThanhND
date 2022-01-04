package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Country;

public interface CountryService {

	void delete(Country entity);

	void deleteById(Integer id);

	Optional<Country> findById(Integer id);

	List<Country> findAll(Sort sort);

	List<Country> findAll();

	<S extends Country> S save(S entity);

	List<Country> findAllByOrderByNameAsc();

}
