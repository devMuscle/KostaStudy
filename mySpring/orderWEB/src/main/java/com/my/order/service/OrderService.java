package com.my.order.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderDAOOracle;
import com.my.order.vo.OrderInfo;

public class OrderService {
	public void add(OrderInfo info) throws AddException{
		OrderDAOOracle dao = new OrderDAOOracle();
		dao.add(info);
	}
	public List<OrderInfo> findById(String orderId) throws FindException {
		OrderDAOOracle dao = new OrderDAOOracle();
		return dao.findById(orderId);		
	}
}
