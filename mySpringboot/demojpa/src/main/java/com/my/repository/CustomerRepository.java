package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	
}
