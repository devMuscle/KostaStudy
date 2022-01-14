package com.my.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.RepBoardDAOInterface;
import com.my.board.vo.RepBoard;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

@Service
public class RepBoardService {
	
	@Autowired
	private RepBoardDAOInterface dao;
	
	public List<RepBoard> findAll() throws FindException{
		return dao.findAll();
	}
	
	public RepBoard findByNo(int boardNo) throws FindException {
		try {
			dao.plusViewCount(boardNo);
			RepBoard rb = dao.findByNo(boardNo);
			return rb;
		} catch (ModifyException e) {
			e.printStackTrace();
			throw new FindException("조회수 증가 실패:" + e.getMessage());
		}
	}
	
	public void write(RepBoard repboard) throws AddException {
		try {
			dao.add(repboard);
		} catch (AddException e) {
			e.printStackTrace();
			throw new AddException("글쓰기 실패:" + e.getMessage());
		}
		
	}
}
