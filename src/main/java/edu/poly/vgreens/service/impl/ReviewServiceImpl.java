package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import edu.poly.vgreens.entity.Review;
import edu.poly.vgreens.model.ProductReviewGroupByProductID;
import edu.poly.vgreens.repository.ReviewRepository;

import edu.poly.vgreens.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public List<Review> commentProduct(Integer id) {
		return reviewRepository.commentProduct(id);
	}

	@Override
	public Optional<Review> findByUsernameAndProduct(String username, Integer id) {
		return reviewRepository.findByUsernameAndProduct(username, id);
	}

	@Override
	public List findReviewByProductId(Integer id) {
		return reviewRepository.findReviewByProductId(id);
	}

	@Override
	public ProductReviewGroupByProductID getProductReview(Integer id) {
		return reviewRepository.getProductReview(id);
	}

	@Override
	public List selectByRatingCount(Integer id) {
		return reviewRepository.selectByRatingCount(id);
	}

	@Override
	public Object getCountRating(Integer id) {
		return reviewRepository.getCountRating(id);
	}

	@Override
	public Object getCountRating5(Integer id) {
		return reviewRepository.getCountRating5(id);
	}

	@Override
	public Object getCountRating4(Integer id) {
		return reviewRepository.getCountRating4(id);
	}

	@Override
	public Object getCountRating3(Integer id) {
		return reviewRepository.getCountRating3(id);
	}

	@Override
	public Object getCountRating2(Integer id) {
		return reviewRepository.getCountRating2(id);
	}

	@Override
	public Object getCountRating1(Integer id) {
		return reviewRepository.getCountRating1(id);
	}

	@Override
	public <S extends Review> S save(S entity) {
		return reviewRepository.save(entity);
	}

	@Override
	public List<Review> findAll() {
		return reviewRepository.findAll();
	}

	@Override
	public Optional<Review> findById(Integer id) {
		return reviewRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		reviewRepository.deleteById(id);
	}

	@Override
	public void delete(Review entity) {
		reviewRepository.delete(entity);
	}

	@Override
	public List<ProductReviewGroupByProductID> getProductReviewGroupByProductID() {
		return reviewRepository.getProductReviewGroupByProductID();
	}

	@Override
	public Optional<Review> findReviewByProductIdUsernameOrderId(Integer id, String username, Integer orderid) {
		return reviewRepository.findReviewByProductIdUsernameOrderId(id, username, orderid);
	}

	
	
	
//	
	
	
}
