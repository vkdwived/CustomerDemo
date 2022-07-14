package com.customer.sample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customer.sample.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
