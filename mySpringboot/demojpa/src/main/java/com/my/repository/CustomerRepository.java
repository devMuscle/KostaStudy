package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Customer> findByName(String name); //find~By~() //쿼리메서드
	
	List<Customer> findByNameLike(String word);
}
