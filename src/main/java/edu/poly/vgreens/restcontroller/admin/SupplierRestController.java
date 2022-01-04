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

import edu.poly.vgreens.entity.Supplier;
import edu.poly.vgreens.service.SupplierService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/supplier")
public class SupplierRestController {
	
	@Autowired
	SupplierService supplierService;
	
	@GetMapping()
	public ResponseEntity<List<Supplier>> list(){
		
		if(supplierService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(supplierService.findAll());
	}
	
	@PostMapping()
	public ResponseEntity<Supplier> create(@RequestBody Supplier entity){
		
		
		entity.setCreated_time(new Date());
		entity.setUpdate_time(new Date());
		return ResponseEntity.ok(supplierService.save(entity));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Supplier> update(@PathVariable("id")Integer id, @RequestBody Supplier entity){
		
		if(supplierService.findById(id).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(supplierService.save(entity));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id){
		
		if(supplierService.findById(id).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		supplierService.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Supplier> findById(@PathVariable("id")Integer id){
		
		if(supplierService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(supplierService.findById(id).get());
		
	}

}
