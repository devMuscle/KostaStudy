package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.order.entity.OrderInfo;

public interface OrderInfoRepository extends CrudRepository<OrderInfo, Integer> {
	
	
}
