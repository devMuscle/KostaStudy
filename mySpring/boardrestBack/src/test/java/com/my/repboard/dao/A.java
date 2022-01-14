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
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@RunWith(SpringJUnit4ClassRunner.class) // Juni4인 경우

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations = {
		/* "file:src/main/webapp/WEB-INF/spring/root-context.xml", */
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class A {

	@Autowired
	@Qualifier("rDAO")
	private RepBoardDAOInterface dao;

	@Test
	public void testfindAll() throws FindException {
		System.out.println("testList 메서드");
		List<RepBoard> list = dao.findAll();
		int expectedSize = 5;
		assertTrue(expectedSize == list.size());

		RepBoard r = list.get(0);
		String expectedBoardContent = "내용2";
		assertEquals(expectedBoardContent, r.getBoardContent());

	}

	@Test
	public void testFindByNo() {
		int boardNo = 1;
		System.out.println("findByNo 메서드");
		try {
			RepBoard rb = dao.findByNo(boardNo);
			assertTrue(0 == rb.getParentNo());
			assertEquals("id1", rb.getBoardC().getId());
			assertEquals("제목1", rb.getBoardTitle());
			assertTrue(0 == rb.getBoardViewcount());
		} catch (FindException e) {
			assertEquals("게시글이 없습니다", e.getMessage());
		}
	}

	@Test
	public void testPlusViewcount()  {
		System.out.println("testPlusViewcount 메서드");

		int boardNo = 1;

		RepBoard rb1, rb2;
		try {
			rb1 = dao.findByNo(boardNo);
			int expectedViewcount = rb1.getBoardViewcount() + 1;
			dao.plusViewCount(boardNo);
			rb2 = dao.findByNo(boardNo);
			assertTrue(expectedViewcount == rb2.getBoardViewcount());
		} catch (FindException e) {
			e.printStackTrace();
		} catch (ModifyException e) {
			e.printStackTrace();
		}


	}

	@Test
	public void testModify() throws FindException, ModifyException {
		System.out.println("modify 메서드");
		int boardNo = 4;
		RepBoard rb1 = dao.findByNo(boardNo);
		
		String testContent = "테스트";
		rb1.setBoardContent(testContent);
		dao.modify(rb1);
		
		RepBoard rb2 = dao.findByNo(boardNo);
		
		assertEquals("테스트", rb2.getBoardContent());
	}
	
	@Test
	public void testAdd() throws AddException, FindException {
		int boardNo = 23;
		RepBoard rb1 = dao.findByNo(boardNo);
		rb1.setBoardTitle("제목3");
		rb1.setParentNo(2);
		dao.add(rb1);
		
		RepBoard rb2 = dao.findByNo(boardNo+1);
		assertEquals("제목3", rb2.getBoardTitle());
		
	}
	
	@Test
	public void testRemove() throws RemoveException{
		int boardNo = 1;
		dao.remove(boardNo);
		
		RepBoard rb2;
		try {
			rb2 = dao.findByNo(boardNo);
		} catch (FindException e) {
			assertEquals("게시글이 없습니다", e.getMessage());
		}
	}
}
