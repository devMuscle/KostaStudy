package com.my.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.customer.entity.Customer;
import com.my.order.entity.OrderInfo;
import com.my.order.entity.OrderLine;
import com.my.product.entity.Product;

@SpringBootTest
class OrderInfoRepositoryTest {
	@Autowired
	private OrderInfoRepository repository;
	
	@Test
	void testInsert() {
		OrderInfo info = new OrderInfo();
		Customer c = new Customer();
		c.setId("id1"); //주문자ID
		info.setOrder_c(c);
		List<OrderLine> lines = new ArrayList<>();
		OrderLine line = new OrderLine();
		line.setOrder_info(info);
		Product p = new Product();
		p.setProd_no("C0001");
		line.setOrder_p(p);
		line.setOrder_quantity(1);
		lines.add(line);
		
		OrderLine line2 = new OrderLine();
		line2.setOrder_info(info);
		Product p2 = new Product();
		p2.setProd_no("C0002");
		line2.setOrder_p(p2);
		line2.setOrder_quantity(2);
		lines.add(line2);
		
		info.setLines(lines);
		repository.save(info);
	}

}
