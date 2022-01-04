package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import edu.poly.vgreens.entity.Advertisement;

public interface AdvertisementService {

	Advertisement getById(String id);

	void deleteById(String id);

	long count();

	List<Advertisement> findAll();

	<S extends Advertisement> S save(S entity);

	Optional<Advertisement> findById(String id);

}
