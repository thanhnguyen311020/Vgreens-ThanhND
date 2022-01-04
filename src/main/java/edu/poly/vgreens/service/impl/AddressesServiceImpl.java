package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.repository.AddressesRepository;
import edu.poly.vgreens.service.AddressesService;
@Service
public class AddressesServiceImpl implements AddressesService {
	@Autowired
	AddressesRepository addressesRepository;

	@Override
	public <S extends Addresses> S save(S entity) {
		return addressesRepository.save(entity);
	}

	@Override
	public Addresses findByIdAndAccount(Integer addressesId, String username) {
		return addressesRepository.findByIdAndAccount(addressesId, username);
	}

	@Override
	public void deleteByIdAndAccount(Integer addressesId, String username) {
		addressesRepository.deleteByIdAndAccount(addressesId, username);
	}

	@Override
	public Addresses deleteAddressesByIdAndAccount_Username(Integer id, String account_username) {
		return addressesRepository.deleteAddressesByIdAndAccount_Username(id, account_username);
	}

	@Override
	public Optional<Addresses> findAddressDefault(String username) {
		return addressesRepository.findAddressDefault(username);
	}

	@Override
	public List<Addresses> findAddressesByAccount_Username(String username) {
		return addressesRepository.findAddressesByAccount_Username(username);
	}

	@Override
	public void setDefaultAddress(Integer addressdefaultId, String username) {

		if (addressdefaultId >0) {
    		addressesRepository.setDefaultAddress(addressdefaultId);
		}
    	
    	addressesRepository.setNonDefaultForOthers(addressdefaultId, username);
	}

	@Override
	public void setNonDefaultForOthers(Integer addressdefaultId, String username) {
		addressesRepository.setNonDefaultForOthers(addressdefaultId, username);
	}

	@Override
	public boolean existsById(Integer id) {
		return addressesRepository.existsById(id);
	}

	@Override
	public List<Addresses> findAll() {
		return addressesRepository.findAll();
	}

	@Override
	public Optional<Addresses> findById(Integer id) {
		return addressesRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		addressesRepository.deleteById(id);
	}

	@Override
	public void delete(Addresses entity) {
		addressesRepository.delete(entity);
	}
	
}
