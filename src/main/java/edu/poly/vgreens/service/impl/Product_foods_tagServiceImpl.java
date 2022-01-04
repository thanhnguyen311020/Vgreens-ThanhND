package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.vgreens.entity.Product_foods_tag;
import edu.poly.vgreens.repository.Product_Post_tagRepository;
import edu.poly.vgreens.service.Product_foods_tagService;

@Service
public class Product_foods_tagServiceImpl implements Product_foods_tagService {

	@Autowired
	private Product_Post_tagRepository product_Post_tagRepository;

	@Override
	public <S extends Product_foods_tag> S save(S entity) {
		return product_Post_tagRepository.save(entity);
	}

	@Override
	public List<Product_foods_tag> findAll() {
		return product_Post_tagRepository.findAll();
	}

	@Override
	public List<Product_foods_tag> findAll(Sort sort) {
		return product_Post_tagRepository.findAll(sort);
	}

	@Override
	public Optional<Product_foods_tag> findById(Integer id) {
		return product_Post_tagRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		product_Post_tagRepository.deleteById(id);
	}

	@Override
	public Product_foods_tag getById(Integer id) {
		return product_Post_tagRepository.getById(id);
	}
	
	

	
	
}
