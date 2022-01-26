package com.my.customer.dao;

import java.util.List;

import com.my.customer.entity.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

public interface CustomerDAOInterface {
	/**
	 * 저장소에 고객을 추가한다
	 * @param customer 추가할 고객객체
	 * @throws AddException  
	 *   ID가 중복된 경우에는 "이미 존재하는 아이디입니다"상세메시지를 갖는 예외가 발생한다.<br>
	 */
	public void add(Customer customer) throws AddException;
	/**
	 * 고객전체를 반환한다
	 * @return
	 * @throws FindException
	 */
	public List<Customer> findAll() throws FindException;
	/**
	 * 아이디에 해당 고객을 저장소에서 찾아 반환한다
	 * @param id 아이디
	 * @return 고객객체
	 * @throws FindException 아이디에 해당 고객이 저장소에 없으면 "아이디에 해당하는 고객이 없습니다"메시지를 갖는 예외발생한다
	 */
	public Customer findById(String id) throws FindException;
	/**
	 * 단어를 포함한 이름을 갖는 고객들을 반환한다
	 * @param word 단어
	 * @return 고객들
	 * @throws FindException 단어를 포함한 이름을 갖는 고객이 한명도 없으면 "단어를 포함한 이름의 고객은 없습니다"메시지를 갖는 예외가 발생한다
	 */
	public List<Customer> findByName(String word) throws FindException;
	/**
	 * 아이디에 해당하는 고객정보(비밀번호, 이름, 주소)를 수정한다<br>
	 * 단, 정보가 기존내용과 같을 경우는 변경하지 않는다
	 * @param c 변경할 고객객체
	 * @throws ModifyException c고객객체의 아이디가 저장소에 없으면 예외 발생한다<br>
	 *                         기존내용과 같을 경우는 "고객정보가 변경되지않았습니다"메시지를 갖는 예외가 발생한다
	 */
	public void modify(Customer c) throws ModifyException;
	public void remove(String id);
	public void printInfo();
	
}
