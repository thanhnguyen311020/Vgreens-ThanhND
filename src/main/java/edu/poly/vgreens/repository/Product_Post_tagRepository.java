package edu.poly.vgreens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Product_foods_tag;

@Repository
public interface Product_Post_tagRepository  extends JpaRepository<Product_foods_tag, Integer>{

}
