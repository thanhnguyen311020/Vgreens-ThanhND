package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Role;

import edu.poly.vgreens.repository.RoleRepository;


import edu.poly.vgreens.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public <S extends Role> S save(S entity) {
		return roleRepository.save(entity);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> findAll(Sort sort) {
		return roleRepository.findAll(sort);
	}

	@Override
	public Optional<Role> findById(String id) {
		return roleRepository.findById(id);
	}

	@Override
	public void deleteById(String id) {
		roleRepository.deleteById(id);
	}

	@Override
	public void delete(Role entity) {
		roleRepository.delete(entity);
	}
	
}
