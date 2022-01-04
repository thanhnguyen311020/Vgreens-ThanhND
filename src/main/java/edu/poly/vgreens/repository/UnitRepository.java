package edu.poly.vgreens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

}
