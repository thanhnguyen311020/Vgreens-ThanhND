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

import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.entity.Unit;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.ProductService;
import edu.poly.vgreens.service.UnitService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/unit")
public class UnitRestController {
	
	@Autowired
	UnitService service;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("product")
	public List<Product> lists(){
		return productService.findAll();
	}
	
	@GetMapping()
	public ResponseEntity<List<Unit>> list(){
		if(service.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(service.findAll());
	}
	
	
	@PostMapping()
	public ResponseEntity<Unit> create(@RequestBody Unit unit){
		unit.setCreated_time(new Date());
		unit.setUpdated_time(new Date());
		return ResponseEntity.ok(service.save(unit));
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Unit> update(@PathVariable("id")Integer id,
			@RequestBody Unit unit){
		if(service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		unit.setCreated_time(new Date());
		unit.setUpdated_time(new Date());
		return ResponseEntity.ok(service.save(unit));
		
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id){
		if(service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Unit> findById(@PathVariable("id")Integer id){
		if(service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}	
		return ResponseEntity.ok(service.findById(id).get());
	}
	
}
