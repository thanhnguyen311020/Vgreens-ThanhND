package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Authority;

public interface AuthorityService {

	void delete(Authority entity);

	void deleteById(Integer id);

	Optional<Authority> findById(Integer id);

	List<Authority> findAll(Sort sort);

	List<Authority> findAll();

	<S extends Authority> S save(S entity);

	List<Authority> findAuthoritiesOfAdministrators();

}
