package edu.poly.vgreens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	List<Country> findAllByOrderByNameAsc();
}
