package edu.poly.vgreens.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.poly.vgreens.entity.Category;
import edu.poly.vgreens.repository.CategoryRepository;

import edu.poly.vgreens.service.CategoryService;

@Service

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	Cloudinary cloud;

	@Override
	public <S extends Category> S save(S entity) {

		entity.setImage(uploadImage(entity.getImg()));
		
		
//		try {
//			File file = new File(entity.getImg());
//			Map r = cloud.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
//			String img = ((String) r.get("secure_url"));
//			entity.setImage(img);
//			file.delete();
//			return categoryRepository.save(entity);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("=====Error=======" + e.getMessage());
//		}

		return categoryRepository.save(entity);
	}
	
	@Override
	public Category update(Category entity) {
		Optional<Category> caOpt = categoryRepository.findById(entity.getId());
		System.out.println("ID"+ caOpt.isEmpty());
		if (caOpt.isPresent()) {
			System.out.println("DK: "+ caOpt.get().getImage().equals(entity.getImage()));
			if (StringUtils.isEmpty(entity.getImg())) {
				entity.setImage(caOpt.get().getImage());
			} else {
				entity.setImage(uploadImage(entity.getImg()));
			}
		} else {
			entity.setImage(uploadImage(entity.getImg()));
			return categoryRepository.save(entity);
		}
		
		
//		try {
//			File file = new File(entity.getImg());
//			Map r = cloud.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
//			String img = ((String) r.get("secure_url"));
//			entity.setImage(img);
//			file.delete();
//			return categoryRepository.save(entity);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("=====Error=======" + e.getMessage());
//		}

		return categoryRepository.save(entity);
	}

	private String uploadImage(String entity) {
		String img = "";
		try {
			File file = new File(entity);
			System.out.println("file: "+ file);
			Map r = cloud.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
			img = ((String) r.get("secure_url"));
//			entity.setImage(img);
			deleteAllFileUpload(file);
			System.out.println("img link "+ img);
			return img;
		} catch (IOException e) {
			System.err.println("=====Error=======" + e.getMessage());
			return img;
		}
	
		
	}
	
	private void deleteAllFileUpload(File file) {
		String pathImage = file.toString();
		String pathFolder = pathImage.substring(0, pathImage.lastIndexOf('\\'));
		try {
			FileUtils.cleanDirectory( new File(pathFolder));
		} catch (IOException e) {
			System.err.println("=======Error========="+ e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}

//
//try {
//	File file = new File(entity.getImg());
//	Map r = cloud.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
//	String img = ((String) r.get("secure_url"));
//	entity.setImage(img);
//	file.delete();
////	return categoryRepository.save(entity);
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	System.err.println("=====Error=======" + e.getMessage());
//}
