package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
	List<Customer> findByName(String name); 

}
