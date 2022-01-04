package edu.poly.vgreens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.ShippingRate;

@Repository
public interface ShippingRateRepository extends JpaRepository<ShippingRate, Integer> {

	@Query("SELECT sr FROM ShippingRate sr WHERE sr.country.id =?1 AND sr.state=?2")
	public ShippingRate findByCountryAndState(Integer countryID, String state);
	
	public ShippingRate findByCountryAndState(Country country, String state);

	public ShippingRate findShippingRateByStateLike(String state);
}
