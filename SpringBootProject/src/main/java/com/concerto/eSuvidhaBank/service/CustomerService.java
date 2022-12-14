package com.concerto.eSuvidhaBank.service;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concerto.eSuvidhaBank.entity.Customer;
import com.concerto.eSuvidhaBank.repo.CustomerRepository;



@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public Customer findCustomerById(String customerIdentity) {

		Optional<Customer> optc = this.customerRepository.findById(customerIdentity);
		return optc.orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));
	}

	public boolean insertCustomer(Customer customer) {
		if (!this.customerRepository.existsById(customer.getCustomerIdentity())) {
			this.customerRepository.save(customer);
			return true;
		}
		throw new EntityExistsException("Customer already exist Try another!!");
	}
}
