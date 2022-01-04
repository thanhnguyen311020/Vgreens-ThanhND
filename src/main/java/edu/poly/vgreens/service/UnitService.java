package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Unit;

public interface UnitService {

	void delete(Unit entity);

	void deleteById(Integer id);

	Optional<Unit> findById(Integer id);

	List<Unit> findAll(Sort sort);

	List<Unit> findAll();

	<S extends Unit> S save(S entity);

}
