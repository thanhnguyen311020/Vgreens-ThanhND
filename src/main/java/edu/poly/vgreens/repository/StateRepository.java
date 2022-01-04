package edu.poly.vgreens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	public List<State> findByCountryOrderByNameAsc(Country country);
	
	@Query("SELECT s FROM State s where s.country.id = ?1")
	List<State> getStateByCountryID(Integer id);
	
	
}
