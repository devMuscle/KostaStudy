package com.my.order.service;

import com.my.exception.AddException;
import com.my.order.dao.OrderDAOOracle;
import com.my.order.vo.OrderInfo;

public class OrderService {
	public void add(OrderInfo info) throws AddException{
		OrderDAOOracle dao = new OrderDAOOracle();
		dao.add(info);
	}
}
