package com.customer.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.sample.model.Address;
import com.customer.sample.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository repository;

	@Override
	public List<Address> getAllAddress() {
		// TODO Auto-generated method stub
		return (List<Address>) repository.findAll();
	}

	@Override
	public Optional<Address> getAddressById(Integer addressId) {
		// TODO Auto-generated method stub
		return repository.findById(addressId);
	}

	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		return repository.save(address);
	}

	@Override
	public Address createAddress(Address address) {
		// TODO Auto-generated method stub
		return repository.save(address);
	}

}
