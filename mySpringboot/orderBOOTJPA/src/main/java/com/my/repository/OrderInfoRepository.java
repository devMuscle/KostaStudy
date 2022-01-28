package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.my.customer.entity.Customer;
import com.my.order.entity.OrderInfo;

public interface OrderInfoRepository extends CrudRepository<OrderInfo, Integer> {
	
	List<OrderInfo> findByOrderCustomer(Customer c); 
}
