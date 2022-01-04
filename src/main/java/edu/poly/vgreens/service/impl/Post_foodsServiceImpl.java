package edu.poly.vgreens.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.poly.vgreens.entity.Category;
import edu.poly.vgreens.entity.Post_foods;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.repository.Post_foodsRepository;
import edu.poly.vgreens.service.Post_foodsService;
import edu.poly.vgreens.service.UploadService;

@Service
public class Post_foodsServiceImpl implements Post_foodsService{

	@Autowired
	private Post_foodsRepository foodsRepository;
	
	@Autowired
	UploadService uploadService;
	
	
	@Autowired
	Cloudinary cloud;

	@Override
	public <S extends Post_foods> S save(S entity) {
		
		if (entity.getPostfoods_Id() != null) {
			Optional<Post_foods> optProduct = foodsRepository.findById(entity.getPostfoods_Id());
			if (optProduct.isPresent()) {
				if (StringUtils.isEmpty(entity.getImg())) {
					entity.setPost_image(optProduct.get().getPost_image());
				} else {
					entity.setPost_image(uploadService.uploadImage(entity.getImg()));
				}
			}
		}else {
			entity.setPost_image(uploadService.uploadImage(entity.getImg()));
//			return productRepository.save(entity);
		}

		return foodsRepository.save(entity);
	}

	@Override
	public List<Post_foods> findAll(Sort sort) {
		return foodsRepository.findAll(sort);
	}

	@Override
	public Optional<Post_foods> findById(Integer id) {
		return foodsRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		foodsRepository.deleteById(id);
	}

	@Override
	public Post_foods getById(Integer id) {
		return foodsRepository.getById(id);
	}
	
	
	@Override
	public Post_foods update(Post_foods entity) {
		if (entity.getPostfoods_Id() != null) {
			Optional<Post_foods> optProduct = foodsRepository.findById(entity.getPostfoods_Id());
			if (optProduct.isPresent()) {
				if (StringUtils.isEmpty(entity.getImg())) {
					entity.setPost_image(optProduct.get().getPost_image());
				} else {
					entity.setPost_image(uploadService.uploadImage(entity.getImg()));
				}
			}
		}else {
			entity.setPost_image(uploadService.uploadImage(entity.getImg()));
//			return productRepository.save(entity);
		}

		return foodsRepository.save(entity);
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
	public List<Post_foods> findAll() {
		return foodsRepository.findAll();
	}

	@Override
	public Optional<Post_foods> findPostFoodsByProductId(Integer id) {
		return foodsRepository.findPostFoodsByProductId(id);
	}
	
	
	
	
}
