package edu.poly.vgreens.restcontroller.site;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.entity.State;
import edu.poly.vgreens.model.StateDTO;
import edu.poly.vgreens.service.StateService;

@RestController
public class StateRestController {

	@Autowired
	private StateService stateService;
	
	@GetMapping("/states/list_state_by_country/{id}")
	public  List<StateDTO> listByCountry(@PathVariable("id") Integer countryId){
		Country country = new Country();
		country.setId(countryId);
		List<State> listStates = stateService.findByCountryOrderByNameAsc(country);
		List<StateDTO> result = new ArrayList<>();
		
		for (State state : listStates) {
//			StateDTO statedto = new StateDTO();
			result.add(new StateDTO(state.getId(), state.getName()));
		}
		return result;
	}
	@GetMapping("/vgreens/rest/statesite")
	public List<State> list(){
		return stateService.findAll();
	}
	@GetMapping("/vgreens/rest/statesite/{country_id}")
	public List<State> getStateByCountryID(@PathVariable("country_id")Integer country_id){
		
		
		return stateService.getStateByCountryID(country_id);
		
	}

}
