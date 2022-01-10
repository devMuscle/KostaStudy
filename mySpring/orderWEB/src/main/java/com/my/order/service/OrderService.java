package com.my.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderDAOInterface;
import com.my.order.dao.OrderDAOOracle;
import com.my.order.vo.OrderInfo;
@Service("oService")
public class OrderService {
	@Autowired
	private OrderDAOInterface dao;
	public void add(OrderInfo info) throws AddException{
		dao.add(info);
	}
	public List<OrderInfo> findById(String orderId) throws FindException{
		System.out.println("in ordeservice dao=" + dao);
		return dao.findById(orderId);
	}
}
