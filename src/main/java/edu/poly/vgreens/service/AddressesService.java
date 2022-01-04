package edu.poly.vgreens.service;

import java.util.List;
import java.util.Optional;

import edu.poly.vgreens.entity.Addresses;

public interface AddressesService {

	void delete(Addresses entity);

	void deleteById(Integer id);

	Optional<Addresses> findById(Integer id);

	List<Addresses> findAll();

	<S extends Addresses> S save(S entity);

	boolean existsById(Integer id);

	void setNonDefaultForOthers(Integer addressdefaultId, String username);

	void setDefaultAddress(Integer addressdefaultId, String username);

	List<Addresses> findAddressesByAccount_Username(String username);

	Optional<Addresses> findAddressDefault(String username);

	Addresses deleteAddressesByIdAndAccount_Username(Integer id, String account_username);

	void deleteByIdAndAccount(Integer addressesId, String username);

	Addresses findByIdAndAccount(Integer addressesId, String username);

}
