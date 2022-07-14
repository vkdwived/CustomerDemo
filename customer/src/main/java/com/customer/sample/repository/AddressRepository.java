package com.customer.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.customer.sample.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
