package com.my.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.customer.entity.Customer;
import com.my.product.entity.Product;


@SpringBootTest
class CustomerRepositoryTest {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private EntityManager em;
	
	@Test
	@Transactional
	@Commit
	void testSave() {
		Customer c1 = new Customer("zzz", "p1", "김구", "서울대");
		repository.save(c1); //update
	}
	
	
	
	@Test
	@Transactional
	void testFindAll() {
		Iterable<Customer> all = repository.findAll(); //2차캐시작업 SELECT
		logger.info("findAll()=" + all);
	}
	
	
	@Test
	void testFindById() {
		String id = "zcccq";
		Optional<Customer> c1 = repository.findById(id);
		logger.info("찾아온 고객 이름:" + c1.get().getName());
	}
	
	
	@Test
	void testFindByName() {
		String name = "김간지";
		List<Customer> list = repository.findByName(name);
		list.forEach(c->logger.info("아이디:" + c.getId() + ", 이름:" + c.getName()));
	}

}
