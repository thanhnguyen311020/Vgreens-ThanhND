package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.ShippingRate;

public interface ShippingRateService {

	void delete(ShippingRate entity);

	void deleteById(Integer id);

	Optional<ShippingRate> findById(Integer id);

	List<ShippingRate> findAll(Sort sort);

	List<ShippingRate> findAll();

	<S extends ShippingRate> S save(S entity);

	ShippingRate getShippingRateForAddress(Addresses address);

	ShippingRate getShippingRateForAccount(Account account);

	ShippingRate findShippingRateByStateLike(String state);
	ShippingRate updateCodSupported(ShippingRate entity);
}
