package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.State;

public interface StateService {

	void delete(State entity);

	void deleteById(Integer id);

	Optional<State> findById(Integer id);

	List<State> findAll(Sort sort);

	List<State> findAll();

	<S extends State> S save(S entity);

	List<State> findByCountryOrderByNameAsc(Country country);

	List<State> getStateByCountryID(Integer id);

}
