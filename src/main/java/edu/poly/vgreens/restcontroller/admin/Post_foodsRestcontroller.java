package edu.poly.vgreens.restcontroller.admin;

import java.util.Date;
import java.util.List;

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

import edu.poly.vgreens.entity.Post_foods;

import edu.poly.vgreens.service.Post_foodsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vgreens/rest/post_foods")
public class Post_foodsRestcontroller {
	
	@Autowired
	private Post_foodsService post_foodsService;
	
	
	@GetMapping("list")
	public ResponseEntity<List<Post_foods>>  list(){
		if (post_foodsService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(post_foodsService.findAll()) ; // kiem tra de dua ta status ok
	}
	
	@PostMapping("create")
	public Post_foods create (@RequestBody Post_foods post_foods) {
		
		return post_foodsService.save(post_foods);
	}
	
	@PutMapping("{id}")
	public Post_foods update(@PathVariable("id")Integer id, @RequestBody Post_foods post_foods) {
		post_foods.setUpdated_time(new Date());
		return post_foodsService.save(post_foods);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		post_foodsService.deleteById(id);
	}
	

}
