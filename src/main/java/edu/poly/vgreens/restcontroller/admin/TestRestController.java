package edu.poly.vgreens.restcontroller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.ProductService;

@RestController
@RequestMapping("/vgreens/test1")
@CrossOrigin("*")
public class TestRestController {

	@Autowired
	ProductService service;
	@Autowired
	AccountService accService;
	
	@GetMapping("product")
	public List<Product> list(){
		return service.findAll();
	}
	
	
	@GetMapping("account")
	public List<Account> list1(){
		return accService.findAll();
	}
}
