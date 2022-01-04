package edu.poly.vgreens.restcontroller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.poly.vgreens.entity.Category;
import edu.poly.vgreens.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest/category")
public class CategoryRestController {
	
	@Autowired 
	CategoryService categoryService;
	
	@Autowired
	Cloudinary cloud;
	
	@GetMapping("list")
	public ResponseEntity<List<Category>> list(){
		if (categoryService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(categoryService.findAll()) ; 
	}
	@PostMapping("create")
	public ResponseEntity<Category> create (@RequestBody Category category) {
		categoryService.save(category);
		return ResponseEntity.ok(category);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Category> update(@PathVariable("id")Integer id,
			@RequestBody Category category) {
		if(categoryService.findById(id).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(categoryService.update(category));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		categoryService.deleteById(id);
	}
	
	@GetMapping("{id}")
	public	Category getOne(@PathVariable("id") Integer id) { //lay 1 san pham theo Id
		return categoryService.findById(id).get();
	}
	
}
