package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.ShippingRate;

import edu.poly.vgreens.repository.ShippingRateRepository;
import edu.poly.vgreens.service.ShippingRateService;
@Service
public class ShippingRateServiceImpl implements ShippingRateService {
	@Autowired
	ShippingRateRepository shippingRateRepository;

	@Override
	public ShippingRate getShippingRateForAccount(Account account) {
		//lay shipping rate tu account
		String state = account.getState();
		if (state ==null || state.isEmpty()) {
			state = account.getCity();
		}
		return shippingRateRepository.findByCountryAndState(account.getCountry(), state);
	}

	@Override
	public ShippingRate getShippingRateForAddress(Addresses address) {
		//lay shipping rate tu address
		String state = address.getState();
		if (state == null || state.isEmpty()) {
			state = address.getCity();
		}
		return shippingRateRepository.findByCountryAndState(address.getCountry(), state);
	}
	@Override
	public <S extends ShippingRate> S save(S entity) {
		return shippingRateRepository.save(entity);
	}

	@Override
	public List<ShippingRate> findAll() {
		return shippingRateRepository.findAll();
	}

	@Override
	public List<ShippingRate> findAll(Sort sort) {
		return shippingRateRepository.findAll(sort);
	}

	@Override
	public Optional<ShippingRate> findById(Integer id) {
		return shippingRateRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		shippingRateRepository.deleteById(id);
	}

	@Override
	public void delete(ShippingRate entity) {
		shippingRateRepository.delete(entity);
	}

	@Override
	public ShippingRate findShippingRateByStateLike(String state) {
		return shippingRateRepository.findShippingRateByStateLike(state);
	}

	@Override
	public ShippingRate updateCodSupported(ShippingRate entity) {
		if(entity.isCodSupported()) {
			entity.setCodSupported(false);
			return shippingRateRepository.save(entity);
		}else {
			entity.setCodSupported(true);
			return shippingRateRepository.save(entity);
		}
	}
}
