package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Supplier;

public interface SupplierService {

	void delete(Supplier entity);

	void deleteById(Integer id);

	Optional<Supplier> findById(Integer id);

	List<Supplier> findAll(Sort sort);

	List<Supplier> findAll();

	<S extends Supplier> S save(S entity);

}
