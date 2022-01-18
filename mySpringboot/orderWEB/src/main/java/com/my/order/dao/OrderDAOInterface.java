package com.my.order.dao;

import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.vo.OrderInfo;

public interface OrderDAOInterface {
	/**
	 * 주문정보 추가한다
	 * @param info 주문기본정보 + 주문상세정볻르
	 * @throws AddException
	 */
	public void add(OrderInfo info) throws AddException;
	
	/**
	 * 주문자아이디 고객이 주문한 주문정보를 검색한다
	 * @param orderId 주문자아이디
	 * @return 주문정보(주문기본정보+상세정보들)들
	 * @throws FindException
	 */
	public List<OrderInfo> findById(String orderId) throws FindException;
	
	/**
	 * 주문일자에 주문된 주문정보를 검색한다
	 * @param dt 주문일자
	 * @return 주문정보(주문기본정보+상세정보들)들
	 * @throws FindException
	 */
	public List<OrderInfo> findByDate(Date dt) throws FindException;
	
	/**
	 * 주문기간에 주문된 주문정보를 검색한다
	 * @param startDt 검색할 주문시작일
	 * @param endDate 검색할 주문종료일
	 * @return 주문정보(주문기본정보+상세정보들)들
	 * @throws FindException
	 */
	public List<OrderInfo> findByDate(Date startDt, Date endDate) throws FindException;
}
