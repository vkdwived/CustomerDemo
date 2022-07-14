package com.customer.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.customer.sample.model.Address;

@Service
public interface AddressService {

	public List<Address> getAllAddress();
	
	public Optional<Address> getAddressById(Integer addressId);
	
	public Address updateAddress(Address address);
	
	public Address createAddress(Address address);
}
