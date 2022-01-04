package edu.poly.vgreens.restcontroller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.model.AccountDTO;
import edu.poly.vgreens.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vgreens/rest/account")
public class AccountRestController {

	@Autowired
	AccountService accountService;
	
	@GetMapping("list")
	public ResponseEntity<List<Account>>  list(){
		if (accountService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accountService.findAll()) ; // kiem tra de dua ta status ok
	}
	@GetMapping("/status")
	public ResponseEntity<List<Account>>  listStatus( @RequestParam(name = "status")String status){
		if (accountService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		boolean st = false;
		if(status.equals("1")) {
			st = true;
		}
		return ResponseEntity.ok(accountService.getAccountByAvailabel(st)) ; // kiem tra de dua ta status ok
	}
	
//	@PostMapping("create")
//	public Account create(@RequestBody AccountDTO dto) {
//		Account entity = new Account();
//		BeanUtils.copyProperties(dto, entity);
//		
//		return accountService.save(entity);
//	}
	
	@PostMapping("create")
	public ResponseEntity<Account>  create(@RequestBody Account account) {
//		Account entity = new Account();
//		BeanUtils.copyProperties(dto, entity);
	if (accountService.existsById(account.getUsername())) {
			return ResponseEntity.badRequest().build();
		}
	
		account.setRegister_date(new Date());
		accountService.save(account);
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("{username}")
	public ResponseEntity<Account> update(@PathVariable("username")String username,
			@RequestBody Account account) {
		
		if (!accountService.existsById(account.getUsername())) {
			return ResponseEntity.notFound().build();
		}
	
		
		accountService.save(account);
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("/updateStatus/{username}")
	public Account updateStatus(@PathVariable("username")String username, @RequestBody Account account) {
		return accountService.updateStatus(account);
	}
	
	@DeleteMapping("{username}")
	public ResponseEntity<Void> delete(@PathVariable("username")String username) {
		
		if (!accountService.existsById(username) ){
			return ResponseEntity.notFound().build();
		}
	
		
		
		accountService.deleteById(username);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{username}")
	public  ResponseEntity<Account> getAccountByUsername(@PathVariable("username")String username) {
		
		Optional<Account> accountopt = accountService.findById(username);
		if (!accountopt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(accountopt.get());
	}
	
	
	@GetMapping()
	public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean>admin){
		
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		
		return accountService.findAll();
		
	}
		
}
