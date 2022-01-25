package com.my.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.product.vo.Product;


@SpringBootTest
class ProductRepositoryTest {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ProductRepository repository;
	@Autowired
	private EntityManager em;
	
	@Test
	@Transactional
	@Commit
	void testSave() {
		Product p1 = new Product("C0020", "슈렉라떼", 8000);
		repository.save(p1); //update
	}

}
