package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Role;

public interface RoleService {

	void delete(Role entity);

	void deleteById(String id);

	Optional<Role> findById(String id);

	List<Role> findAll(Sort sort);

	List<Role> findAll();

	<S extends Role> S save(S entity);

}
