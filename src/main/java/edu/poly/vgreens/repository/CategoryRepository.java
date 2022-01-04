package edu.poly.vgreens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import edu.poly.vgreens.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
