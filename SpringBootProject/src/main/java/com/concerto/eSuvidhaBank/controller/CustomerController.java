package com.concerto.eSuvidhaBank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.concerto.eSuvidhaBank.dto.Loandto;
import com.concerto.eSuvidhaBank.entity.Collateral;
import com.concerto.eSuvidhaBank.entity.Customer;
import com.concerto.eSuvidhaBank.entity.Loan;
import com.concerto.eSuvidhaBank.repo.CollateralRepository;
import com.concerto.eSuvidhaBank.service.CustomerService;
import com.concerto.eSuvidhaBank.service.LoanService;



@RestController
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CollateralRepository collateralRepository;
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/customers")
	public String home() {
		System.out.println("hello worlds");
		return "home";
	}
	
	@PostMapping("/customer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		this.customerService.insertCustomer(customer);
		return ResponseEntity.of(Optional.of("Success"));
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
		System.out.println(id);
	Customer c= this.customerService.findCustomerById(id);
	return ResponseEntity.of(Optional.of(c));
	
	}
	
	@GetMapping("/loan/{id}")
	public ResponseEntity<Loan> getLoanById(@PathVariable String id) {
		//System.out.println("hello wordl");
		Loan l=this.loanService.findLoanById(id);
		System.out.println(l);
		return ResponseEntity.of(Optional.of(l));
	}
	

	@PostMapping("/loan")
	public ResponseEntity<Loan> applyForLoan(@RequestBody Loandto loandto) throws Exception 
	{	
		System.out.println("hello wordl");
		Loan l= this.loanService.applyForLoan(loandto.getType(),loandto.getAmount(),loandto.getPeriod(),loandto.getIdentity());
		return ResponseEntity.of(Optional.of(l));
	}
	@PostMapping("/collateral/{customerId}")
	public ResponseEntity<String> addCollat(@RequestBody Collateral collateral,@PathVariable String customerId) {
		this.collateralRepository.save(collateral);
		this.loanService.updateLoan(customerId);
		return ResponseEntity.of(Optional.of("Success"));
		
	}
	
}
