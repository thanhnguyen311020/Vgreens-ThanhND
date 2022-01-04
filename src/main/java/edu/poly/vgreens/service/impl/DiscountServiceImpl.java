package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.vgreens.entity.Discount;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.repository.DiscountRepository;
import edu.poly.vgreens.service.DiscountService;
import edu.poly.vgreens.service.UploadService;
@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	DiscountRepository discountRepository;
	
	@Autowired
	UploadService uploadService;

	@Override
	public <S extends Discount> S save(S entity) {
		
		if (entity.getId() != null) {
			Optional<Discount> optDiscount = discountRepository.findById(entity.getId());
			if (optDiscount.isPresent()) {
				if (StringUtils.isEmpty(entity.getImg())) {
					entity.setDiscount_image(optDiscount.get().getDiscount_image());
				} else {
					entity.setDiscount_image(uploadService.uploadImage(entity.getImg()));
				}
			}
		}else {
			entity.setDiscount_image(uploadService.uploadImage(entity.getImg()));
//			return productRepository.save(entity);
		}

		return discountRepository.save(entity);
		
	}

	@Override
	public List<Discount> findAll() {
		return discountRepository.findAll();
	}

	@Override
	public List<Discount> findAll(Sort sort) {
		return discountRepository.findAll(sort);
	}

	@Override
	public Optional<Discount> findById(Integer id) {
		return discountRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		discountRepository.deleteById(id);
	}

	@Override
	public void delete(Discount entity) {
		discountRepository.delete(entity);
	}

	@Override
	public Discount getById(Integer id) {
		return discountRepository.getById(id);
	}
	
	@Override
	public Discount updateStatus(Discount entity) {
		if(entity.getStatus()) {
			entity.setStatus(false);
			return discountRepository.save(entity);
		}else {
			entity.setStatus(true);
			return discountRepository.save(entity);
		}
	}

	@Override
	public List<Discount> findAllDiscountByStatusAndImage() {
		return discountRepository.findAllDiscountByStatusAndImage();
	}
	
	
	
}
