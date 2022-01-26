package com.my.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.customer.entity.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.entity.Product;



@SpringBootTest
public class OrderDAOOracleTest {

	@Autowired
	private OrderDAOInterface dao;
	private Logger log = 
			LoggerFactory.getLogger(getClass());
	@Test
	public void testAdd() {
		OrderInfo info = new OrderInfo();
		Customer c = new Customer();
		info.setOrderCustomer(c);
		String orderId = "id2";
		c.setId(orderId);
		
		List<OrderLine> lines = new ArrayList<>();
		info.setLines(lines);
		
		OrderLine line = new OrderLine();
		lines.add(line);
		
		String orderProdNo = "C0001";
		int failOrderQuantity = 1*100;
		Product p = new Product();
		p.setProdNo(orderProdNo);
		line.setOrderProduct(p);
		line.setOrderQuantity(failOrderQuantity);
		try {
			dao.add(info);
		} catch (AddException e1) {
			e1.printStackTrace();
		}
		
//		try {
//			List<OrderInfo> infos = dao.findById(orderId);
//		
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}	


}
