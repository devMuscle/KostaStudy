package com.my.board.dao;

import java.rmi.RemoteException;
import java.util.List;

import com.my.board.vo.RepBoard;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

public interface RepBoardDAOInterface {
	/**
	 * 전체 게시글 검색한다
	 * @return
	 * @throws FindException
	 */
	public List<RepBoard> findAll() throws FindException;
	
	/**
	 * 글번호로 게시글을 검색한다 
	 * @param boardNo 글번호
	 * @return
	 * @throws FindException
	 */
	public RepBoard findByNo(int boardNo) throws FindException;

	/**
	 * 단어를 포함한 제목이나 내용의 게시글 검색한다
	 * @param word
	 * @return
	 * @throws FindException
	 */
	public List<RepBoard> findByWord(String word) throws FindException;
	
	/**
	 * 게시글의 조회수를 1증가한다
	 * @param boardNo 글번호
	 * @throws ModifyException
	 */
	public void plusViewCount(int boardNo) throws ModifyException;
	
	/**
	 * 게시글의 제목, 내용을 수정한다
	 * @param repBoard
	 * @throws ModifyException
	 */
	public void modify(RepBoard repBoard) throws ModifyException;
	
	/**
	 * 게시글 삭제
	 * @param repBoard
	 * @throws RemoveException
	 */
	public void remove(int boardNo) throws RemoveException;
	
	
	/**
	 * 게시글 추가
	 * @param repBoard
	 * @throws AddException
	 */
	public void add(RepBoard repBoard) throws AddException;

	
}
