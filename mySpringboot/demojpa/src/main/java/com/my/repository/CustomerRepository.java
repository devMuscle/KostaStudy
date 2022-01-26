package com.my.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	/**
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "SELECT * FROM jpacustomer WHERE c_name = ?1", nativeQuery=true)
//	@Query("SELECT c FROM customer c WHERE c.name = ?1")
	List<Customer> findByName(String name); //find~By~() //쿼리메서드
	
	List<Customer> findByNameLike(String word);
}
