package com.my.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.order.vo.OrderInfo;

public interface OrderRepository extends JpaRepository<OrderInfo, String>{
	List<OrderInfo> findByTestStr(String testStr);

}
