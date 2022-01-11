package com.my.customer.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.customer.vo.Customer;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.vo.Product;
//Spring용 단위테스트
@RunWith(SpringJUnit4ClassRunner.class) //Juni4인 경우

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		/* "file:src/main/webapp/WEB-INF/spring/root-context.xml", */
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CustomerDAOOracleTest {

	@Autowired
	private CustomerDAOInterface dao;
	
	@Test
	public void findById() throws FindException {
		String id = "id1";
		Customer c = dao.findById(id);
		String expectedName = "오문정";
		String expectedPwd = "p1";
		String expectedAddress = "서울";
		assertEquals(expectedName, c.getName());
		
	}

	@Test
	public void modify() throws ModifyException, FindException {
		Customer c = new Customer();
		c.setId("id1");
		c.setPwd("pp1");
		c.setAddress("제주도");
		dao.modify(c);
		
		Customer c1 = dao.findById("id1");
		String expectedPwd = "pp1";
		String expectedName = "오문정";
		String expectedAddress = "제주도";
		assertEquals(expectedPwd, c1.getPwd());
		assertEquals(expectedName, c1.getName());
		assertEquals(expectedAddress, c1.getAddress());	
	}

}
