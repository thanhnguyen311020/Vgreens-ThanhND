package edu.poly.vgreens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Advertisement;


@Repository
public interface AdvertisementReponsitory extends JpaRepository<Advertisement, String>{
	
}
