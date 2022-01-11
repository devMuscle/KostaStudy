package com.my.product.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.customer.dao.CustomerDAOInterface;
import com.my.customer.vo.Customer;
import com.my.exception.FindException;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.vo.Product;
//Spring용 단위테스트
@RunWith(SpringJUnit4ClassRunner.class) //Juni4인 경우

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		/* "file:src/main/webapp/WEB-INF/spring/root-context.xml", */
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ProductDAOOracleTest {
	
	@Autowired
	@Qualifier("pDAO")
	private ProductDAOInterface dao;

	@Test
	public void testFindByNo() throws FindException {
		String prod_no = "C0001";
		Product p = dao.findByNo(prod_no); //DB검색결과

		String expectedProdName = "아메리카노";//예상
		int expectedProdPrice = 1000;
		
		assertEquals(expectedProdName, p.getProdName());
		assertTrue(expectedProdPrice == p.getProdPrice());
	}

	@Test
	public void testFindAll() throws FindException {
		System.out.println("testSelectAll메서드호출");
		List<Product>list = dao.findAll();
		int expectedSize = 10; //예상
		assertTrue(expectedSize == list.size());
		
		Product p = list.get(0);
		String expectedProdName = "ICE라테";
		assertEquals(expectedProdName, p.getProdName());
	}	
}
