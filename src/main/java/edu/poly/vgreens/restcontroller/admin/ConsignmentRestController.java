package edu.poly.vgreens.restcontroller.admin;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Consignment;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.entity.Unit;
import edu.poly.vgreens.service.ConsignmentService;
import edu.poly.vgreens.service.ProductService;
import edu.poly.vgreens.service.UnitService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/consignment")
public class ConsignmentRestController {
	
	@Autowired
	ConsignmentService consignmentService;

	
	@GetMapping()
	public ResponseEntity<List<Consignment>> list(){
		
		if(consignmentService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(consignmentService.findAll());
		
	}
	@GetMapping("/ExpiryLessDateNow")
	public ResponseEntity<List<Consignment>> listLessDateNow(){
		if(consignmentService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(consignmentService.findConsignmentByExpiryLessDateNow());
	}
	
	@GetMapping("/ExpiryGreaterDateNow")
	public ResponseEntity<List<Consignment>> listExpiryGreaterDateNow(){
		if(consignmentService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(consignmentService.findConsignmentByExpiryGreaterThanOrEqualToDateNow());
	}
	
	@GetMapping("/date")
	public List<Consignment> getConsignmentByDate(@RequestParam(name ="startDate")String startDate
			,@RequestParam(name = "endDate")String endDate) throws Exception{
		Date st = new SimpleDateFormat("yyyy-MM-dd").parse(startDate+ " 00:00:00");
		Date ed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate+ " 23:00:00");
		
		return consignmentService.findConsignmentDate(st, ed);
	}
	
	

	
	
	@PostMapping()
	public ResponseEntity<Consignment> create(@RequestBody Consignment entity){		
//		entity.setCreated_time(new Date());		
//		entity.setUpdated_time(new Date());		
		return ResponseEntity.ok(consignmentService.save(entity));	
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Consignment> update(@PathVariable("id")Integer id,
			@RequestBody Consignment entity){		
		if(consignmentService.findById(id).isEmpty()) {
			return ResponseEntity.badRequest().build();
		}		
		return ResponseEntity.ok(consignmentService.save(entity));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id ){
		
		if(!consignmentService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		consignmentService.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("{sup}")
	  public List<Consignment> getBySup(@PathVariable("sup") Integer id) {
	      return   consignmentService.findBySup(id);
	    }
	

	
	
	
}
