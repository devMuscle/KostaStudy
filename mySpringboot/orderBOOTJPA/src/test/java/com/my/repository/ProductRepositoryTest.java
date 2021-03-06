package com.my.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.product.entity.Product;


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
	
	@Test
	@Transactional
	void testFindAll() {
		Iterable<Product> all = repository.findAll(); //2차캐시작업 SELECT
		//logger.info("findAll()=" + all);
	}
	
	@Test
	void testFindByNo() {
		String no = "C0001";
		Product p1 = repository.findByProdNo(no);
		logger.info("찾아온 상품 이름:" + p1.getProdName());
	}

}
