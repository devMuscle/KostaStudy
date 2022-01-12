package com.my.repboard.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.board.dao.RepBoardDAOInterface;
import com.my.board.vo.RepBoard;
import com.my.exception.FindException;

//Spring용 단위테스트
@RunWith(SpringJUnit4ClassRunner.class) //Juni4인 경우

//Sprig 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

public class RepBoardDAOOracleTest {

	@Autowired
	@Qualifier("rDAO")
	private RepBoardDAOInterface dao;
	
	@Test
	public void testList() throws FindException {
		System.out.println("testList 메서드");
		List<RepBoard> list = dao.list();
		int expectedSize = 10;
		assertTrue(expectedSize == list.size());
		
	}

}
