package com.my.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.my.customer.entity.Customer;
import com.my.order.entity.OrderInfo;
import com.my.order.entity.OrderLine;
import com.my.product.entity.Product;

@SpringBootTest
class OrderInfoRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderInfoRepository repository;
	
//	@Commit
//	@Transactional
	@Test
	void testInsert() {
	}
}