package com.my.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.board.vo.RepBoard;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Repository
public class RepBoardDAOOracle implements RepBoardDAOInterface {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<RepBoard> list() throws FindException {
		SqlSession session = null;
		try {
			sqlSessionFactory.openSession();
			List<RepBoard> list = session.selectList("com.my.board.RepBoardMapper.findAll");
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public RepBoard finByNo(int boardNo) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepBoard> findByWord(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plusViewCount(int boardNo) throws ModifyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(RepBoard repBoard) throws ModifyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(RepBoard repBoard) throws RemoveException {
		// TODO Auto-generated method stub

	}

}
