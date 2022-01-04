package edu.poly.vgreens.restcontroller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Role;
import edu.poly.vgreens.service.RoleService;

@RestController
@RequestMapping("/vgreens/rest/roles")
@CrossOrigin("*")
public class RoleRestController {

	@Autowired
	RoleService roleService;
	
	@GetMapping
	public List<Role> list (){
		return roleService.findAll();
	}
	
}
