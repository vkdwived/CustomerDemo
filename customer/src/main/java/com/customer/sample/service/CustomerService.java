package com.customer.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.customer.sample.model.*;

@Service
public interface CustomerService {
	
	List<Customer> getAllCustomer();
	
	Optional<Customer> getCustomerById(int id);
	
	void updateCustomer(Customer customer);
	
	Customer createCustomer(Customer customer);

}
