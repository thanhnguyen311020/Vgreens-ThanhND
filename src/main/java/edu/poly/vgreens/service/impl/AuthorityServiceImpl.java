package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Authority;
import edu.poly.vgreens.repository.AccountRepository;
import edu.poly.vgreens.repository.AuthorityRepository;
import edu.poly.vgreens.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityRepository authorityRepository;

	@Override
	public <S extends Authority> S save(S entity) {
		return authorityRepository.save(entity);
	}

	@Override
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	@Override
	public List<Authority> findAll(Sort sort) {
		return authorityRepository.findAll(sort);
	}

	@Override
	public Optional<Authority> findById(Integer id) {
		return authorityRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		authorityRepository.deleteById(id);
	}

	@Override
	public void delete(Authority entity) {
		authorityRepository.delete(entity);
	}
	
	@Autowired
	AccountRepository acountDAO;
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators(){
		List<Account> accounts = acountDAO.getAdministrators();
		return authorityRepository.authoritiesOf(accounts);
	}
}
