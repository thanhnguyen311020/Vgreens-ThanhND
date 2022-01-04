package edu.poly.vgreens.restcontroller.admin;

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
import edu.poly.vgreens.entity.State;
import edu.poly.vgreens.service.StateService;

@RequestMapping("/vgreens/rest/stateAdmin")
@RestController
@CrossOrigin("*")
public class StateAdminRestController {
	
	@Autowired
	StateService stateService;
	
	@GetMapping
	public List<State> list(){
		return stateService.findAll();
	}
	
	@GetMapping("/{country_id}")
	public List<State> getStateByCountryID(@PathVariable("country_id")Integer country_id){		
		return stateService.getStateByCountryID(country_id);
		
	}
	
	@PostMapping
	public State create(@RequestBody State state) {
		
		return stateService.save(state);
	}
	
	@PutMapping("{id}")
	public State update(@RequestBody State state, @PathVariable("id")Integer id) {
		
		if(stateService.findById(id).isEmpty()) {
			return null;
		}
		return stateService.save(state);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		stateService.deleteById(id);
	}
	
	@GetMapping("/getOne/{id}")
	public  ResponseEntity<State> getStateById(@PathVariable("id")Integer id) {
		
		if(stateService.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return  ResponseEntity.ok(stateService.findById(id).get());
		
	}
}
