package edu.poly.vgreens.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.vgreens.entity.Advertisement;
import edu.poly.vgreens.repository.AdvertisementReponsitory;
import edu.poly.vgreens.service.AdvertisementService;
import edu.poly.vgreens.service.UploadService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
	
	@Autowired
	AdvertisementReponsitory reponsitory;
	
	@Autowired
	UploadService uploadService;

	@Override
	public <S extends Advertisement> S save(S entity) {
		
			Optional<Advertisement> adver = reponsitory.findById(entity.getId());
			if(adver.isPresent()) {
				if(StringUtils.isEmpty(entity.getImg())) {
					entity.setImage(adver.get().getImage());
				}else {
					entity.setImage(uploadService.uploadImage(entity.getImg()));
				}
			}else {
				entity.setImage(uploadService.uploadImage(entity.getImg()));
			}
		
		
		return reponsitory.save(entity);
	}

	@Override
	public List<Advertisement> findAll() {
		return reponsitory.findAll();
	}

	@Override
	public long count() {
		return reponsitory.count();
	}

	@Override
	public void deleteById(String id) {
		reponsitory.deleteById(id);
	}

	@Override
	public Advertisement getById(String id) {
		return reponsitory.getById(id);
	}

	@Override
	public Optional<Advertisement> findById(String id) {
		return reponsitory.findById(id);
	}
	
	
}
