package com.my.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.board.vo.RepBoard;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Repository("rDAO")
public class RepBoardDAOOracle implements RepBoardDAOInterface {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<RepBoard> findAll() throws FindException {
		return findAll(1, 5);
	}
	
	@Override
	public List<RepBoard> findAll(int currentPage, int cntPerPage) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String, Integer> map = new HashMap<>();
			map.put("currentPage", currentPage); //1페이지 검색
			map.put("cntPerPage", cntPerPage); //페이지별 보여줄 목록수
			List<RepBoard> list = session.selectList("com.my.board.RepBoardMapper.findAll", map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	

	@Override
	public RepBoard findByNo(int boardNo) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			RepBoard rb = session.selectOne("com.my.board.RepBoardMapper.findByNo", boardNo);
			if (rb == null) {
				throw new FindException("게시글이 없습니다");
			}
			return rb;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<RepBoard> findByWord(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plusViewCount(int boardNo) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.RepBoardMapper.plusViewCount", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void modify(RepBoard repBoard) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.RepBoardMapper.modify", repBoard);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void remove(int boardNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.board.RepBoardMapper.remove1", boardNo);
			session.delete("com.my.board.RepBoardMapper.remove2", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}
	
	@Override
	public void add(RepBoard repBoard) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.RepBoardMapper.add", repBoard);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
