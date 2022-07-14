package com.customer.sample.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.sample.exception.ResourceNotFoundException;
import com.customer.sample.model.Address;
import com.customer.sample.model.Customer;
import com.customer.sample.service.AddressService;
import com.customer.sample.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getAllCustomer();
	}
	
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Integer customerId) {
		return customerService.getCustomerById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
	}
	
	@PatchMapping("/customer/{id}")
	public Customer updateCustomerFields(@RequestBody Customer modifiedCustomer, @PathVariable(value = "id") Integer customerId) {
		Customer existingCustomer = customerService.getCustomerById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		
		Set<Address> modifiedAddresses = modifiedCustomer.getAddresses();
		Set<Address> addressToUpdate = new HashSet<>();
		
		if (!modifiedAddresses.isEmpty()) {
			for (Address newAddress : modifiedAddresses) {
				Address oldAddress = addressService.getAddressById(newAddress.getId()).orElseThrow(() -> new ResourceNotFoundException("Address", "id", customerId));
				oldAddress.setAddress(newAddress.getAddress());
				addressService.updateAddress(oldAddress);
				addressToUpdate.add(oldAddress);
			}
		}
		
		if (modifiedCustomer.getEmail() != null) {
			existingCustomer.setEmail(modifiedCustomer.getEmail());
		}
		
		if (modifiedCustomer.getFirstName() != null) {
			existingCustomer.setFirstName(modifiedCustomer.getFirstName());
		}
		
		if (modifiedCustomer.getLastName() != null) {
			existingCustomer.setLastName(modifiedCustomer.getLastName());
		}
		
		if(!addressToUpdate.isEmpty()) {
			existingCustomer.setAddresses(addressToUpdate);
		}
		
		// Save customer
		customerService.updateCustomer(existingCustomer);
			
		return customerService.getCustomerById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
	}
}
