package com.my.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.TransactionScoped;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.customer.entity.Customer;

@SpringBootTest
class CustomerRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CustomerRepository repository;
	
	@Test
	@Transactional
	@Commit
	void testSave() {
		Customer c1 = new Customer("id1", "p1", "n1");
		repository.save(c1); //insert
		logger.info("--- 1. saved ---");
		
		Customer c2 = new Customer("id1", "비1", "이1");
		repository.save(c2); //update
		logger.info("--- 2. saved ---");
		//insert처리, update처리
	}
	
	@Test
	@Transactional
	void testFindById() {
		String id = "id1";
		Optional<Customer> optC1 = repository.findById(id); //2차캐시작업진행 select->엔터티객체관리->엔테티객체반환
		assertTrue(optC1.isPresent());
		
		Optional<Customer> optC2 = repository.findById(id); //1차캐시 작업 진행 엔테티 객체 반환
		assertTrue(optC2.isPresent());
		Customer c2 = optC2.get();
		entityManager.contains(optC2.get()); //Persistence Context에 c2객체 존재
	}
	
	@Test
	@Transactional
	void testFindAll() {
		Iterable<Customer> all = repository.findAll(); //2차캐시작업 SELECT
		Iterable<Customer> all2 = repository.findAll(); //2차캐시작업 SELECT
		//logger.info("findAll()=" + all);
	}
	
	@Test
	@Transactional
	@Commit
	void testDelete() {
		String id = "id2";
		repository.deleteById(id);
	}
	
	@Test
	@Transactional
	@Commit
	void testUpdate() {
		String id = "id1";
		Optional<Customer> optC = repository.findById(id);
		assertTrue(optC.isPresent());
		Customer c = optC.get();
		assertEquals("이1", c.getName());
		
		c.setName("이름1");
		repository.save(c);
		
	}
	
	@Test
	void  n() {
		String name = "이름";
		List<Customer> list = repository.findByName(name);
		int expectedSize = 2;
		assertTrue(expectedSize == list.size());
	}
	
	@Test
	void testFindByNameLike() {
		String word = "%이름%";
		List<Customer> list = repository.findByNameLike(word);
		list.forEach(c->logger.info("아이디:" + c.getId() + ", 이름:" + c.getName()));
	}

}
